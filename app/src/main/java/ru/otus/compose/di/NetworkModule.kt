package ru.otus.compose.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.otus.compose.data.HeaderInterceptor
import ru.otus.compose.data.MarvelService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideHeroService(okHttpClient: OkHttpClient): MarvelService {
        val contentType = "application/json".toMediaTypeOrNull()
            ?: throw IllegalArgumentException("Should be not null")
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
            .create(MarvelService::class.java)
    }

    @Provides
    @Singleton
    fun provideOtherInterceptorOkHttpClient(
        otherInterceptor: HeaderInterceptor,
        @ApplicationContext
        context: Context,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(AuthorizationInterceptor())
            .addInterceptor(TimeStampInterceptor())
            .addInterceptor(otherInterceptor)
            .cache(createCache(context))
            .build()
    }

    private fun createCache(context: Context): Cache {
        return Cache(
            directory = context.cacheDir.resolve(HTTP_CACHE_DIR),
            maxSize = CACHE_SIZE,
        )
    }

    private const val HTTP_CACHE_DIR = "http_cache"
    private const val CACHE_SIZE = 20L * 1024L * 1024L // 20 MB
}
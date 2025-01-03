package ru.otus.compose.data

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val HEROES_ACCESS_TOKEN = "1243325372714127"

class HeaderInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("access-token", HEROES_ACCESS_TOKEN)
                .build()
        )
    }
}

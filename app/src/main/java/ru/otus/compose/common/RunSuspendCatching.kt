package ru.otus.compose.common

import kotlin.coroutines.cancellation.CancellationException

inline fun <R> runSuspendCatching(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (c: CancellationException) {
        throw c
    } catch (e: Throwable) {
        Result.failure(e)
    }
}

inline fun <T, R> Result<T>.resolve(
    onSuccess: (value: T) -> R,
    onError: (exception: Throwable) -> R,
): R {
    return when (val exception = exceptionOrNull()) {
        null -> onSuccess(getOrThrow())
        else -> onError(exception)
    }
}

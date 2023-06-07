package com.komissarov.tasktracker

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.util.concurrent.CancellationException
import javax.inject.Inject

class CoroutineDispatchers @Inject constructor() {

    val io: CoroutineDispatcher = Dispatchers.IO
    val default: CoroutineDispatcher = Dispatchers.Default
    val main: CoroutineDispatcher = Dispatchers.Main
    val unconfined: CoroutineDispatcher = Dispatchers.Unconfined
}

inline fun <R> launchWithExceptionHandle(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Result.failure(e)
    }
}
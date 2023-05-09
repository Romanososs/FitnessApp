package com.example.fitnessapp.base.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModelImpl: ViewModel() {
    protected val scope = CoroutineScope(Dispatchers.Main)
    protected val jobs: MutableList<Job> = mutableListOf()

    protected suspend fun exceptionHandleable(
        executionBlock: suspend () -> Unit,
        failureBlock: (suspend (error: String) -> Unit)? = null,
        completionBlock: (suspend () -> Unit)? = null
    ) {
        try {
            executionBlock()
        } catch (exception: Throwable) {
            if (exception is CancellationException) throw exception
            failureBlock?.invoke(exception.message ?: "")
        } finally {
            completionBlock?.invoke()
        }
    }

    open fun onViewShown() {}

    open fun onViewHidden() {
        clearJobs()
    }

    override fun onCleared() {
        clearJobs()
    }

    private fun clearJobs() {
        jobs.forEach {
            it.cancel()
        }
        jobs.clear()
    }
}
package com.example.fitnessapp.base.state

sealed class LoadingState {
    object Loading: LoadingState()
    object Success: LoadingState()
    class Error(val message: String): LoadingState()
}
package br.lavstaritaoclient.ui.home.states

sealed class HomeState {
    object Idle : HomeState()
    object Loading : HomeState()
    data class Success(val data: String) : HomeState()
    data class Error(val message: String) : HomeState()
}
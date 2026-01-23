package br.lavstaritaoclient.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.lavstaritaoclient.data.remoteRepository.RemoteRepositoryImpl
import br.lavstaritaoclient.ui.home.states.HomeState
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repository: RemoteRepositoryImpl
) : ViewModel() {

    var uiState by mutableStateOf<HomeState>(HomeState.Idle)

    fun getServiceInformation(){
        viewModelScope.launch {
            uiState = HomeState.Loading



        }
    }
}
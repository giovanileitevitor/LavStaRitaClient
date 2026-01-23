package br.lavstaritaoclient.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.lavstaritaoclient.data.localRepository.LocalRepository
import br.lavstaritaoclient.data.remoteRepository.RemoteRepositoryImpl
import br.lavstaritaoclient.ui.login.states.LoginState
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: RemoteRepositoryImpl,
    private val localRepository: LocalRepository
) : ViewModel() {

    var cpf by mutableStateOf("")
    var uiState by mutableStateOf<LoginState>(LoginState.Idle)

    fun checkCPForCNPJ() {
        if (cpf.isBlank()) return

        viewModelScope.launch {
            uiState = LoginState.Loading
            repository.checkDocumentExists(cpf).fold(
                onSuccess = { client ->
                    if (client != null) {
                        localRepository.saveUserData(client.name, cpf)
                        uiState = LoginState.Success("Bem-vindo, ${client.name} !")
                    } else {
                        uiState = LoginState.Error("Usuário não encontrado")
                    }
                },
                onFailure = { error ->
                    uiState = LoginState.Error("${error.message}")
                }
            )
        }
    }

    fun simulateFail(){
        throw RuntimeException("Simulated Crash for Firebase Crashlytics")
    }
}

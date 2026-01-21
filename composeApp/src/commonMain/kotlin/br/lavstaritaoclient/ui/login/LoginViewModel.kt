package br.lavstaritaoclient.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.lavstaritaoclient.ui.login.states.LoginState
import kotlinx.coroutines.launch
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class LoginViewModel(private val httpClient: HttpClient) : ViewModel() {

    var cpf by mutableStateOf("")
    var uiState by mutableStateOf<LoginState>(LoginState.Idle)

    fun checkCPForCNPJ() {
        if (cpf.isBlank()) return

        viewModelScope.launch {
            uiState = LoginState.Loading
            try {
                // Exemplo de GET: substitua pela sua URL real
                val response: HttpResponse = httpClient.get("https://sua-api.com/clientes/$cpf")

                if (response.status.value == 200) {
                    uiState = LoginState.Success("Bem-vindo!")
                } else {
                    uiState = LoginState.Error("Usuário não encontrado")
                }
            } catch (e: Exception) {
                uiState = LoginState.Error("Erro de conexão: ${e.message}")
            }
        }
    }
    
    fun simulateFail(){
        throw RuntimeException("Simulated Crash for Firebase Crashlytics")
    }
}

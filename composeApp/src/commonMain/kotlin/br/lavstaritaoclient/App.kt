package br.lavstaritaoclient

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import br.lavstaritaoclient.ui.login.LoginScreen
import br.lavstaritaoclient.ui.login.LoginViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val httpClient = remember {
            HttpClient {
                install(ContentNegotiation) {
                    json()
                }
            }
        }

        // Criando o ViewModel passando o httpClient necessário
        val viewModel = viewModel { LoginViewModel(httpClient) }

        LoginScreen(viewModel = viewModel)
    }
}

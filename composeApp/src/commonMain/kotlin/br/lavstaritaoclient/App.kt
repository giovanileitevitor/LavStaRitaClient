package br.lavstaritaoclient

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import br.lavstaritaoclient.data.localRepository.LocalRepository
import br.lavstaritaoclient.data.remoteRepository.RemoteRepositoryImpl
import br.lavstaritaoclient.ui.home.HomeScreen
import br.lavstaritaoclient.ui.home.HomeViewModel
import br.lavstaritaoclient.ui.login.LoginScreen
import br.lavstaritaoclient.ui.login.LoginViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

@Composable
fun App(localRepository: LocalRepository) {
    MaterialTheme {
        val httpClient = remember {
            HttpClient {
                install(ContentNegotiation) {
                    json()
                }
            }
        }

        val remoteRepository = remember { RemoteRepositoryImpl(httpClient) }
        var currentScreen by remember { mutableStateOf("login") }

        when (currentScreen) {
            "login" -> {
                val loginViewModel = viewModel { LoginViewModel(
                    remoteRepository,
                    localRepository
                ) }
                LoginScreen(
                    viewModel = loginViewModel,
                    onLoginSuccess = {
                        currentScreen = "home"
                    }
                )
            }
            "home" -> {
                val homeViewModel = viewModel { HomeViewModel(remoteRepository) }
                HomeScreen(viewModel = homeViewModel)
            }
        }
    }
}

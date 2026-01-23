package br.lavstaritaoclient

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import br.lavstaritaoclient.data.localRepository.LocalRepository
import br.lavstaritaoclient.data.localRepository.createDataStore

fun MainViewController() = ComposeUIViewController {
    val localRepository = remember {
        LocalRepository(createDataStore())
    }
    App(localRepository = localRepository)
}

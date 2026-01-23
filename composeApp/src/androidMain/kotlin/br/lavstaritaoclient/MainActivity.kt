package br.lavstaritaoclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import br.lavstaritaoclient.data.localRepository.LocalRepository
import br.lavstaritaoclient.data.localRepository.createDataStore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val localRepository = remember { 
                LocalRepository(createDataStore(applicationContext)) 
            }
            App(localRepository = localRepository)
        }
    }
}

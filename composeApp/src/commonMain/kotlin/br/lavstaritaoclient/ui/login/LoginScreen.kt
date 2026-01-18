package br.lavstaritaoclient.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.lavstaritaoclient.ui.login.states.LoginState
import lavstaritaclient.composeapp.generated.resources.Res
import lavstaritaclient.composeapp.generated.resources.img_lav
import org.jetbrains.compose.resources.painterResource


@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val azulEscuro = Color(0xFF2B4678)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(Res.drawable.img_lav),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(250.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = "Lavanderia Sta Rita",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = azulEscuro
        )

        Spacer(modifier = Modifier.height(32.dp))


        OutlinedTextField(
            value = viewModel.cpf,
            onValueChange = { newValue ->
                viewModel.cpf = newValue.filter { it.isDigit() }
            },
            label = { Text("Informe seu CPF / CNPJ ") },
            modifier = Modifier.fillMaxWidth(0.85f),
            trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.uiState is LoginState.Loading) {
            CircularProgressIndicator(color = azulEscuro)
        } else {
            Button(
                onClick = { viewModel.checkCPForCNPJ() },
                modifier = Modifier.fillMaxWidth(0.85f).height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = azulEscuro),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("CONSULTAR", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        // 5. Tratamento de Sucesso/Erro
        when (val state = viewModel.uiState) {
            is LoginState.Success -> Text("Sucesso!", color = Color.Green, modifier = Modifier.padding(8.dp))
            is LoginState.Error -> Text(state.message, color = Color.Red, modifier = Modifier.padding(8.dp))
            else -> {}
        }

        Spacer(modifier = Modifier.weight(1f))

        // 6. Rodapé Social
        Text("Saiba mais em:", color = azulEscuro, fontSize = 12.sp)
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Ícones fictícios - use Image ou Icon com seus vetores
            Text("FB", color = azulEscuro, fontWeight = FontWeight.Bold)
            Text("WA", color = azulEscuro, fontWeight = FontWeight.Bold)
            Text("IG", color = azulEscuro, fontWeight = FontWeight.Bold)
        }

        Text("Versão: 1.0.0", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.padding(bottom = 16.dp))
    }
}

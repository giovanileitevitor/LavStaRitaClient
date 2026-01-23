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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.lavstaritaoclient.APP_VERSION
import br.lavstaritaoclient.ui.login.states.LoginState
import br.lavstaritaoclient.utils.CpfCnpjVisualTransformation
import lavstaritaclient.composeapp.generated.resources.Res
import lavstaritaclient.composeapp.generated.resources.img_lav
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit
) {
    val uiState = viewModel.uiState

    LaunchedEffect(uiState) {
        if (uiState is LoginState.Success) {
            onLoginSuccess()
        }
    }

    val darkBlue = Color(0xFF2B4678)
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
            color = darkBlue
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = viewModel.cpf,
            onValueChange = { newValue ->
                val digits = newValue.filter { it.isDigit() }
                if (digits.length <= 14) {
                    viewModel.cpf = digits
                }
            },
            label = { Text("Informe seu CPF / CNPJ ") },
            modifier = Modifier.fillMaxWidth(0.85f),
            trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = CpfCnpjVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.uiState is LoginState.Loading) {
            CircularProgressIndicator(color = darkBlue)
        } else {
            Button(
                onClick = {
                    viewModel.checkCPForCNPJ()
                  },
                modifier = Modifier.fillMaxWidth(0.85f).height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = darkBlue),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("CONSULTAR", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        // Tratamento de Erro (Sucesso agora navega)
        if (uiState is LoginState.Error) {
            Text(uiState.message, color = Color.Red, modifier = Modifier.padding(8.dp))
        }

        Spacer(modifier = Modifier.weight(1f))
        Text("Saiba mais em:", color = darkBlue, fontSize = 12.sp)
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text("FB", color = darkBlue, fontWeight = FontWeight.Bold)
            Text("WA", color = darkBlue, fontWeight = FontWeight.Bold)
            Text("IG", color = darkBlue, fontWeight = FontWeight.Bold)
        }
        Text("Versão: $APP_VERSION", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.padding(bottom = 16.dp))
    }
}

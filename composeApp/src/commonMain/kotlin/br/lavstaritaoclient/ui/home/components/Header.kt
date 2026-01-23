package br.lavstaritaoclient.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Toolbar(badgeItems: String = "0"){
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Menu, contentDescription = null, tint = Color.White)
        BadgedBox(
            badge = { Badge { Text(badgeItems) } }
        ) {
            Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)
        }
    }
}

@Composable
fun Profile(name: String = "Charlotte",
            location: String = "Jalan Selamat, Singapore"){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.fillMaxSize().padding(10.dp), tint = Color.LightGray)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text("Charlotte", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Jalan Selamat, Singapore", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF2B4678)
@Composable
private fun PreviewToolbar(){
    Column {
        Toolbar("2")
        Profile()
    }

}
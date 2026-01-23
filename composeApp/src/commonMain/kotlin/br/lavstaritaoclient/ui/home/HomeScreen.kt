package br.lavstaritaoclient.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.lavstaritaoclient.ui.home.components.Profile
import br.lavstaritaoclient.ui.home.components.Toolbar
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val gradientColors = listOf(Color(0xFF4A90E2), Color(0xFF7B61FF))
    val lightGray = Color(0xFFF7F8FA)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightGray)
            .verticalScroll(rememberScrollState())
    ) {
        // --- HEADER ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(Brush.verticalGradient(gradientColors))
                .statusBarsPadding()
                .padding(horizontal = 20.dp)
        ) {
            Column {
                Toolbar(badgeItems = "2")
                Spacer(modifier = Modifier.height(20.dp))
                Profile()
            }

            // --- BALANCE CARD ---
            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = 60.dp)
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row {
                        Text("My Balance : ", color = Color.Gray)
                        Text("$1000", color = Color(0xFF4A90E2), fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ActionItem(Icons.Default.Download, "Drop-off")
                        ActionItem(Icons.Default.LocalShipping, "Pick up")
                        ActionItem(Icons.Default.ShoppingBag, "Shop")
                        ActionItem(Icons.Default.AccountBalanceWallet, "Top up")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(60.dp))

        // --- EXPLORE SECTION ---
        SectionHeader("Explore OLLO Life", "View All")
        
        val categories = listOf(
            CategoryData(Icons.Default.Checkroom, "Everyday Wear"),
            CategoryData(Icons.Default.DryCleaning, "T-Shirt"),
            CategoryData(Icons.Default.Store, "Jeans"),
            CategoryData(Icons.Default.Checkroom, "Long Dress")
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { category ->
                CategoryItem(category)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- ACTIVE ORDERS ---
        SectionHeader("Active Orders (2)", "Order History")
        
        OrderCard("#25644778", "Order Confirmed")
        OrderCard("#24744888", "Order Confirmed")
        OrderCard("#24744888", "Order Confirmed")
        OrderCard("#24744888", "Order Confirmed")

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun ActionItem(icon: ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(0xFFE8F1FF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = Color(0xFF4A90E2), modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, fontSize = 11.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun SectionHeader(title: String, actionText: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("Discover more things", fontSize = 11.sp, color = Color.Gray)
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A90E2)),
            shape = RoundedCornerShape(20.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
        ) {
            Text(actionText, fontSize = 11.sp)
        }
    }
}

@Composable
fun CategoryItem(data: CategoryData) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFE8EAF6)),
            contentAlignment = Alignment.Center
        ) {
            Icon(data.icon, contentDescription = null, modifier = Modifier.size(40.dp), tint = Color(0xFF4A90E2))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(data.name, fontSize = 11.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun OrderCard(orderNo: String, status: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8F1FF)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Inventory, contentDescription = null, tint = Color(0xFF4A90E2))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("Order No: $orderNo", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(status, color = Color(0xFF4A90E2), fontSize = 12.sp)
            }
        }
    }
}

data class CategoryData(val icon: ImageVector, val name: String)

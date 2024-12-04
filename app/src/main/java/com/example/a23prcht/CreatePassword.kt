package com.example.a23prcht

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CreatePassword(navController: NavController) {
    var password by remember { mutableStateOf("") }
    var activeButton by remember { mutableStateOf<String?>(null) }

    fun onNumberClick(number: String) {
        if (password.length < 4) {
            password += number
        }
        activeButton = number

        if (password.length == 4) {
            navController.navigate("CreateCard")
        }
    }

    fun onDeleteClick() {
        if (password.isNotEmpty()) {
            password = password.dropLast(1)
        }
        activeButton = null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Создайте пароль",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Для защиты ваших персональных данных",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(4) { index ->
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .background(
                            if (index < password.length) Color(0xFF1A6FEE) else Color.White,
                            CircleShape
                        )
                        .border(
                            2.dp,
                            if (index < password.length) Color(0xFF1A6FEE) else Color(0xFF1A6FEE),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (index < password.length) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(Color(0xFF1A6FEE), CircleShape) // Цвет точки
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val numbers = listOf(
                listOf("1", "2", "3"),
                listOf("4", "5", "6"),
                listOf("7", "8", "9"),
                listOf("0")
            )

            numbers.forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    row.forEach { number ->
                        Button(
                            onClick = { onNumberClick(number) },
                            modifier = Modifier.size(64.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (activeButton == number) Color(0xFF1A6FEE) else Color(0xFFE5E5E5)
                            )
                        ) {
                            Text(
                                text = number,
                                style = MaterialTheme.typography.titleLarge,
                                color = if (activeButton == number) Color.White else Color.Black
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onDeleteClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE5E5E5))
        ) {
            Text(text = "Удалить", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(32.dp))

        TextButton(
            onClick = {
                navController.navigate("CreateCard")
            }
        ) {
            Text(
                text = "Пропустить",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}







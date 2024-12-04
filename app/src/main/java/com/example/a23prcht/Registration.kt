package com.example.a23prcht

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun RegistrationScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }

    val isEmailValid = email.contains("@") && email.contains(".")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            text = "Добро пожаловать!",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.weight(0.1f))
        Text(
            text = "Войдите, чтобы пользоваться функциями приложения",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Вход по email",
            color = Color.Gray,
            style = MaterialTheme.typography.bodySmall
        )

        EmailInputField(value = email, onValueChange = { email = it })

        Button(
            onClick = {
                if (isEmailValid) {
                    navController.navigate("code_screen")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isEmailValid,
            colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
            ) {
            Text(text = "Далее")
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Или войдите с помощью",
            color = Color.Gray,
            style = MaterialTheme.typography.bodySmall
        )


        Button(
            onClick = { /* Логика для Яндекс входа */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Black, shape = MaterialTheme.shapes.small),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Text(text = "Войти с Яндекс")
        }
    }
}

@Composable
fun EmailInputField(value: String, onValueChange: (String) -> Unit) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions.Default.copy(

        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (value.isEmpty()) {
                    Text(text = "Введите email", color = Color.Gray)
                }
                innerTextField()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewRegistrationScreen() {
    val navController = rememberNavController()
    RegistrationScreen(navController)
}








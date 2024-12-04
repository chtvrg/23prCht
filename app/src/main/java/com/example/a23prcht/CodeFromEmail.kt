package com.example.a23prcht
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun CodeInputScreen(navController: NavController) {
    var code by remember { mutableStateOf("") }
    val timer = remember { mutableStateOf(59) }

    // Таймер для повторной отправки кода
    LaunchedEffect(Unit) {
        while (timer.value > 0) {
            kotlinx.coroutines.delay(1000)
            timer.value--
        }
    }

    LaunchedEffect(code) {
        if (code.length == 4) {
            navController.navigate("CreatePassword") // Переход на экран создания пароля
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Назад"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Введите код из E-mail",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(contentAlignment = Alignment.Center) {
            BasicTextField(
                value = code,
                onValueChange = {

                    if (it.length <= 4) {
                        code = it.filter { char -> char.isDigit() }
                    }
                    if (code.length == 4) {
                        navController.navigate("CreatePassword")
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                decorationBox = { innerTextField ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        repeat(4) { index ->
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .background(Color(0xFFF9F9F9), RoundedCornerShape(8.dp))
                                    .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                if (index < code.length) {
                                    Text(
                                        text = code[index].toString(),
                                        style = MaterialTheme.typography.headlineMedium,
                                        color = Color.Black
                                    )
                                }
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = if (timer.value > 0) {
                "Отправить код повторно можно будет через ${timer.value} секунд"
            } else {
                "Вы можете запросить код повторно"
            },
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

    }
}






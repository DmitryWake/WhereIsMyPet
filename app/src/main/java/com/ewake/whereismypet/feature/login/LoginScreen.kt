package com.ewake.whereismypet.feature.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author Nikolaevskiy Dmitriy
 */

@Composable
fun LoginScreen(onNavigateNext: () -> Unit) {
    PhoneLoginScreen(onNavigateNext)
}

@Composable
private fun PhoneLoginScreen(onNavigateNext: () -> Unit) {

    var phone by remember {
        mutableStateOf("+7 (")
    }

    Column {
        Text(
            text = "Введите номер вашего мобильного телефона",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 275.dp, start = 50.dp, end = 50.dp)
                .align(CenterHorizontally),
            fontSize = 30.sp
        )

        TextField(
            value = phone,
            onValueChange = {
                phone = it
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .align(CenterHorizontally)
        )

        Button(
            onClick = onNavigateNext,
            modifier = Modifier
                .padding(top = 16.dp)
                .align(CenterHorizontally)
        ) {
            Text(text = "Продолжить")
        }
    }
}
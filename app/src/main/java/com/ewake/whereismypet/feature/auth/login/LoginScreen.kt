package com.ewake.whereismypet.feature.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ewake.whereismypet.core.ui.items.CodeEnterField
import com.ewake.whereismypet.feature.auth.login.viewmodel.LoginScreenUiState
import com.ewake.whereismypet.feature.auth.login.viewmodel.LoginViewModel

/**
 * @author Nikolaevskiy Dmitriy
 */

@ExperimentalLifecycleComposeApi
@Composable
fun LoginScreen(onNavigateNext: (Boolean) -> Unit, viewModel: LoginViewModel = hiltViewModel()) {
    val uiState: LoginScreenUiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is LoginScreenUiState.EnterCode -> {
            EnterCodeScreen(
                phoneCodeLength = LoginScreenUiState.EnterCode.PHONE_CODE_LENGTH,
                onNextClicked = { viewModel.sendCode(it) })
        }
        is LoginScreenUiState.EnterPhone -> {
            PhoneLoginScreen(onPhoneEntered = viewModel::sendPhoneNumber)
        }
        LoginScreenUiState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        }
        is LoginScreenUiState.Navigating -> onNavigateNext.invoke((uiState as LoginScreenUiState.Navigating).isFirstLogin)
    }
}

@Composable
private fun PhoneLoginScreen(onPhoneEntered: (String) -> Unit) {

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
                .align(CenterHorizontally),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        Button(
            onClick = {
                onPhoneEntered.invoke(phone)
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .align(CenterHorizontally)
        ) {
            Text(text = "Продолжить")
        }
    }
}

@Composable
private fun EnterCodeScreen(onNextClicked: (String) -> Unit, phoneCodeLength: Int) {

    var code by remember {
        mutableStateOf("")
    }

    Column {
        Text(
            text = "Введите код, отправленный на введный вами номер",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 275.dp, start = 50.dp, end = 50.dp)
                .align(CenterHorizontally),
            fontSize = 30.sp
        )

        CodeEnterField(
            modifier = Modifier
                .padding(top = 16.dp)
                .align(CenterHorizontally),
            onValueChanged = {
                code = it
            },
            onAfterAllCodeEntered = {
                onNextClicked.invoke(it)
            },
            size = phoneCodeLength
        )

        Button(
            onClick = {
                onNextClicked.invoke(code)
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .align(CenterHorizontally)
        ) {
            Text(text = "Продолжить")
        }
    }
}
package com.ewake.whereismypet.feature.auth.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ewake.whereismypet.core.model.RegisterModel
import com.ewake.whereismypet.feature.auth.register.viewmodel.RegisterScreenUiState
import com.ewake.whereismypet.feature.auth.register.viewmodel.RegisterViewModel

/**
 * @author Nikolaevskiy Dmitriy
 */

@ExperimentalLifecycleComposeApi
@Composable
fun RegisterScreen(onNavigateNext: () -> Unit, viewModel: RegisterViewModel = hiltViewModel()) {
    val uiState: RegisterScreenUiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is RegisterScreenUiState.Default -> {
            RegisterFields(
                onButtonClicked = viewModel::onContinueClicked,
                isLoading = false,
                error = (uiState as RegisterScreenUiState.Default).error
            )
        }
        RegisterScreenUiState.Loading -> {
            RegisterFields(onButtonClicked = viewModel::onContinueClicked, isLoading = true)
        }
        RegisterScreenUiState.Navigation -> onNavigateNext.invoke()
    }
}

@Composable
private fun RegisterFields(onButtonClicked: (RegisterModel) -> Unit, isLoading: Boolean, error: String? = null) {
    var lastName by remember {
        mutableStateOf("")
    }
    var firstName by remember {
        mutableStateOf("")
    }
    var additionalName by remember {
        mutableStateOf("")
    }

    Column {
        error?.let {
            Text(
                text = it,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 12.dp),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                color = Color.Red
            )
        }

        Text(
            text = "Регистрация",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 50.dp),
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )

        TextField(
            value = lastName,
            onValueChange = {
                lastName = it
            },
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Фамилия") },
            placeholder = { Text(text = "Фамилия") },
            enabled = !isLoading
        )

        TextField(
            value = firstName,
            onValueChange = {
                firstName = it
            },
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Имя") },
            placeholder = { Text(text = "Имя") },
            enabled = !isLoading
        )

        TextField(
            value = additionalName,
            onValueChange = {
                additionalName = it
            },
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Отчество") },
            placeholder = { Text(text = "Отчество") },
            enabled = !isLoading
        )

        Button(
            onClick = {
                onButtonClicked.invoke(
                    RegisterModel(
                        lastName = lastName,
                        firstName = firstName,
                        additionalName = additionalName
                    )
                )
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally),
            enabled = !isLoading
        ) {
            Text(text = "Продолжить")
        }
    }

    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .padding(horizontal = 100.dp)
        )
    }
}
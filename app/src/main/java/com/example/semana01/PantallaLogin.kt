package com.example.semana01


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.semana01.modelo.Usuario
import kotlin.math.sin


@Preview(showBackground = true, name = "Prueba")
@Composable
fun PantallaLogin() {
    val showLoginForm = rememberSaveable { mutableStateOf(true) }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (showLoginForm.value) {
                Text(text = "Iniciar Sesión")
                UserForm(
                    isCreateAccount = false
                ) { email, pass ->
                    Log.d("Proy", "Logueado con: $email y $pass")
                }
            } else {
                Text(text = "Crea una cuenta")
                UserForm(isCreateAccount = true)
                { email, pass ->
                    Log.d("Proy", "Cuenta creada con: $email y $pass")
                    val usuario = Usuario(email, pass)
                    Usuario.agregarUsuario(usuario)
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                val text1 = if (showLoginForm.value) "¿No tienes cuenta?"
                else "¿Ya tienes cuenta?"

                val text2 = if (showLoginForm.value) "Regístrate"
                else "Inicia Sesión"

                Text(text = text1)
                Text(text = text2,
                    modifier = Modifier
                        .clickable{showLoginForm.value = !showLoginForm.value}
                        .padding(start = 5.dp),
                    color = MaterialTheme.colors.secondaryVariant)
            }
        }
    }
}


@Composable
fun UserForm(isCreateAccount: Boolean = false, onDone: (String, String) -> Unit = {email, pass ->}) {
    val email = rememberSaveable() { mutableStateOf("") }
    val pass = rememberSaveable() { mutableStateOf("") }
    val passVisible = rememberSaveable() { mutableStateOf(false) }
    val valido = remember(email.value, pass.value) {
        email.value.trim().isNotEmpty() && pass.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        EmailInput(
            emailState = email
        )
        PassInput(
            passState = pass,
            labelID = "Password",
            passVisible = passVisible
        )
        SubmitButton(
            textId = if(isCreateAccount) "Crear Cuenta" else "Login",
            inputValido = valido
        ){
            onDone(email.value.trim(), pass.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
fun SubmitButton(textId: String, inputValido: Boolean, onClic: ()->Unit) {
    Button(onClick = onClic,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = inputValido
    ) {
        Text(text = textId,
            modifier = Modifier
                .padding(5.dp))
    }

}

@Composable
fun PassInput(passState: MutableState<String>, labelID: String, passVisible: MutableState<Boolean>) {
    val visualTransformation = if (passVisible.value)
        VisualTransformation.None
    else PasswordVisualTransformation()
    OutlinedTextField(
        value = passState.value,
        onValueChange = { passState.value = it },
        label = { Text(text = labelID) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (passState.value.isNotBlank()) {
                PasswordVisibleIcon(passVisible)
            } else null
        }
    )
}

@Composable
fun PasswordVisibleIcon(passVisible: MutableState<Boolean>) {
    val image =
        if (passVisible.value)
            Icons.Default.VisibilityOff
        else
            Icons.Default.Visibility

    IconButton(onClick = {
        passVisible.value = !passVisible.value
    }) {
        Icon(
            imageVector = image,
            contentDescription = ""
        )
    }
}

@Composable
fun EmailInput(emailState: MutableState<String>,
               labelID: String = "Email") = InputField(
                   valueState = emailState,
                   labelID = labelID,
                   keyboardType = KeyboardType.Email
               )

@Composable
fun InputField(valueState: MutableState<String>, labelID: String, isSingleLine: Boolean = true, keyboardType: KeyboardType) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelID) },
        singleLine = isSingleLine,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )

}
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


@Preview(showBackground = true, name = "Prueba Recuperar Pass")
@Composable
fun PantallaRecuperar() {
    val showLoginForm = rememberSaveable { mutableStateOf(true) }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (showLoginForm.value) {
                Text(text = "Recuperar Contraseña")
                UserFormRecuperar(
                    isCreateAccount = false
                ){
                    email ->
                    Log.d("Proy", "Mail ingresado a buscar: $email")
                    val UsuarioEncontrado = Usuario.obtUsuarioMail(email)
                    if(UsuarioEncontrado != null){
                        Log.d("Proy", "Usuario encontrado")
                    }
                    else
                    {
                        Log.d("Proy", "Usuario NO encontrado")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Composable
fun UserFormRecuperar(isCreateAccount: Boolean = false, onDone: (String) -> Unit = {email ->}) {
    val email = rememberSaveable() { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valido = remember(email.value) {
        email.value.trim().isNotEmpty()
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        EmailInputRecuperar(
            emailState = email
        )
        SubmitButtonRecuperar(
            textId = "Recuperar",
            inputValido = valido
        ){
            onDone(email.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
fun SubmitButtonRecuperar(textId: String, inputValido: Boolean, onClic: ()->Unit) {
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
fun EmailInputRecuperar(emailState: MutableState<String>,
               labelID: String = "Email") = InputFieldRecuperar(
    valueState = emailState,
    labelID = labelID,
    keyboardType = KeyboardType.Email
)

@Composable
fun InputFieldRecuperar(valueState: MutableState<String>, labelID: String, isSingleLine: Boolean = true, keyboardType: KeyboardType) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelID) },
        singleLine = isSingleLine,
        modifier = Modifier
            .padding(bottom = 50.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )

}


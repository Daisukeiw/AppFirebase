package com.example.appfirebase.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp // Ícone de logout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appfirebase.AuthState
import com.example.appfirebase.AuthViewModel

@Composable
fun HomePage(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {

    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), // Adiciona padding geral na tela
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home Page",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold, // Deixa o título em negrito
            color = MaterialTheme.colorScheme.primary // Usa a cor primária do tema
        )

        Spacer(modifier = Modifier.height(24.dp)) // Adiciona espaço entre o título e o botão

        Button( // Usa Button para mais destaque
            onClick = {
                authViewModel.signout()
            }
        ) {
            Icon(
                imageVector = Icons.Filled.ExitToApp, // Ícone de logout
                contentDescription = "Sign out icon",
                modifier = Modifier.size(ButtonDefaults.IconSize) // Tamanho padrão do ícone do botão
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing)) // Espaço entre o ícone e o texto
            Text(text = "Sign out")
        }
    }
}
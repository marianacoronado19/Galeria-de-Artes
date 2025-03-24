package com.example.galeriadeartes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galeriadeartes.ui.theme.GaleriaDeArtesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GaleriaDeArtesTheme {
                GaleriaApp()
            }
        }
    }
}

data class ObraDeArte(val imageRes: Int, val title: String, val author: String, val year: Int, val description: String)

@Composable
fun GaleriaApp(modifier: Modifier = Modifier // ajustar tamanho de tela
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)
    .padding(top = 25.dp)
) {
    var click by remember { mutableStateOf(1)}
    val obra = when(click){ // variável com when para quando click for determinado número
        1 -> ObraDeArte(
            R.drawable.mona_lisa,
            stringResource(R.string.mona_lisa),
            stringResource(R.string.leonardo_da_vinci),
            1506,
            stringResource(R.string.desc_mona_lisa)
        )
        2 -> ObraDeArte(
            R.drawable.noite_estrelada,
            stringResource(R.string.a_noite_estrelada),
            stringResource(R.string.vincent_van_gogh),
            1889,
            stringResource(R.string.desc_noite_estrelada)
        )
        3 -> ObraDeArte(
            R.drawable.abaporu,
            stringResource(R.string.abaporu),
            stringResource(R.string.tarsila_do_amaral),
            1928,
            stringResource(R.string.desc_abaporu)
        )
        4 -> ObraDeArte(
            R.drawable.criacao_de_adao,
            stringResource(R.string.criacao_de_adao),
            stringResource(R.string.michelangelo_buonarroti),
            1512,
            stringResource(R.string.desc_criacao_de_adao)
        )
        else -> ObraDeArte(
            R.drawable.nascimento_de_venus,
            stringResource(R.string.o_nascimento_de_venus),
            "Sandro Botticelli",
            1483,
            "O Nascimento de Vênus de Sandro Botticelli foi pintado em 1483 em Têmpera sobre tela."
        )
    }

    fun nextImage(){
        if (click >= 5){
            click = 1
        } else {
            click++
        }
    }
    fun lastImage(){
        if(click <= 1) {
            click = 5
        } else {
            click--
        }
    }
    Column( // Coluna principal, todos os elementos são organizados aqui dentro
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(obra.imageRes),
            contentDescription = obra.description,
            modifier = Modifier
                .size(350.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(16.dp)
                .width(250.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = obra.title,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "${obra.author}, ${obra.year}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Button(onClick = { lastImage() }){
                Text(text = stringResource(R.string.voltar))
            }
            Button(onClick = { nextImage() }) {
                Text(text = stringResource(R.string.proximo))
            }
        }
    }

}



@Preview(showBackground = true)
@Composable
fun GaleriaPreview() {
    GaleriaApp()
}

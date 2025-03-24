package com.example.galeriadeartes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
) {
    var click by remember { mutableStateOf(1)}
    val obra = when(click){
        1 -> ObraDeArte(
            R.drawable.mona_lisa,
            "Mona Lisa",
            "Leonardo da Vinci",
            1506,
            "Mona Lisa de Leonardo da Vinci foi pintada entre 1503-1506 a Óleo sobre madeira de álamo.")
        2 -> ObraDeArte(
            R.drawable.noite_estrelada,
            "A Noite Estrelada",
            "Vincent van Gogh",
            1889,
            "A Noite Estrelada de Vincent Van Gogh foi pintada em 1889 em Óleo sobre tela."
        )
        3 -> ObraDeArte(
            R.drawable.abaporu,
            "Abaporu",
            "Tarsila do Amaral",
            1928,
            "O Abaporu de Tarsila do Amaral foi pintado em 1928 em Óleo sobre tela."
        )
        4 -> ObraDeArte(
            R.drawable.criacao_de_adao,
            "A Criação de Adão",
            "Michelangelo Buonarroti",
            1512,
            "A Criação de Adão de Michelangelo foi pintada no teto da Capela Sistina, Roma entre 1508-1512."
        )
        else -> ObraDeArte(
            R.drawable.nascimento_de_venus,
            "O Nascimento de Vênus",
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
            click = 0
        } else {
            click--
        }
    }

    Column( // Coluna principal, todos os elementos são organizados aqui dentro
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF6750A4))
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Galeria de Artes",
                fontSize = 20.sp,
                color = Color.White,
            )
        }

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
                Text(text = "Voltar")
            }
            Button(onClick = { nextImage() }) {
                Text(text = "Próximo")
            }
        }
    }

}



@Preview(showBackground = true)
@Composable
fun GaleriaPreview() {
    GaleriaApp()
}

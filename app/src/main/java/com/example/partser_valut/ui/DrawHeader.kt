package com.example.partser_valut.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getColor
import com.example.partser_valut.R
import com.example.partser_valut.domain.Cb



@Composable
fun DrawHeader( listCb: Cb?,context: Context) {

    Column (modifier = Modifier.fillMaxWidth().background(color = Color(getColor(context,R.color.blue)))
    ) {

        Text(text = listCb?.stringCb?: "", textAlign = TextAlign.Center, fontSize = 24.sp, fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic, modifier = Modifier.fillMaxWidth().padding(14.dp)

        )

        Row (modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp), horizontalArrangement = Arrangement.SpaceAround) {
            with(listCb) { DrawItemBlock(this?.usdCb,"usd")
                DrawItemBlock(this?.eurCb,"eur")
                DrawItemBlock(this?.cnyCb,"cny")
            }

}}}

@Composable
fun DrawItemBlock(value: String?, text: String) {

    Column (horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = text, color = Color.Blue)
        Text(text = value ?: "", fontSize = 18.sp, fontWeight = FontWeight.Bold)

    }
}













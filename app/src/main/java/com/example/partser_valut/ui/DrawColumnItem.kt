package com.example.partser_valut.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partser_valut.domain.Bank


@Composable
fun DrawColumnItem(bank: Bank) {

    Column  (modifier = Modifier.fillMaxWidth().padding(4.dp).background(color = Color.LightGray)
    ) {

        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

            Image(painter = painterResource(bank.image), contentDescription = "", modifier = Modifier.size(58.dp)
                .padding( top = 8.dp, start = 8.dp), contentScale = ContentScale.Crop)
            Text(text = bank.name, fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }

        Row (modifier = Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.SpaceAround) {
            Text(text = "USD", color = Color.Blue)
            Text(text = "EUR", color = Color.Blue)
            Text(text = "CNY", color = Color.Blue)
        }

        Row (modifier = Modifier.fillMaxWidth().padding(top = 6.dp, bottom = 6.dp), horizontalArrangement = Arrangement.SpaceAround){
            Text(text = "${bank.usdBY} / ${bank.usdSell}", fontSize = 16.sp,modifier = Modifier.sizeIn(70.dp))
            Text(text = "${bank.eurBy} / ${bank.eurSell}", fontSize = 16.sp,modifier = Modifier.sizeIn(70.dp))
            Text(text = "${bank.cnyBY} / ${bank.cnySell}", fontSize = 16.sp, modifier = Modifier.sizeIn(70.dp)) }

        Text(text = bank.upDate, fontStyle = FontStyle.Italic, modifier = Modifier.fillMaxWidth().padding(8.dp), textAlign = TextAlign.Center) }



}
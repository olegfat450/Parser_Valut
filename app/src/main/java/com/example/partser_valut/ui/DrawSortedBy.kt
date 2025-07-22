package com.example.partser_valut.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.partser_valut.R

@Composable
fun DrawSortedBy(top: Dp, onClick: (Pair<String,String>) -> Unit) {




    Row (modifier = Modifier.padding(top = 96.dp + top).fillMaxWidth().height(60.dp).background(color = Color.Black),
        horizontalArrangement = Arrangement.Absolute.SpaceAround, verticalAlignment = Alignment.CenterVertically)

    {  DrawItemBlock("usd") { onClick(Pair(it.first,"usd${it.second}"))}
        DrawItemBlock("eur") { onClick(Pair(it.first,"eur${it.second}"))}
        DrawItemBlock("cny")  {onClick(Pair(it.first,"cny${it.second}"))}}
}

@Composable
fun DrawItemBlock(valut: String,onClick: (Pair<String,String>) -> Unit) {
    Row(modifier = Modifier.padding(start = 0.dp)) {


        DrawItem() { onClick(Pair(it,"By"))}
        DrawItem() { onClick(Pair(it,"Sell"))}


    }
}

@Composable
fun DrawItem(onClick:(String) -> Unit) {
      Column() {
        Image(
            painter = painterResource(R.drawable.up),
            contentDescription = "",
            modifier = Modifier.clickable { onClick("Up") }
        )



        Image(painter = painterResource(R.drawable.down), contentDescription = "", modifier = Modifier.clickable { onClick("Down") })
    }
}

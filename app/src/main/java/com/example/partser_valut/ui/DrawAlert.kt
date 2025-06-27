package com.example.partser_valut.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.finishAffinity
import org.jsoup.Connection.Request


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DrawAlert(message: String, onDismissRequest: () -> Unit, onExit: () -> Unit, onRepeat: () -> Unit) {
    BasicAlertDialog(onDismissRequest = { onDismissRequest() }, content = {
        Column ( modifier = Modifier.background(color = Color.LightGray, shape = RoundedCornerShape(8.dp)).heightIn(300.dp), verticalArrangement = Arrangement.SpaceAround) {


            Text(text = "ОШИБКА", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 26.sp)
            Text(text = message, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, color = Color.Red)

            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {

                Button(onClick = {onExit()}) { Text(text = "Выход") }
                Button(onClick = {onRepeat()}) {  Text(text = "Повторить") }
            }


        }
    })

}
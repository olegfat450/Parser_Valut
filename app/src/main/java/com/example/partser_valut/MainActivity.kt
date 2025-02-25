package com.example.partser_valut

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partser_valut.ui.theme.Partser_ValutTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class MainActivity : ComponentActivity() {

           var listBank: MutableState<List<Bank>> = mutableStateOf(emptyList())
          var indicatorSize = mutableStateOf(84.dp)

    var stringCb = ""
    var usdCb = ""
    var eurCb = ""
    var cnyCb = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getList()


        setContent {
            Strart() }
    }



    @Composable
fun  Strart() {

         Column (modifier = Modifier.fillMaxWidth().background(color = Color(getColor(R.color.blue))).pointerInput (Unit){
             detectTapGestures ( onLongPress = { getList() }) }) {

             Text(text = stringCb, textAlign = TextAlign.Center, fontSize = 24.sp, fontWeight = FontWeight.Bold,
                 fontStyle = FontStyle.Italic, modifier = Modifier.fillMaxWidth().padding(16.dp)

             )

             Row (modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp), horizontalArrangement = Arrangement.SpaceAround) {
                 Text(text = usdCb, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                 Text(text = eurCb, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                 Text(text = cnyCb, fontSize = 18.sp, fontWeight = FontWeight.Bold) } }


        Row (modifier = Modifier.padding(top = 96.dp).fillMaxWidth().height(60.dp).background(color = Color.Black),
            horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically)

        { Head() }




           Box( modifier = Modifier.fillMaxSize().padding(top = 156.dp).background(color = Color(getColor(R.color.blue))), contentAlignment = Alignment.Center) {

               CircularProgressIndicator(modifier = Modifier.size(indicatorSize.value))


        LazyColumn () {

            items(listBank.value) {

                Column  (modifier = Modifier.fillMaxWidth().padding(4.dp).background(color = Color.LightGray)
                ) {

            Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

                     Image(painter = painterResource(it.image), contentDescription = "", modifier = Modifier.size(58.dp)
                         .padding( top = 8.dp, start = 8.dp), contentScale = ContentScale.Crop)
                    Text(text = it.name, fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)}

                    Row (modifier = Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.SpaceAround) {
                        Text(text = "USD", color = Color.Blue)
                        Text(text = "EUR", color = Color.Blue)
                        Text(text = "CNY", color = Color.Blue)
                    }

                    Row (modifier = Modifier.fillMaxWidth().padding(top = 6.dp, bottom = 6.dp), horizontalArrangement = Arrangement.SpaceAround){
                        Text(text = "${it.usdBY} / ${it.usdSell}", fontSize = 16.sp,modifier = Modifier.sizeIn(70.dp))
                        Text(text = "${it.eurBy} / ${it.eurSell}", fontSize = 16.sp,modifier = Modifier.sizeIn(70.dp))
                        Text(text = "${it.cnyBY} / ${it.cnySell}", fontSize = 16.sp, modifier = Modifier.sizeIn(70.dp)) }

                    Text(text = it.upDate, fontStyle = FontStyle.Italic, modifier = Modifier.fillMaxWidth().padding(8.dp), textAlign = TextAlign.Center) } } }
           }



}

    @Composable
    private fun Head() {

        val mod = Modifier.size(32.dp)

        Row(modifier = Modifier.padding(start = 32.dp)) {
            Column() {
                Image(painter = painterResource(R.drawable.up),
                    contentDescription = "",
                    modifier = mod.clickable(onClick = {
                            listBank.value = listBank.value.sortedBy { it.usdBY }
                        })
                )
                Image(painter = painterResource(R.drawable.down),
                    contentDescription = "",
                    modifier = mod
                        .clickable(onClick = {
                            listBank.value = listBank.value.sortedBy { it.usdBY }.reversed()
                        })
                )
            }

            Column {
                Image(painter = painterResource(R.drawable.up),
                    contentDescription = "",
                    modifier = mod
                        .clickable(onClick = {
                            listBank.value = listBank.value.sortedBy { it.usdSell }
                        })
                )
                Image(painter = painterResource(R.drawable.down),
                    contentDescription = "",
                    modifier = mod
                        .clickable(onClick = {
                            listBank.value = listBank.value.sortedBy { it.usdSell }.reversed()
                        })
                )
            }
        }

        Row {
            Column() {
                Image(painter = painterResource(R.drawable.up),
                    contentDescription = "",
                    modifier = mod
                        .clickable(onClick = {
                            listBank.value = listBank.value.sortedBy { it.eurBy }
                        })
                )
                Image(painter = painterResource(R.drawable.down),
                    contentDescription = "",
                    modifier = mod
                        .clickable(onClick = {
                            listBank.value = listBank.value.sortedBy { it.eurBy }.reversed()
                        })
                )
            }

            Column {
                Image(painter = painterResource(R.drawable.up),
                    contentDescription = "",
                    modifier = mod
                        .clickable(onClick = {
                            listBank.value = listBank.value.sortedBy { it.eurSell }
                        })
                )
                Image(painter = painterResource(R.drawable.down),
                    contentDescription = "",
                    modifier = mod
                        .clickable(onClick = {
                            listBank.value = listBank.value.sortedBy { it.eurSell }.reversed()
                        })
                )
            }
        }


        Row(modifier = Modifier.padding(end = 32.dp)) {
            Column() {
                Image(painter = painterResource(R.drawable.up),
                    contentDescription = "",
                    modifier = mod
                        .clickable(onClick = {
                            listBank.value = listBank.value.sortedBy { it.cnyBY }
                        })
                )
                Image(painter = painterResource(R.drawable.down),
                    contentDescription = "",
                    modifier = mod
                        .clickable(onClick = {
                            listBank.value = listBank.value.sortedBy { it.cnyBY }.reversed()
                        })
                )
            }

            Column {
                Image(painter = painterResource(R.drawable.up),
                    contentDescription = "",
                    modifier = mod
                        .clickable(onClick = {
                            listBank.value = listBank.value.sortedBy { it.cnySell }
                        })
                )
                Image(painter = painterResource(R.drawable.down),
                    contentDescription = "",
                    modifier = mod
                        .clickable(onClick = {
                            listBank.value = listBank.value.sortedBy { it.cnySell }.reversed()
                        })
                )
            }
        }
    }

    fun  getList() {

         indicatorSize.value = 84.dp
        listBank.value = emptyList();

        CoroutineScope(Dispatchers.IO).launch {
        var arrayV: Array<List<String>> = arrayOf()
        val base = Base.base
            var list: List<String> = listOf()
        try {
           val doc = Jsoup.connect("https://kovalut.ru/kurs/krasnojarsk").get()
            val table: Elements = doc.getElementsByClass("trow")
            val tableCb: Elements = doc.getElementsByClass("col-span-1 grid grid-cols-1 gap-2")
            table.forEach { list += (it.children().text()) }
           list.forEach { arrayV += it.split(" ") }


            arrayV.forEach {
                var price: Array<Double> = arrayOf()

                val s1 = it.takeWhile { it != "Покупка" }

                val s = s1.takeWhile { it != "Обновление:" }

                val s3 = s1 - s
                var upDate = ""
                s3.forEach { upDate += "${it} " }
              //  Log.d("MyLog", upDate.toString())


                var name =""
                s.forEach { name += " ${it} "  }
                var image: Int = R.drawable.ic_launcher_background

                base.forEach { if(name.uppercase().contains(it.bank.uppercase())) image = it.image  }


                for ( i in it.indices ) { if ((it[i] == "Покупка") or (it[i] == "Продажа")) {

                  try { price += it[i+1].toDouble() } catch (_:Exception ) { price += 0.0 }
                }}

                listBank.value += Bank(name,price[0],price[1],price[2],price[3],price[4],price[5],image,upDate)

            }

             stringCb = tableCb.get(0).child(0).text() + " " +tableCb.get(0).child(1).text()
             usdCb = tableCb.get(0).child(3).text()
             eurCb = tableCb.get(0).child(5).text()
             cnyCb = tableCb.get(0).child(7).text()
                 indicatorSize.value = 0.dp

                      Log.d("Mylog",usdCb + " " + eurCb + " " + cnyCb )
            //  listValut.value = arrayV
        } catch (_: Exception) {
            Log.d("MyLog", "ERROR") }
        }



}}


















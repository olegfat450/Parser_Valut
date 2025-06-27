package com.example.partser_valut.data

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.unit.dp
import com.example.partser_valut.R
import com.example.partser_valut.domain.Bank

import com.example.partser_valut.domain.BankCb
import com.example.partser_valut.domain.Base
import com.example.partser_valut.domain.Cb
import org.jsoup.Jsoup
import org.jsoup.select.Elements

fun getList(context: Context): BankCb? {
   var stringCb = ""
   var usdCb = ""
   var eurCb = ""
   var cnyCb = ""


    var id = 1

  //  indicatorSize.value = 84.dp

    //  listBank.value = emptyList()

    var listB: List<Bank> = mutableListOf()

    //  CoroutineScope(Dispatchers.IO).launch {
    var arrayV: Array<List<String>> = arrayOf()
    val base = Base.base
    var list: List<String> = listOf()
  //  try {
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



            var name =""
            s.forEach { name += " ${it} ".replace(Regex("[.\\d]"),"") }

            var image: Int = R.drawable.ic_launcher_background

            base.forEach { if(name.uppercase().contains(it.bank.uppercase())) image = it.image  }


            for ( i in it.indices ) { if ((it[i] == "Покупка") or (it[i] == "Продажа")) {

                try { price += it[i+1].toDouble() } catch (_:Exception ) { price += 0.0 }
            }}


            val b = Bank(id = id++, name, price[0], price[1], price[2], price[3], price[4], price[5], image, upDate) ?: null

            b?.let { listB = listB.plus(b) }
//
//            Log.d("Ml","$b")
//            Log.d("Ml","${listB}")
        }

       stringCb = tableCb.get(0).child(0).text() + " " +tableCb.get(0).child(1).text()
        usdCb = tableCb.get(0).child(3).text()
        eurCb = tableCb.get(0).child(5).text()
       cnyCb = tableCb.get(0).child(7).text()


    return BankCb(listB, Cb(stringCb,usdCb,eurCb,cnyCb))


}



package com.example.partser_valut.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.partser_valut.R
import com.example.partser_valut.data.getList
import com.example.partser_valut.domain.Bank
import com.example.partser_valut.domain.Cb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import com.example.partser_valut.domain.BankCb

class MainActivity : ComponentActivity() {


      //  private  var listBank: MutableState<List<Bank?>?>? = null
             var listBank by mutableStateOf<List<Bank?>?>(null)
      var listCb by mutableStateOf<Cb?>(null)
        var stsrt = true
        var alert by mutableStateOf(false)
   // var alert = false
          var indicatorSize by mutableStateOf(0.dp)
          var errorMeesage = ""
         //   var indicatorSize = 0.dp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getBanks()

        setContent {

              DrawHeader(context = this@MainActivity, listCb = listCb)


            DrawSortedBy() { sorted(it) }
             Start() { getBanks() } }

    }



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
fun  Start(onRefresh: () -> Unit) {

   //  listBank = remember { mutableStateOf<List<Bank?>?>(null) }


        val listState = rememberLazyListState()
         val scope = rememberCoroutineScope()

        PullToRefreshBox(onRefresh = {onRefresh()}, isRefreshing = false) {

           Box( modifier = Modifier.fillMaxSize().padding(top = 156.dp).background(color = Color(getColor(
               R.color.blue
           ))), contentAlignment = Alignment.Center) {

          CircularProgressIndicator(modifier = Modifier.size(indicatorSize))

        LazyColumn (state = listState) {

            listBank?.let {

            items(listBank!!,key = {it!!.id}) { DrawColumnItem(it!!) }

            }
            scope.launch {  listState.animateScrollToItem(0) }
        } }
        }
           if (alert) {

               DrawAlert(message = errorMeesage, { alert = false },{ finishAffinity() }, { alert = false; getBanks() } )

           }


}

   private fun getBanks() {
       var list: BankCb? = null
       listBank = null
       indicatorSize = 100.dp
       CoroutineScope(Dispatchers.IO).launch {



        try {

            list = getList(this@MainActivity) } catch (e:Exception) {

                errorMeesage = e.message.toString()
              alert = true
                //runOnUiThread { Toast.makeText(applicationContext,"$e",Toast.LENGTH_LONG).show()

       //         }
       }



           list?.let {
        listCb = it.cb; listBank = it.banks
    }
       indicatorSize = 0.dp
   }


   }




    private fun sorted(valut: Pair<String, String>) {
        when(valut.second) {

            "usdBy" -> { listBank = listBank?.sortedBy { it?.usdBY } }
            "usdSell" -> { listBank = listBank?.sortedBy { it?.usdSell } }
            "eurBy" -> { listBank = listBank?.sortedBy { it?.eurBy} }
            "eurSell" -> { listBank = listBank?.sortedBy { it?.eurSell }  }
            "cnyBy" -> { listBank = listBank?.sortedBy { it?.cnyBY }; refresh() }


            "cnySell" -> { listBank = listBank?.sortedBy { it?.cnySell }; refresh() }
        }
        if (valut.first == "Down") { listBank = listBank?.reversed(); refresh()}

    }

    fun refresh() {

        val l = listBank?.takeWhile { it?.cnyBY == 0.0 }
        l?.let { listBank = listBank?.drop(l.size); listBank = listBank?.plus(l) }

    }


}


















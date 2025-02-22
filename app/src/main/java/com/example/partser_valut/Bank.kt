package com.example.partser_valut

data class Bank (val name: String,val usdBY: Double = 0.0,val usdSell: Double = 0.0,
                 val eurBy: Double = 0.0,val eurSell: Double = 0.0,val cnyBY: Double = 0.0,val cnySell: Double = 0.0,
    val image: Int,val upDate: String = "") {
}
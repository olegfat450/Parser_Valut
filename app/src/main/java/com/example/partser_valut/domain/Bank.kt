package com.example.partser_valut.domain

import androidx.compose.runtime.MutableState


data class BankCb(var banks: List<Bank?>, val cb: Cb?)

data class Bank (val id: Int,val name: String,val usdBY: Double = 0.0,val usdSell: Double = 0.0,
                 val eurBy: Double = 0.0,val eurSell: Double = 0.0,val cnyBY: Double = 0.0,val cnySell: Double = 0.0,
    val image: Int,val upDate: String = "") {
}


data class Cb(val stringCb: String,val usdCb: String,val eurCb: String,val cnyCb: String)


package com.example.partser_valut

data class Base(val bank: String,val image: Int) {

    companion object {

        val b1 = Base("альфа-банк",R.drawable.alfa)
        val b2 = Base("финам",R.drawable.finam)
        val b3 = Base("юнистрим",R.drawable.unistrim)
        val b4 = Base("тихоокеанский",R.drawable.aziatsco___ticho)
        val b5 = Base("уралсиб",R.drawable.uralsib)
        val b6 = Base("ббр",R.drawable.br)
        val b7 = Base("бкс",R.drawable.bks)
        val b8 = Base("левобережный",R.drawable.levobereshny)
        val b9 = Base("втб",R.drawable.vtb)
        val b10 = Base("интеза",R.drawable.inteza)
        val b11 = Base("стандарт",R.drawable.russkiy_standert)
        val b12 = Base("синара",R.drawable.sinatra)
        val b13 = Base("братский",R.drawable.bratsky)
        val b14 = Base("газпромбанк",R.drawable.gazprom)
        val b15 = Base("газэнерго",R.drawable.gazenergo)
        val b16 = Base("дальневосточный",R.drawable.dalnevostochny)
        val b17 = Base("енисейский",R.drawable.eniseyskiy)
        val b18 = Base("ингосстрах",R.drawable.ingosstrach)
        val b19 = Base("ланта",R.drawable.lanta)
        val b20 = Base("мтс",R.drawable.mts)
        val b21 = Base("отп",R.drawable.otp)
        val b22 = Base("райффайзен",R.drawable.rayfaizing)
        val b23 = Base("россельхоз",R.drawable.rosselhoz)
        val b24 = Base("сдм",R.drawable.sdm)
        val b25 = Base("сбербанк",R.drawable.sber)
        val b26 = Base("совкомбанк",R.drawable.sovkom)
        val b27 = Base("солид",R.drawable.solid)
        val b28 = Base("убрир",R.drawable.ubrir)
        val b29 = Base("хакасский",R.drawable.hakas)
        val b30 = Base("цифра",R.drawable.cifra)
        val b31 = Base("экспобанк",R.drawable.ekspo)

        val base = listOf(b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22,b23,b24,b25,b26,b27,b28,b29,b30,b31)

    }

}
package com.example.quoteapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context) : ViewModel() {

    var quotelist : Array<Quote> = emptyArray()
        var index =0;
    lateinit var gson: Gson

    init {
        quotelist = loadQuotefromAsset()
    }
    private fun loadQuotefromAsset(): Array<Quote> {
       val input = context.assets.open("Quote.json")
        val size:Int = input.available()
        val buffer = ByteArray(size)
        input.read(buffer)
        input.close()
        val json = String(buffer, Charsets.UTF_8)

        gson = Gson()

        return gson.fromJson(json, Array<Quote>::class.java)

    }

    fun getquoteList()= quotelist[index]

    fun nextList() = quotelist[++index]

    fun previousList()= quotelist[--index]

}
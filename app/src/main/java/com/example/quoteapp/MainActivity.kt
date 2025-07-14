package com.example.quoteapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {


    lateinit var mainViewModel: MainViewModel

    private val quoteText: TextView
        get() = findViewById(R.id.quoteText)
    private val authorText: TextView
        get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(applicationContext)).get(MainViewModel::class.java)

        setText(mainViewModel.getquoteList())


    }

    fun setText(quote: Quote){
        quoteText.text = quote.text
        authorText.text = quote.author
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type ="text/plain"

        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getquoteList().text)
        startActivity(intent)
    }
    fun onNext(view: View) {
        setText(mainViewModel.nextList())
    }
    fun onPrevious(view: View) {
        setText(mainViewModel.previousList())

    }


}
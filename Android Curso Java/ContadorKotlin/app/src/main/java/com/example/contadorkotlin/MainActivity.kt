package com.example.contadorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var contador = 0

    fun incrementar(view: View){
        this.contador++
        textView.text = this.contador.toString()
    }

    fun reiniciar(view: View){
        this.contador = 0
        textView.text = this.contador.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textView = findViewById<TextView>(R.id.textView)
    }
}
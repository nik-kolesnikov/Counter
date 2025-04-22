package com.example.counter

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var buttonPlus: Button? = null
    private var buttonClearData: Button? = null
    private var textCount: TextView? = null
    var count: Int = 0

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", count)
        textCount!!.text = count.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textCount = findViewById(R.id.textView3)
        buttonPlus = findViewById(R.id.buttonPlus)
        buttonClearData = findViewById(R.id.buttonClearData)
    }

    fun onClickButtonPlus(view: View?) {
        count++
        textCount!!.text = count.toString()
    }

    fun onClickButtonClearData(view: View?) {
        count = 0
        textCount!!.text = count.toString()

        if (count == 0) {
            val toast = Toast.makeText(applicationContext, "Data Cleaned", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState.getInt("count")
        textCount!!.text = count.toString()
    }
}
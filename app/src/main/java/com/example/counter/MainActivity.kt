package com.example.counter

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var buttonPlus: Button
    private lateinit var buttonClearCount: Button
    private lateinit var buttonClearRecord: Button
    private lateinit var textCount: TextView
    private lateinit var textRecord: TextView

    private var count: Int = 0
    private var record: Int? = 0

    private val sharedPref: SharedPreferences by lazy {
        baseContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textCount = findViewById(R.id.textView3)
        textRecord = findViewById(R.id.recordTextView)
        buttonPlus = findViewById(R.id.buttonPlus)
        buttonClearCount = findViewById(R.id.buttonClearCount)
        buttonClearRecord = findViewById(R.id.buttonClearRecord)

        record = sharedPref.getInt(RECORD_PREF_NAME, 0)
        textRecord.text = record.toString()

        buttonPlus.setOnClickListener { onClickButtonPlus() }
        buttonClearCount.setOnClickListener { onClickButtonClearCount() }
        buttonClearRecord.setOnClickListener { onClickButtonClearRecord() }
    }

    fun onClickButtonPlus() {
        count++
        textCount.text = count.toString()

        if (count > record!!) {
            record = count
            textRecord.text = record.toString()
            sharedPref.edit().putInt(RECORD_PREF_NAME, record!!).apply()
        }
    }

    fun onClickButtonClearCount() {
        if (count == 0) {
            Toast.makeText(applicationContext, "Пока нечего очищать - продолжайте наживать!", Toast.LENGTH_SHORT).show()
        } else {
            count = 0
            textCount.text = count.toString()
            Toast.makeText(applicationContext, "Очистили, начинаем заново!", Toast.LENGTH_SHORT).show()
        }
    }

    fun onClickButtonClearRecord() {
        val builder = AlertDialog.Builder(this)
        builder
            .setTitle("Сбросить рекорд?")
            .setMessage("Вы уверены, что хотите сбросить рекорд?")
            .setPositiveButton("Да") { _, _ ->
                record = 0
                textRecord.text = record.toString()
                sharedPref.edit().remove(RECORD_PREF_NAME).apply()
            }
            .setNegativeButton("Нет") { _, _ -> }

        val dialog = builder.create()
        dialog.show()
    }

    companion object {
        private const val PREFS_NAME = "CounterPrefs"
        private const val RECORD_PREF_NAME = "counter_record"
    }
}


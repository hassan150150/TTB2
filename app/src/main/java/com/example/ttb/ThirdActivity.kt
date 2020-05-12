package com.example.ttb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {

    private lateinit var slider : SeekBar
    private lateinit var value : TextView
    private lateinit var slider2 : SeekBar
    private lateinit var value2 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        val toolbar: Toolbar = findViewById(R.id.toolbar2)

        slider = findViewById<SeekBar>(R.id.seekBar)
        value = findViewById<TextView>(R.id.textView5)
        slider2 = findViewById<SeekBar>(R.id.seekBar2)
        value2 = findViewById<TextView>(R.id.textView7)

        slider.max = 100
        slider2.max = 10

        slider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                value.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        slider2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                value2.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        val nameEt = findViewById<EditText>(R.id.editText)
        val noteEt = findViewById<EditText>(R.id.editText3)
        val save = findViewById<Button>(R.id.save)

        save.setOnClickListener{
            val name = nameEt.text.toString()
            val note = noteEt.text.toString()



            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("Name",name)
            intent.putExtra("Note",note)
            startActivity(intent)
        }
    }
}

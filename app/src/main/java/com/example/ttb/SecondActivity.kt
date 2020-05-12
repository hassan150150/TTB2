package com.example.ttb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_third.*

class SecondActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        imageButton.setOnClickListener{
            val somthing = Intent(this, ThirdActivity::class.java)
            startActivity(somthing)
        }
        var intent = intent
        val name = intent.getStringExtra("Name")
        val note = intent.getStringExtra("Note")

        if(name != null && note != null) {
            val textView2 = findViewById<TextView>(R.id.textView2)
            textView2.text = name
            val textView12 = findViewById<TextView>(R.id.textView12)
            textView12.text = note
        }

    }

}

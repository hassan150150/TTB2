package com.example.ttb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_second__temp.*

class Second_Temp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second__temp)

        imageButton.setOnClickListener{
            val something = Intent(this, ThirdActivity::class.java)
            startActivity(something)
        }

        button.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

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

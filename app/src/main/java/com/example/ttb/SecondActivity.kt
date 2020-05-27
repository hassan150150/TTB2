package com.example.ttb

import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.appsinthesky.bluetoothtutorial.BT
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_third.*
import java.io.IOException
import java.util.*

class SecondActivity : AppCompatActivity() {

    companion object {
        var m_myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        var m_bluetoothSocket: BluetoothSocket? = null
        lateinit var m_progress: ProgressDialog
        lateinit var m_bluetoothAdapter: BluetoothAdapter
        var m_isConnected: Boolean = false
        lateinit var m_address: String
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        imageButton.setOnClickListener{
            val something = Intent(this, ThirdActivity::class.java)
            startActivity(something)
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

        if(m_bluetoothSocket != null){ //temp
            try{
                val available = inputStream.available()
                val bytes = ByteArray(available)
                m_bluetoothSocket!!.inputStream.read(bytes, 0, available)
                val text = String(bytes)
                Log.i("server", "Temp received")
                Log.i("server", text)
                val textView10 = findViewById<TextView>(R.id.textView10)
                textView10.text = text

            } catch(e: IOException) {
                e.printStackTrace()
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        m_address = intent.getStringExtra(BT.EXTRA_ADDRESS)

        ConnectToDevice(this).execute()

        button.setOnClickListener { disconnect() }
    }

    private fun sendCommand(input: String) {
        if (m_bluetoothSocket != null) {
            try{
                m_bluetoothSocket!!.outputStream.write(input.toByteArray())
            } catch(e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private val inputStream = m_bluetoothSocket!!.inputStream

    private fun disconnect() {
        if (m_bluetoothSocket != null) {
            try {
                m_bluetoothSocket!!.close()
                m_bluetoothSocket = null
                m_isConnected = false
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        finish()
    }

    private class ConnectToDevice(c: Context) : AsyncTask<Void, Void, String>() {
        private var connectSuccess: Boolean = true
        private val context: Context

        init {
            this.context = c
        }

        override fun onPreExecute() {
            super.onPreExecute()
            m_progress = ProgressDialog.show(context, "Connecting...", "please wait")
        }

        override fun doInBackground(vararg p0: Void?): String? {
            try {
                if (m_bluetoothSocket == null || !m_isConnected) {
                    m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                    val device: BluetoothDevice = m_bluetoothAdapter.getRemoteDevice(m_address)
                    m_bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(m_myUUID)
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
                    m_bluetoothSocket!!.connect()
                }
            } catch (e: IOException) {
                connectSuccess = false
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (!connectSuccess) {
                Log.i("data", "couldn't connect")
            } else {
                m_isConnected = true
            }
            m_progress.dismiss()
        }
    }
}

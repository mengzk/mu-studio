package com.edu.postest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edu.postest.ui.TestActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<View>(R.id.main_text).setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }

        listenCallState()
    }

    //
    fun listenCallState() {
        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        telephonyManager.listen(object : PhoneStateListener() {
            override fun onCallStateChanged(state: Int, phoneNumber: String?) {
                when (state) {
                    TelephonyManager.CALL_STATE_OFFHOOK -> {
                        // 通话接通
                        Log.d("TelephonyManager" , "onCallStateChanged: 通话接通 $phoneNumber" )
                    }
                    TelephonyManager.CALL_STATE_IDLE -> {
                        // 通话结束
                        Log.d("TelephonyManager" , "onCallStateChanged: 通话结束 $phoneNumber" )
                    }
                    TelephonyManager.CALL_STATE_RINGING -> {
                        // 来电响铃
                        Log.d("TelephonyManager" , "onCallStateChanged: 来电响铃 $phoneNumber" )
                    }
                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE)
    }
}
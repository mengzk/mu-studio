package com.edu.postest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.edu.postest.model.body.LoginBody
import com.edu.postest.model.entity.UpdateEntity
import com.edu.postest.model.entity.UserEntity
import com.edu.postest.modules.network.BodyData
import com.edu.postest.modules.network.Client
import com.edu.postest.modules.network.RfCallback
import com.edu.postest.modules.network.SafeResult
import kotlinx.coroutines.launch
import retrofit2.Callback

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.act_welcome)

        initData()
    }

    fun initData() {
        // TODO: Initialize data here
        // 倒计时3秒
        window.decorView.postDelayed({
            // 跳转到主界面
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }

    override fun onResume() {
        super.onResume()
        check()
    }

    fun check() {
        lifecycleScope.launch {
            val res = Client.safeApiCall { Client.main.checkVersion("1212")}
            if(res is SafeResult.Success) {
                Log.i("WelcomeActivity", "---> res: "+res.data.version)
            }else if(res is SafeResult.Error) {
                Log.e("WelcomeActivity", "---> err: "+res.msg)
            }
//            try {
//                val res = Client.main.checkVersion("1212")
//                Log.i("WelcomeActivity", "---> res:")
//            }catch (e: Exception) {
//                Log.e("WelcomeActivity", "---> err: ${e.message}")
//            }
        }

    }
}


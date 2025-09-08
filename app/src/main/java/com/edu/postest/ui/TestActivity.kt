package com.edu.postest.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.window.OnBackInvokedCallback
import android.window.OnBackInvokedDispatcher
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import com.edu.postest.R
import com.edu.postest.custom.VMActivity
import androidx.navigation.findNavController

/**
 * Author: Meng
 * Date: 2025/08/29
 * Modify: 2025/08/29
 * Desc:
 */
class TestActivity: VMActivity() {
    private val viewModel: TestViewModel by lazy {
        ViewModelProvider(this)[TestViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.act_test)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT, {
                    onBack()
                }
            )
        }else {
            onBackPressedDispatcher.addCallback(this) {
                onBack()
            }
        }

        // 只在首次创建时初始化
        if (savedInstanceState == null) {
            initView()
        }
    }

    fun initView() {
//        val nav = this.findNavController(R.id.nav_host_test)
//        nav.navigate(R.id.frag_test1)
    }

    override fun onSupportNavigateUp(): Boolean {
//        return super.onSupportNavigateUp()
        val nav = this.findNavController(R.id.nav_host_test)
        return nav.navigateUp()
    }

//    @Deprecated("Deprecated in Java")
//    override fun onBackPressed() {
//        super.onBackPressed()
//        val nav = this.findNavController(R.id.nav_host_test)
//        if (nav.currentDestination?.id == R.id.frag_test1) {
//            finish()
//        } else {
//            nav.popBackStack()
//        }
//    }

    fun onBack() {
        Log.e("TestActivity", "-------> onCreate: back")
//        val nav = findNavController(R.id.nav_host_test)
//        if (nav.currentDestination?.id == R.id.frag_test1) {
//            finish()
//        } else {
//            nav.popBackStack()
//        }
    }

}
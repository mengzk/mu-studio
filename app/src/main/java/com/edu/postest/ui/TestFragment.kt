package com.edu.postest.ui

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.edu.postest.R
import com.edu.postest.custom.VMFragment
import com.edu.postest.databinding.FragTestBinding
import com.edu.postest.modules.network.Client
import com.edu.postest.modules.network.SafeResult
import com.edu.postest.modules.network.safeApiCall
import kotlinx.coroutines.launch

/**
 * Author: Meng
 * Date: 2025/09/08
 * Modify: 2025/09/08
 * Desc:
 */
class TestFragment: VMFragment<FragTestBinding>(R.layout.frag_test) {
    private val viewModel: TestViewModel by viewModels()

    override fun lazyInit(binding: FragTestBinding) {
        binding.fragTestText.text = "Fragment 01 go"
        binding.fragTestText2.text = "Test Fragment 01"

        binding.fragTestText.setOnClickListener {
            navigateTo(R.id.test_to_test2)
        }

        binding.fragTestText2.setOnClickListener {
            navigateTo(R.id.test_to_test3)
        }
    }


    fun check() {
        lifecycleScope.launch {
            val res = safeApiCall { Client.main.checkVersion("1212")}
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
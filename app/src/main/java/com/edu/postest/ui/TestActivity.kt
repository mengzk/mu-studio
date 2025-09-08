package com.edu.postest.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.edu.postest.R
import com.edu.postest.custom.VMActivity

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

        setContentView(R.layout.act_home)
    }


}
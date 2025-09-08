package com.edu.postest.ui

import androidx.fragment.app.viewModels
import com.edu.postest.R
import com.edu.postest.custom.VMFragment
import com.edu.postest.databinding.FragTestBinding

/**
 * Author: Meng
 * Date: 2025/09/08
 * Modify: 2025/09/08
 * Desc:
 */
class TestFragment: VMFragment<FragTestBinding>(R.layout.frag_test) {
    private val viewModel: TestViewModel by viewModels()

}
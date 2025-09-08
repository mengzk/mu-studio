package com.edu.postest.ui.testNav

import androidx.fragment.app.viewModels
import com.edu.postest.R
import com.edu.postest.custom.VMFragment
import com.edu.postest.databinding.FragTestBinding
import com.edu.postest.ui.TestViewModel

/**
 * Author: Meng
 * Date: 2025/09/08
 * Modify: 2025/09/08
 * Desc:
 */
class Test3Fragment: VMFragment<FragTestBinding>(R.layout.frag_test) {
    private val viewModel: TestViewModel by viewModels()

    override fun lazyInit(binding: FragTestBinding) {
        binding.fragTestText.text = "Test Fragment 03"
    }
}
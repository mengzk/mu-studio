package com.edu.postest.custom

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.window.OnBackInvokedCallback
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController

/**
 * Author: Meng
 * Date: 2025/11/21
 * Modify: 2025/11/21
 * Desc:
 */

open class VMFragment<VB : ViewDataBinding>(@LayoutRes val layoutId: Int) : Fragment() {

    private var isLoaded = false
    protected lateinit var binding: VB
    protected lateinit var activity: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as VMActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<VB>(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        onBindView(binding, savedInstanceState)
        return binding.root
//        return super.onCreateView(inflater, container, savedInstanceState)
    }

    // 视图绑定 -同onCreateView
    open fun onBindView(binding: VB, savedInstanceState: Bundle?) {}

    // 懒加载
    open fun lazyInit(binding: VB) {}

    private fun navigation(): NavController {
        return findNavController()
    }

    protected fun navigateTo(
        @IdRes resId: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = null,
        navExtras: Navigator.Extras? = null
    ) {
        navigation().navigate(resId, args, navOptions, navExtras)
    }

    protected fun popBack() {
        navigation().popBackStack()
    }


//
    protected fun initBackLis() {

//        val callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                onBack()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

//        val canBack = navigation().navigateUp()
        val canBack = navigation().popBackStack()
        if(canBack) {
            activity.finish()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isLoaded) {
            isLoaded = true
            lazyInit(binding)
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
        binding.unbind()
    }
}
package com.edu.postest.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.edu.postest.custom.AppViewModel

/**
 * Author: Meng
 * Date: 2025/09/08
 * Modify: 2025/09/08
 * Desc:
 */
class TestViewModel: AppViewModel() {
    val title = MutableLiveData<String>()
    val list = MutableLiveData<List<String>>()


    fun updateTitle(newTitle: String) {
        title.value = newTitle
    }

    fun test() {
        Log.i("TestViewModel", "---> test:")
    }
}
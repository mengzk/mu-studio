package com.edu.postest.model.body

import com.edu.postest.config.Constants

class LoginBody(
    var phone: String,
    var code: String,
) {
    val deviceSn: String = Constants.DEVICE_SN
    override fun toString(): String {
        return "LoginBody(phone='$phone', code='$code')"
    }
}
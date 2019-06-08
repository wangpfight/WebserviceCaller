package com.example.retrofitdemo.api.responsemodels

import com.google.gson.JsonObject


class BaseResWrapper {
    var status: Status? = null
    var result: JsonObject?=null
    var error_code = ""
    var message = ""
}


class CallResult<T> {
    var status: Status = Status()
    var clazz: T? = null
}
package com.example.retrofitdemo.api.responsemodels

class Status {
    var error_code: Int = 1
    var message: String = "No Data Error!"

    override fun toString(): String {
        return "Status(error_code=$error_code, message='$message')"
    }


}
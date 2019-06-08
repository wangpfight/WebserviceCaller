package com.example.retrofitdemo.api.domain


import android.content.Context
import com.example.retrofitdemo.api.getRestCaller
import com.example.retrofitdemo.api.requestmodels.LoginRequest
import com.example.retrofitdemo.api.responsemodels.BaseResWrapper
import com.example.retrofitdemo.LoginActivity
import com.example.webservicecaller.applySchedulers
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.ResponseBody

fun Context.doLogin(loginRequest: LoginRequest, onGetLoginSucess: (result: JsonObject) -> Unit,
                    onError: (message: String) -> Unit){

    /*if (!AndroidUtility.isNetworkAvailable(this)) {
        AndroidUtility.showSnackBar(this as Activity, getString(R.string.pls_check_internet_con))
        return
    }*/
    (this as LoginActivity).showLoader()
    getRestCaller().doLogin(loginRequest).compose<ResponseBody>(applySchedulers<ResponseBody>()).subscribe({ response ->
        val string_response = response?.string() ?: "{}"
        val gson = Gson()
        try {
            val baseResWrapper = gson.fromJson(string_response, BaseResWrapper::class.java)
            if (baseResWrapper.status?.error_code == 0) {
                onGetLoginSucess(baseResWrapper.result!!)
            } else {
                onError(baseResWrapper.status?.message?:"")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            onError("Error Connecting...Please try again!")
        }
        this.hideLoader()
    }, { error ->
        this.hideLoader()
        onError("Error Connecting...Please try again!")
    })
}
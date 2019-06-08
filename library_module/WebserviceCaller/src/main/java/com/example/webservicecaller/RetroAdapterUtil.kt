package com.example.webservicecaller

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit


fun getRetroAdapter(baseUrl: String, timeoutTime: Long): Retrofit {
    val builder : Retrofit.Builder = Retrofit.Builder().
        addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
        addConverterFactory(GsonConverterFactory.create())
    val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            message ->
        Log.d("ReqLogger", "REQ_LOG: $message")
    })
    interceptor.level = (HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .connectTimeout(timeoutTime, TimeUnit.MINUTES).readTimeout(timeoutTime, TimeUnit.MINUTES).writeTimeout(timeoutTime, TimeUnit.MINUTES)
        .addInterceptor(interceptor)
        .build()

    builder.client(client)
    return builder.baseUrl(baseUrl).build()
}

fun <T> getServiceCaller(adapter: Retrofit,clazz: Class<T>) : T {
    return adapter.create(clazz)
}

fun <T> applySchedulers(): Observable.Transformer<T, T> {
    return Observable.Transformer<T,T>() {
            observable ->
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> applyAsyncSchedulers(): Observable.Transformer<T, T> {
    return Observable.Transformer<T,T>() {
            observable ->
        observable.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread())
    }
}
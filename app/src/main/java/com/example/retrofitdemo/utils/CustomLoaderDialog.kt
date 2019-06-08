package com.example.retrofitdemo.utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.example.retrofitdemo.R
import com.example.retrofitdemo.widgets.AVLoadingIndicatorView


class CustomLoaderDialog(context: Context): Dialog(context) {
    lateinit var aviloader: AVLoadingIndicatorView
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_loader)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initView()
    }

    private fun initView() {
        aviloader = findViewById(R.id.aviloader)
    }

    override fun setOnCancelListener(listener: DialogInterface.OnCancelListener?) {
        super.setOnCancelListener(listener)
        aviloader.hide()
    }

    override fun setOnShowListener(listener: DialogInterface.OnShowListener?) {
        super.setOnShowListener(listener)
        aviloader.show()
    }
}
package com.example.retrofitdemo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.retrofitdemo.api.domain.doLogin
import com.example.retrofitdemo.api.requestmodels.LoginRequest
import com.example.retrofitdemo.api.responsemodels.User
import com.google.gson.Gson
import com.example.retrofitdemo.utils.AndroidUtility
import com.example.retrofitdemo.utils.CustomLoaderDialog

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mCustomLoaderDialog: CustomLoaderDialog
    private val tvLogin by lazy { findViewById<TextView>(R.id.tv_login) }
    private val tvForgotPassword by lazy { findViewById<TextView>(R.id.tv_forget_pass) }
    private val tvSignUpNow by lazy { findViewById<TextView>(R.id.tv_sign_up_now) }
    private val et_email by lazy { findViewById<EditText>(R.id.et_email) }
    private val et_Password by lazy { findViewById<EditText>(R.id.et_Password) }
    private var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mContext = this@LoginActivity
        initView()
    }

    private fun initView() {
        mCustomLoaderDialog = CustomLoaderDialog(this@LoginActivity)
        tvLogin.setOnClickListener(this)
        tvForgotPassword.setOnClickListener(this)
        tvSignUpNow.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_login -> {
                setLogin()
            }
         /*   R.id.tv_forget_pass -> {
                showForgotPasswordDialog()
            }
            R.id.tv_sign_up_now -> {
                startActivity<RegistrationActivity>()
                overridePendingTransition(0, 0)
            }*/
        }
    }

   /* private fun showForgotPasswordDialog() {
        var mForgotPasswordDialog = ForgotPasswordDialog(this@LoginActivity,
            object : OnYesNoClickListener {
                override fun onYesClicked() {
                }

                override fun onNoClicked() {
                }
            })
        mForgotPasswordDialog.show()
    }*/

    fun showLoader() {
        mCustomLoaderDialog.show()
    }

    fun hideLoader() {
        if (mCustomLoaderDialog.isShowing)
            mCustomLoaderDialog.cancel()
    }

    private fun setLogin() {
        val userName = et_email.text.toString().trim()
        val strPassword = et_Password.text.toString().trim()
     /*   if (userName.equals("")) {
            AndroidUtility.showSnackBar(this@LoginActivity, getString(R.string.pls_enter_user_name))
            return
        }
        if (strPassword.equals("")) {
            AndroidUtility.showSnackBar(this@LoginActivity, getString(R.string.pls_enter_password))
            return
        }
        if (!AndroidUtility.isValidEmail(userName)) {
            AndroidUtility.showSnackBar(this@LoginActivity, getString(R.string.invalid_email))
            return
        }*/
        /*if (!AndroidUtility.validatePassword(strPassword)) {
            AndroidUtility.showSnackBar(this@LoginActivity, getString(R.string.is_valid_passwd))
            return
        }*/
        callLoginAPI(userName, strPassword)
    }

    fun callLoginAPI(userName: String, strPassword: String) {
        val loginRequest = LoginRequest()
        loginRequest.email = userName
        loginRequest.password = strPassword

        doLogin(loginRequest, onGetLoginSucess =
        {
            var gson = Gson()
            val userDetails = it.getAsJsonObject("userdetails")
            val user = gson.fromJson(userDetails, User::class.java)
            AndroidUtility.showSnackBar(this,"Login Successfully.....")
           // saveUser(user)
           // Stash.put(USER_DETAILS, user)
           // moveToDashboard(user)
        }, onError = {
            AndroidUtility.showSnackBar(this@LoginActivity, it)
        })
    }

}
/*
fun Activity.moveToDashboard(user: User) {
    when(user.userRoleId){
        "1" -> (applicationContext as EquineOrgnizerApplication).mUserTypes = UserTypes.OWNER
        "2" -> (applicationContext as EquineOrgnizerApplication).mUserTypes = UserTypes.PROVIDER
        "6" -> (applicationContext as EquineOrgnizerApplication).mUserTypes = UserTypes.TRAINERS
    }
    startActivity<DashboardActivity>()
    overridePendingTransition(0, 0)
    finish()
}*/

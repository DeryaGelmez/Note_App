package com.example.thinkpad.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.thinkpad.MainActivity
import com.example.thinkpad.Models.Note
import com.example.thinkpad.Models.NoteViewModel
import com.example.thinkpad.R
import com.example.thinkpad.databinding.ActivityLoginBinding
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class LoginActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener, View.OnKeyListener {

    private lateinit var mBinding: ActivityLoginBinding

    fun showErrorToast(context: Context, errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
        mBinding.emailEt.onFocusChangeListener=this
        mBinding.passwordEt.onFocusChangeListener=this
        onClick(mBinding.root)
        mBinding.textView.text=""
    }
    private fun validateEmail():Boolean{
       var errorMessage:String?=null
        val value=mBinding.emailEt.text.toString()
        if(value.isEmpty()){
            errorMessage="Email is required"
        }else if(!Patterns.EMAIL_ADDRESS.matcher(value).matches()){
errorMessage="Email address is invalid"
        }
        if(errorMessage!=null) {
            mBinding.emailTil.apply {
                mBinding.emailTil.isErrorEnabled = true
                error = errorMessage

            }
        }
        return errorMessage==null
    }
    private fun validatePassword():Boolean{
        var errorMessage:String?=null
        val value=mBinding.passwordEt.text.toString()
        if(value.isEmpty()){
            errorMessage="Password is required"
        }else if(value.length<4){
            errorMessage="Password must be 4 characters long"
        }
        if(errorMessage!=null) {
            mBinding.passwordTil.apply {
                mBinding.passwordTil.isErrorEnabled = true
                error = errorMessage

            }
        }


        return errorMessage==null
    }
    override fun onClick(view: View?) {
        var errorMessage:String?=null
        lateinit var viewModel: NoteViewModel
        val getContent=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->

            if(result.resultCode== Activity.RESULT_OK){

                val note = result.data?.getSerializableExtra("note") as? Note
                if(note!=null){
                    viewModel.insertNote(note)
                }
            }
        }
        mBinding.registerBtn.setOnClickListener{

            val intent = Intent(this, RegisterActivity::class.java)
            getContent.launch(intent)
        }
        mBinding.loginBtn.setOnClickListener{

            var g=mBinding.passwordTil.isErrorEnabled
            if(!mBinding.passwordTil.isErrorEnabled && !mBinding.emailTil.isErrorEnabled){
                val gfgThread = Thread {
                    try {
                        val client = OkHttpClient()
                        val jsonRequestBody = ""
                        val JSON: MediaType = MediaType.get("application/json; charset=utf-8")
                        val body: RequestBody = RequestBody.create(JSON, jsonRequestBody)
                        val request = Request.Builder()
                            .url("http://192.168.1.45:5000/api/Login/Login?email="+mBinding.emailEt.text+"&password="+mBinding.passwordEt.text)
                            .post(body)
                            .build()
                        val response = client.newCall(request).execute()
                        if  (response.isSuccessful) {
                            val responseBody = response.body()?.string()

                            val isTrue = responseBody?.toBoolean() ?: false
                            if (isTrue)
                            {
                                val intent = Intent(this, MainActivity::class.java)
                                getContent.launch(intent)
                                mBinding.textView.text=""
                            }
                            else    {
                                mBinding.textView.text= "Check your mail/password"
                            }
                        }

                    } catch (e: Exception) {
                        mBinding.textView.text= e.message
                        e.printStackTrace()
                    }

                }
                gfgThread.start()
            }
            }
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
       if(view!=null){
           when(view.id){
               R.id.emailEt->{
                       if(validateEmail()){
                           mBinding.emailTil.isErrorEnabled=false
                    }
               }
               R.id.passwordEt->{
                       if(validatePassword()){
                           mBinding.passwordTil.isErrorEnabled=false
                       }
                   else {
                           mBinding.passwordTil.apply {
                               setStartIconDrawable(R.drawable.check_circle_24)
                               setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                           }
                       }

               }
           }
       }
    }

    override fun onKey(view: View?, event: Int, keyevent: KeyEvent?): Boolean {
       return false
    }
}
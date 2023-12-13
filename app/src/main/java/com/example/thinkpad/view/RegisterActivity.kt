package com.example.thinkpad.view

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.thinkpad.MainActivity
import com.example.thinkpad.Models.Note
import com.example.thinkpad.Models.NoteViewModel
import com.example.thinkpad.R
import com.example.thinkpad.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener, View.OnKeyListener {

    private lateinit var mBinding: ActivityRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
        mBinding.fullNameEt.onFocusChangeListener=this
        mBinding.emailEt.onFocusChangeListener=this
        mBinding.passwordEt.onFocusChangeListener=this
        mBinding.cPasswordEt.onFocusChangeListener=this
        onClick(mBinding.root)
    }
    private fun validateFullName():Boolean {
        var errorMessage: String?=null
        val value: String = mBinding.fullNameEt.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Full name is required"
        }
if(errorMessage!=null){
    mBinding.fullNameTil.apply {
        isErrorEnabled=true
        error=errorMessage
    }
}

return errorMessage==null

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
                isErrorEnabled = true
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
        }else if(value.length<6){
            errorMessage="Password must be 6 characters long"
        }
        if(errorMessage!=null) {
            mBinding.passwordTil.apply {
                isErrorEnabled = true
                error = errorMessage

            }
        }


        return errorMessage==null
    }

    private fun validateConfirmPassword():Boolean{
        var errorMessage:String?=null
        val value=mBinding.cPasswordEt.text.toString()
        if(value.isEmpty()){
            errorMessage="Confirm password is required"
        }else if(value.length<6){
            errorMessage="Confirm password must be 6 characters long"
        }
        if(errorMessage!=null) {
            mBinding.cPasswordTil.apply {
                isErrorEnabled = true
                error = errorMessage

            }
        }

        return errorMessage==null
    }
private fun validatePasswordAndConfirmPassword():Boolean{
    var errorMessage:String?=null
    val password =mBinding.passwordEt.text.toString()
    val confirmPassword=mBinding.cPasswordEt.text.toString()
    if(password!=confirmPassword){
        errorMessage="Confirm password doesn't match with password"
    }
    if(errorMessage!=null) {
        mBinding.cPasswordTil.apply {
            isErrorEnabled = true
            error = errorMessage

        }
    }


    return errorMessage==null
}
    override fun onClick(view: View?) {
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
            val intent = Intent(this, MainActivity::class.java)
            getContent.launch(intent)
        }

    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
       if(view!=null){
           when(view.id){
               R.id.fullNameEt->{
                   if(hasFocus){
                   if(mBinding.fullNameTil.isErrorEnabled){
                       mBinding.fullNameTil.isErrorEnabled=false
                   }

}else{
    validateFullName()
}
               }
               R.id.emailEt->{
                   if(hasFocus){
                       if(mBinding.emailTil.isErrorEnabled){
                           mBinding.emailTil.isErrorEnabled=false
                       }

                   }else{
                       if(validateEmail()){

                       }
                   }
               }
               R.id.passwordEt->{
                   if(hasFocus){
                       if(mBinding.passwordTil.isErrorEnabled){
                           mBinding.passwordTil.isErrorEnabled=false
                       }
                   }else{
                      if(validatePassword()&&mBinding.cPasswordEt.text!!.isNotEmpty()&&validateConfirmPassword()&&validatePasswordAndConfirmPassword()) {
                          if (mBinding.cPasswordTil.isErrorEnabled) {
                              mBinding.cPasswordTil.isErrorEnabled = false
                          }
                          mBinding.cPasswordTil.apply{
                              setStartIconDrawable(R.drawable.check_circle_24)
                              setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                      }
                      }
                   }
               }
               R.id.cPasswordEt->{
                   if(hasFocus){
                       if(mBinding.cPasswordTil.isErrorEnabled){
                           mBinding.cPasswordTil.isErrorEnabled=false
                       }
                   }else{
                    if(validateConfirmPassword()&&validatePassword()&&validatePasswordAndConfirmPassword()){
                        if(mBinding.passwordTil.isErrorEnabled){
                            mBinding.passwordTil.isErrorEnabled=false
                        }
                        mBinding.cPasswordTil.apply{
                            setStartIconDrawable(R.drawable.check_circle_24)
                            setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                        }
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
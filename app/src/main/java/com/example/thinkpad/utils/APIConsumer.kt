package com.example.thinkpad.utils

import com.example.thinkpad.data.UniqueEmailValidationResponse
import com.example.thinkpad.data.ValidateEmailBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIConsumer {

    @POST("users/validate-unique-email")
    suspend fun validateEmailAddress(@Body body:ValidateEmailBody):Response<UniqueEmailValidationResponse>

}
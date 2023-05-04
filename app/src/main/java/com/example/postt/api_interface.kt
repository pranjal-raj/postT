package com.example.postt

import retrofit2.Call
import retrofit2.http.*

interface api_interface {


    @POST("aadhaar") //OUR ENDPOINT
    fun createPost(
        @Header("apy-token") //SINCE OUR API USES HEADERS WE HAVE HEADERS HERE. NOTE WE JUST ANNOTATE THE HEADER "KEY" AND VALUE IS PROVIDED AS A PARAMETER. HERE I'M PROVIDING A AUTHENTICATION TOKEN
        token: String,
        @Body
        Model: Model //SINCE OUR BODY IS ALSO AN OBJECT THEREFORE WE ARE PROVIDING AN OBJECT AS BODY TO OUR POST REQUEST
    )
    : Call<response> //IMPORTANT : CALL TYPE IS ALWAYS THE RESPONSE TYPE THAT IS THE RESPONSE MODEL
}
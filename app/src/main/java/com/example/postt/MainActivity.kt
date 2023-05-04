package com.example.postt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.postt.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
API :

const options = {
  method: 'POST',
  headers: {
    'apy-token': 'APY0kSVxDHNY1k1AveWKIlPVxAQPIZh93t24I8X8p9Y32Tyo76sCsnTCsFbyroRlyB0tfEz',
    'Content-Type': 'application/json'
  },
  body: '{"aadhaar":"568095330001"}'
};

fetch('https://api.apyhub.com/validate/aadhaar', options)
  .then(response => response.json())
  .then(response => console.log(response))
  .catch(err => console.error(err));

 */

class MainActivity : AppCompatActivity() {
    lateinit var mainxml : ActivityMainBinding //COZ IM USING VIEW BINDING
    val token = "APT00dfiWid5WlSPFI7uM4MRonCPnWGVbh536oqTtbrLe3BQg" //AUTHENTICATION REQUIRED FOR THE RESTAPI IM USING MIGHT NOT BE REQUIRED FOR YOU
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainxml = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainxml.root)


        mainxml.button.setOnClickListener {
            val an = mainxml.an.text.toString() //USER INPUT
            validate(an) //FUNCTION TO CALL
        }
    }

    public fun validate(an : String)
    {
        val url : String = "https://api.apyhub.com/validate/" //OUR BASE URL. NOTE HOW I HAVE NOT USED FULL "https://api.apyhub.com/validate/aadhaar" COZ "AADHAAR" IS OUR ENDPOINT

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api_interface::class.java)

        val myRequest = Model(an) // OBJECT OF OUR MODEL CLASS TO SEND FOR REQUEST
        val retrofitdata  = retrofitBuilder.createPost(token ,myRequest)
        retrofitdata.enqueue(object : Callback<response> //NOTICE THE CALLBACK IS ALSO RESPONSE TYPE
         {
            override fun onResponse(call: Call<response>, response: Response<response>) {
                val responseBody = response.body()
                if(responseBody!=null) {
                    val result : Boolean = responseBody.data
                    mainxml.resultText.setText(result.toString())
                }
            }

            override fun onFailure(call: Call<response>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Something Went Wrong : " + t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}
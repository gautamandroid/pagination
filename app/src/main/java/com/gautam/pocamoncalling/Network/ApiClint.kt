package com.gautam.json_passing.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClint {


       companion object{

           private var retrofit:Retrofit?=null

                fun init():ApiService{
                  retrofit = Retrofit.Builder()
                      .baseUrl("https://pokeapi.co/api/")
                      .addConverterFactory(GsonConverterFactory.create())
                      .build()

              return retrofit!!.create(ApiService::class.java)


           }

       }

  }
package com.gautam.json_passing.Network


import com.gautam.pokemonapi.Data.CallBackresponce
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


      @GET("v2/pokemon/")
      fun getUserList(
            @Query("offset") offset:Int,
            @Query("limit") limit:Int
      ):Call<CallBackresponce>

}

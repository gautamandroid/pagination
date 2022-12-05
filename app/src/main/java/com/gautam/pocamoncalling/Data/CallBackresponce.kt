package com.gautam.pokemonapi.Data

import com.google.gson.annotations.SerializedName

data class CallBackresponce(

    @SerializedName("count") var count: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("previous") var previous: String? = null,
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf()


)

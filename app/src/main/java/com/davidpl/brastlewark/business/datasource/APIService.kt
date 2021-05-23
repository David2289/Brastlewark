package com.example.display.business.datasource

import com.davidpl.brastlewark.business.model.Users
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("data.json")
    fun fetchBrastlewark(): Single<Users>

    @GET("data.json")
    fun fetchBrastlewarkCall(): Call<Users>

}
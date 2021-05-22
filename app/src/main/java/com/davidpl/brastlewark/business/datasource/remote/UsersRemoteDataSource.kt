package com.example.display.business.datasource.remote

import com.davidpl.brastlewark.business.model.Users
import com.example.display.business.datasource.APIService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UsersRemoteDataSource @Inject constructor(private val apiService: APIService) {

    fun fetchUsers(): Single<Users> {
        return apiService.fetchBrastlewark()
    }

}
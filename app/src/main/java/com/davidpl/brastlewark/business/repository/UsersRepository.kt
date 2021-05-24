package com.davidpl.brastlewark.business.repository

import com.davidpl.brastlewark.business.model.User
import com.davidpl.brastlewark.business.model.Users
import com.example.display.business.datasource.local.UsersLocalDataSource
import com.example.display.business.datasource.remote.UsersRemoteDataSource
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UsersRepository @Inject constructor(private val remoteDataSource: UsersRemoteDataSource,
                                          private val localDataSource: UsersLocalDataSource)  {

    fun getUsers(): Single<List<User>> {
        return if (localDataSource.isDataStored()) {
            Single.just(localDataSource.getUserList())
        }
        else {
            remoteDataSource.fetchUsers()
                .doOnSuccess(::saveUsers)
                .flatMap { response -> Observable.fromIterable(response.users).toList() }
        }

    }

    private fun saveUsers(users: Users) {
        localDataSource.saveUser(users)
    }

}
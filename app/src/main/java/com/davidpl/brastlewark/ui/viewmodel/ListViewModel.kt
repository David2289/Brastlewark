package com.davidpl.brastlewark.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davidpl.brastlewark.business.model.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel /*@Inject constructor(private val usersRepository: UsersRepository)*/ : ViewModel() {

    var userListLiveData: MutableLiveData<List<User>> = MutableLiveData()
    var userList: ArrayList<User> = ArrayList()

    /*fun getUsers() {
        usersRepository.getUsers(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleFirstListResponse, this::handleError)
    }

    private fun handleFirstListResponse(userList: List<User>) {
        this.userList.clear()
        this.userList.addAll(userList)
        usersRepository.getUsers(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSecondListResponse, this::handleError)
    }*/

    private fun handleSecondListResponse(userList: List<User>) {
        this.userList.addAll(userList)
        userListLiveData.value = this.userList
    }

    private fun handleError(t: Throwable) {
        Log.w("RETROFIT", "HAS BEEN AN ERROR: " + t.message)
    }

}
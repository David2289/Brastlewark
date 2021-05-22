package com.example.display.business.datasource.local

import com.davidpl.brastlewark.business.model.User
import com.example.display.business.datasource.local.androom.dao.UserDao
import javax.inject.Inject

class UsersLocalDataSource @Inject constructor(private val userDao: UserDao) {

//    fun getUserList(): List<User> {
//        return userDao.getUsers()
//    }

//    fun saveUser(user: User) {
//        userDao.insertUser(user)
//    }

}
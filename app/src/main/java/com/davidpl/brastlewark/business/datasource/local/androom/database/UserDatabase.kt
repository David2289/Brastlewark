package com.example.display.business.datasource.local.androom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.davidpl.brastlewark.business.model.User
import com.example.display.business.datasource.local.androom.dao.UserDao

//@Database(
//    entities = arrayOf(User::class),
//    version = 1,
//    exportSchema = false
//)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}
package com.example.display.business.datasource.local.androom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.brastlewark.business.datasource.local.androidroom.entity.UserEntity
import com.example.brastlewark.business.datasource.local.androidroom.entity.UserFriendEntity
import com.example.brastlewark.business.datasource.local.androidroom.entity.UserProfessionEntity
import com.example.display.business.datasource.local.androom.dao.UserDao

@Database(
    entities = arrayOf(
        UserEntity::class,
        UserProfessionEntity::class,
        UserFriendEntity::class
    ),
    version = 1,
    exportSchema = false
)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}
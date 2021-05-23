package com.example.display.business.datasource.local.androom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.brastlewark.business.datasource.local.androidroom.entity.UserEntity
import com.example.brastlewark.business.datasource.local.androidroom.entity.UserFriendEntity
import com.example.brastlewark.business.datasource.local.androidroom.entity.UserProfessionEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM gnomes_table")
    fun getAll(): List<UserEntity>

    @Query("SELECT name FROM gnomes_table WHERE id = :id")
    fun getName(id: Int): String

    @Query("SELECT age FROM gnomes_table WHERE id = :id")
    fun getAge(id: Int): Int

    @Insert
    fun insert(user: UserEntity)



    @Query("SELECT friend FROM gnome_friends_table WHERE gnome_id = :gnomeId")
    fun getFriends(gnomeId: Int): List<String>

    @Insert
    fun insert(userFriend: UserFriendEntity)



    @Query("SELECT profession FROM gnome_professions_table WHERE gnome_id = :gnomeId")
    fun getProfession(gnomeId: Int): List<String>

    @Insert
    fun insert(userProfession: UserProfessionEntity)

}
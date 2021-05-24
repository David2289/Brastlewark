package com.example.display.business.datasource.local

import com.davidpl.brastlewark.business.model.User
import com.davidpl.brastlewark.business.model.Users
import com.example.brastlewark.business.datasource.local.androidroom.entity.UserEntity
import com.example.brastlewark.business.datasource.local.androidroom.entity.UserFriendEntity
import com.example.brastlewark.business.datasource.local.androidroom.entity.UserProfessionEntity
import com.example.display.business.datasource.local.androom.dao.UserDao
import javax.inject.Inject

class UsersLocalDataSource @Inject constructor(private val userDao: UserDao) {

    fun isDataStored(): Boolean {
        return userDao.getAll().isNotEmpty()
    }

    fun getUserList(): List<User> {
        val userList = ArrayList<User>()
        for (userEntity in userDao.getAll()) {
            val professions = ArrayList<String>()
            for (profession in userDao.getProfession(userEntity.id)) {
                professions.add(profession)
            }
            val friends = ArrayList<String>()
            for (friend in userDao.getFriends(userEntity.id)) {
                friends.add(friend)
            }
            val user = User(userEntity.id, userEntity.name, userEntity.thumbnail, userEntity.age,
                userEntity.weight, userEntity.height, userEntity.hairColor, professions, friends)
            userList.add(user)
        }
        return userList
    }

    fun saveUser(users: Users) {
        for(user in users.users) {

            val userEntity = UserEntity(user.id, user.name, user.thumbnail, user.age, user.weight, user.height, user.hairColor)
            userDao.insert(userEntity)

            user.friends?.let {
                for (friend in it) {
                    val userFriendEntity = UserFriendEntity(user.id, friend)
                    userDao.insert(userFriendEntity)
                }
            }

            user.professions?.let {
                for (profession in it) {
                    val userProfessionEntity = UserProfessionEntity(user.id, profession)
                    userDao.insert(userProfessionEntity)
                }
            }
        }

    }

}
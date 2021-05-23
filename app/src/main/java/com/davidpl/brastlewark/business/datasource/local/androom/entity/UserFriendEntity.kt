package com.example.brastlewark.business.datasource.local.androidroom.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gnome_friends_table")
class UserFriendEntity (
    @ColumnInfo(name = "gnome_id") val gnomeId: Int,
    @ColumnInfo(name = "friend") val friend: String
    )
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "key")var key: Int? = null
}
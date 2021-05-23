package com.example.brastlewark.business.datasource.local.androidroom.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gnomes_table")
class UserEntity (
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "weight") val weight: Double,
    @ColumnInfo(name = "height") val height: Double,
    @ColumnInfo(name = "hairColor") val hairColor: String
    ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "key")var key: Int? = null
}



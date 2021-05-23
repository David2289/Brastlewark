package com.example.brastlewark.business.datasource.local.androidroom.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gnome_professions_table")
class UserProfessionEntity(
    @ColumnInfo(name = "gnome_id") val gnomeId: Int,
    @ColumnInfo(name = "profession") val profession: String
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "key")var key: Int? = null
}
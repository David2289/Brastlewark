package com.davidpl.brastlewark.business.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
        @SerializedName("id") val id: Int,

        @SerializedName("name") val name: String,

        @SerializedName("thumbnail") val thumbnail: String,

        @SerializedName("age") val age: Int,

        @SerializedName("weight") val weight: Double,

        @SerializedName("height") val height: Double,

        @SerializedName("hair_color") val hairColor: String,

        @SerializedName("professions") val professions: List<String>?,

        @SerializedName("friends") val friends: List<String>?

) : Parcelable
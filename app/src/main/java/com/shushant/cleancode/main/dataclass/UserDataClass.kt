package com.shushant.cleancode.main.dataclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shushant.cleancode.utils.Constants.TABLE_USERS
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = TABLE_USERS)
data class UserDataClass(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    var id: Int,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    var name: String,
    @ColumnInfo(name = "email")
    @SerializedName("email")
    @Expose
    var email: String,
    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    @Expose
    var gender: String,
    @ColumnInfo(name = "status")
    @SerializedName("status")
    @Expose
    var status: String
)

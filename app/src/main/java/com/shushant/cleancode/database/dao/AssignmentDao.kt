package com.shushant.cleancode.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shushant.cleancode.main.dataclass.UserDataClass
import com.shushant.cleancode.utils.Constants.TABLE_USERS

@Dao
interface AssignmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserDataClass>): List<Long>

    @Query("SELECT * FROM $TABLE_USERS")
    fun getUsersList(): List<UserDataClass>

}
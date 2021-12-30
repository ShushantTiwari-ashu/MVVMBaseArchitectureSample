package com.shushant.cleancode.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shushant.cleancode.database.dao.AssignmentDao
import com.shushant.cleancode.main.dataclass.UserDataClass
import com.shushant.cleancode.utils.Constants.DB_NAME

@Database(
    entities = [UserDataClass::class],
    version = 1,
    exportSchema = false
)
abstract class AssignmentDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var sInstance: AssignmentDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AssignmentDatabase? {
            if (sInstance == null) {
                synchronized(AssignmentDatabase::class.java) {
                    if (sInstance == null) {
                        sInstance = Room.databaseBuilder(
                            context,
                            AssignmentDatabase::class.java,
                            DB_NAME
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return sInstance
        }
    }

    abstract fun assignmentDao(): AssignmentDao
}
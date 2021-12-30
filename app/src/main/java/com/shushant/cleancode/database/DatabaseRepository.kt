package com.shushant.cleancode.database

import android.content.Context
import com.shushant.cleancode.database.dao.AssignmentDao
import com.shushant.cleancode.main.dataclass.UserDataClass
import java.util.concurrent.*

class DatabaseRepository(
    private var mDao: AssignmentDao? = null,
    private var mIoExecutor: ExecutorService? = null
) {
    companion object {
        @Volatile
        private var sInstance: DatabaseRepository? = null

        /**
         * Function to provide instance of data repository
         */
        fun getInstance(context: Context): DatabaseRepository? {
            if (sInstance == null) {
                synchronized(DatabaseRepository::class.java) {
                    if (sInstance == null) {
                        val database: AssignmentDatabase? =
                            AssignmentDatabase.getInstance(context)
                        sInstance = DatabaseRepository(
                            database?.assignmentDao(),
                            Executors.newSingleThreadExecutor()
                        )
                    }
                }
            }
            return sInstance
        }
    }

    private fun executeListInsertTasks(insertCallable: Callable<List<Long>>): List<Long> {
        val rowIdList by lazy { mutableListOf<Long>() }
        val future: Future<List<Long>>? = mIoExecutor?.submit(insertCallable)
        try {
            rowIdList.clear()
            rowIdList.addAll(future?.get() ?: mutableListOf())
        } catch (e1: InterruptedException) {
            e1.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
        return rowIdList
    }

    fun insertUsers(userList: List<UserDataClass>): List<Long> = executeListInsertTasks {
        mDao?.insertUsers(
            userList
        )
    }

    fun getUsersList(): List<UserDataClass> {
        val usersList by lazy { mutableListOf<UserDataClass>() }
        val getUsersListCallable =
            Callable { mDao?.getUsersList() as MutableList<UserDataClass> }
        val future: Future<MutableList<UserDataClass>>? =
            mIoExecutor?.submit(getUsersListCallable)
        try {
            usersList.clear()
            usersList.addAll(future?.get() ?: mutableListOf())
        } catch (e1: InterruptedException) {
            e1.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
        return usersList
    }

}

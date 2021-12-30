package com.assignment.userassignment.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.assignment.userassignment.databinding.ActivityUsersBinding
import com.assignment.userassignment.main.adapter.UserListAdapter
import com.assignment.userassignment.main.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityUsersBinding
    private val mViewModel: UserViewModel by viewModels()
    private val userAdapter by lazy { UserListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initViews()
        setUpObservers()
        fetchUsers()
    }

    private fun initViews() {
        mBinding.rvUsers.adapter = userAdapter
    }

    private fun fetchUsers() {
        mViewModel.fetchUsers()
    }

    private fun setUpObservers() {
        mViewModel.usersData.observe(this, {
            mViewModel.saveToDB(it)
        })

        mViewModel.usersListInsertResponse.observe(this, {
            mViewModel.getUsersFromDb()
        })

        mViewModel.usersLists.observe(this, {
            userAdapter.updateList(it)
        })
    }

}
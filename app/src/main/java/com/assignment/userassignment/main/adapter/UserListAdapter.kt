package com.assignment.userassignment.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.assignment.databinding.ItemUserLayoutBinding
import com.assignment.userassignment.main.dataclass.UserDataClass

class UserListAdapter() :
    RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    var userDataClass = mutableListOf<UserDataClass>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder(
            ItemUserLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bindPost(userDataClass[position])
    }

    class UserListViewHolder(private val mBinding: ItemUserLayoutBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bindPost(userDataClass: UserDataClass) {
            mBinding.user = userDataClass
        }
    }

    override fun getItemCount(): Int = userDataClass.size

    fun updateList(it: List<UserDataClass>?) {
        userDataClass.clear()
        it?.let { it1 ->
            userDataClass.addAll(it1)
            notifyItemRangeChanged(0, it.size)
        }
    }
}
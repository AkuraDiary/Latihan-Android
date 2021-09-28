package com.example.submission3belajarfundamentalaplikasiandroid.ui.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.submission3belajarfundamentalaplikasiandroid.databinding.ListLayoutUserBinding
import com.example.submission3belajarfundamentalaplikasiandroid.user.User


class UserAdapter(private val dataUser: ArrayList<User>, private val clickListener:(String, View)->Unit):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

        inner class UserViewHolder(private val binding: ListLayoutUserBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(user: User, click:(String, View)->Unit){
                binding.data = user
                binding.root.transitionName = user.login
                binding.root.setOnClickListener{
                    click(user.login, binding.root)
                }
            }
        }

    @SuppressLint("NotifyDataSetChanged")
    fun setUserData(Items: List<User>){
        dataUser.apply{
            clear()
            addAll(Items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ListLayoutUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        return holder.bind(dataUser[position], clickListener)
    }

    override fun getItemCount(): Int {
        return dataUser.size
    }

}
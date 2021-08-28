package com.example.submission2belajarfundamentalaplikasiandroid.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.submission2belajarfundamentalaplikasiandroid.databinding.ListLayoutUserBinding
import com.example.submission2belajarfundamentalaplikasiandroid.user.User


class AdapterUser(private val dataUser: ArrayList<User>, private val clickListener:(String,View)->Unit):RecyclerView.Adapter<AdapterUser.UserViewHolder>() {

        inner class UserViewHolder(private val binding: ListLayoutUserBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(user: User, click:(String, View)->Unit){
                binding.data = user
                binding.root.transitionName = user.login
                binding.root.setOnClickListener{
                    click(user.login, binding.root)
                }
            }
        }


    fun setData(Items: List<User>){
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
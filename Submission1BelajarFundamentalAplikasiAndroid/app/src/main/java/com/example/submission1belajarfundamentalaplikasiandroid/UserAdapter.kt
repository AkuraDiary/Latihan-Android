package com.example.submission1belajarfundamentalaplikasiandroid


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission1belajarfundamentalaplikasiandroid.DetailUser.Companion.EXTRA_USER_DATA
//import com.bumptech.glide.request.RequestOptions
import com.example.submission1belajarfundamentalaplikasiandroid.databinding.UserItemBinding

class UserAdapter:RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    private val dataListUser = ArrayList<Person>()
    fun setData(data: List<Person>){
        dataListUser.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val tampil = UserItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return UserViewHolder(tampil)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(dataListUser[position])
    }

    override fun getItemCount(): Int {
        return dataListUser.size
    }
    
    inner class UserViewHolder(private val view: UserItemBinding):RecyclerView.ViewHolder(view.root) {

        fun bind(user:Person){
            view.apply{
                txtUsername.text = user.username
                txtName.text = user.name
            }

            Glide.with(itemView.context)
                .load(user.avatar)
                //.apply(RequestOptions.circleCropTransform())
                .into(view.imgAvatar)

            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetailUser::class.java)
                intent.putExtra(EXTRA_USER_DATA, user)
                itemView.context.startActivity(intent)
            }
        }




    }

}
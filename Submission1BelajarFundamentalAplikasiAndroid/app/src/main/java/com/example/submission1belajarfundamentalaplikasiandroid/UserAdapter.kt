package com.example.submission1belajarfundamentalaplikasiandroid

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(private val context: Context, private val listUser:ArrayList<Person>):BaseAdapter() {
    @SuppressLint("InflateParams")
    override fun getView(position: Int, view : View?, viewGroup: ViewGroup):View{
        var itemView = view
        if(itemView==null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            itemView = inflater.inflate(R.layout.user_item, null, true)
        }
        val viewHolder = ViewHolder(itemView as View)
        val user = getItem(position) as Person
        //viewHolder.bind(user)
        return itemView
    }

    override fun getItem(position: Int): Any {
        return listUser[position]
    }

    override fun getItemId(i:Int):Long{
        return i.toLong()
    }

    override fun getCount(): Int {
        return listUser.size
    }

    private inner class ViewHolder(view: View){
        private val img_avatar: CircleImageView = view.findViewById(R.id.img_avatar)
        private val Name: TextView = view.findViewById(R.id.txt_name)
        //private val Location: TextView = view.findViewById(R.id.txt_location)
        //private val Company: TextView = view.findViewById(R.id.txt_company)

        /*fun bind(user: Person){
            user.avatar?.let{img_avatar.setImageResource(it)}
            Name.text = user.name
            Location.text = user.location
            Company.text = user.company
        }*/
    }
}
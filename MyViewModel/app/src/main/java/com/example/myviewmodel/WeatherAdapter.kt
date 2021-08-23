package com.example.myviewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myviewmodel.databinding.WeatherItemsBinding

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>(){

    private val  mData = ArrayList<WeatherItems>()

    fun setData(items: ArrayList<WeatherItems>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): WeatherViewHolder {
        val mView = LayoutInflater.from(viewGroup.context).inflate(R.layout.weather_items, viewGroup, false)
        return WeatherViewHolder(mView)
    }

    override fun onBindViewHolder(weatherViewHolder: WeatherViewHolder, position: Int) {
        weatherViewHolder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }
    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = WeatherItemsBinding.bind(itemView)
        fun bind(weatherItems: WeatherItems) {
            with(itemView){
                binding.textCity.text = weatherItems.name
                binding.textTemp.text = weatherItems.temperature
                binding.textDesc.text = weatherItems.description
            }
        }
    }
}
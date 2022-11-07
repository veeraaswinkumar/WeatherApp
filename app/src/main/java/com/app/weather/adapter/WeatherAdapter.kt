package com.app.weather.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.weather.R
import com.app.weather.extension.getDateFromTimeStamp
import com.app.weather.extension.getDayFromTimeStamp
import com.app.weather.extension.getTimeFromTimeStamp
import com.app.weather.model.WeatherModel
import kotlinx.android.synthetic.main.item_weather.view.*

class WeatherAdapter(val context: Context, var it: WeatherModel) : RecyclerView.Adapter<WeatherAdapter.MyViewHolder>() {

    class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(context: Context, it: WeatherModel, position: Int) {
            for (element in it.list[position].weather){
                view.tvTempType.text =  element.description
            }
            view.tvDay.text = it.list[position].dt.toString().getDayFromTimeStamp()
            view.tvDate.text = it.list[position].dt.toString().getDateFromTimeStamp()
            view.tvTime.text = it.list[position].dt.toString().getTimeFromTimeStamp()
            view.tvTemp.text = "${it.list[position].main.temp} ${context.getString(R.string.degree_celsius)}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
         return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_weather,parent,false))
    }

    override fun getItemCount(): Int = it.list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(context,it,position)
    }
}
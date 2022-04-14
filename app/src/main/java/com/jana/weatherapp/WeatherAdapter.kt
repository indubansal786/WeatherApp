package com.jana.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jana.weatherapp.databinding.TextRowItemBinding

class WeatherAdapter(var clickListener: ClickListener) : RecyclerView.Adapter<WeatherAdapter.WeatherItem>()  {

   private var list= ArrayList<WeatherDataModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItem {
            val binding = TextRowItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherItem(binding)
    }

    override fun onBindViewHolder(holder: WeatherItem, position: Int) {
       holder.bind(list[position])
        holder.itemBinding.llRoot.setOnClickListener {
clickListener.onItemClickListener(position)
        }
    }

    fun refreshList( tempList: ArrayList<WeatherDataModel>){
        list.clear()
        list.addAll(tempList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    class WeatherItem( val itemBinding: TextRowItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(paymentBean: WeatherDataModel) {
            itemBinding.text.text = paymentBean.timepoint.toString()
            itemBinding.text1.text = paymentBean.temp2m.toString()

        }
    }

    interface ClickListener{
        fun onItemClickListener(position: Int)
    }

}
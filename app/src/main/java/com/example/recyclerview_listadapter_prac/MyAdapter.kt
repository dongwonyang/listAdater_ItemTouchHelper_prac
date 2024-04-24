package com.example.recyclerview_listadapter_prac

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_listadapter_prac.databinding.ItemFoodBinding
import java.util.Collections

class MyAdapter
    : ListAdapter<String, MyAdapter.ViewHolder>(Callback){
    object Callback: DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(private val binding: ItemFoodBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: String){
            binding.tvFoodname.text = data
        }

        fun setBackground(color: Int){
            binding.tvFoodname.setBackgroundColor(color)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun moveItem(fromPosition: Int, toPosition:Int){
        val newList = currentList.toMutableList()
        Collections.swap(newList, fromPosition, toPosition)
        submitList(newList)
    }

    fun removeItem(position:Int){
        val newList = currentList.toMutableList()
        newList.removeAt(position)
        submitList(newList)
    }
}
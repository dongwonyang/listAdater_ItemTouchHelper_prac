package com.example.recyclerview_listadapter_prac

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter.ViewBinder
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.recyclerview_listadapter_prac.databinding.ItemFoodBinding
import com.example.recyclerview_listadapter_prac.databinding.ItemMenuBinding
import java.util.Collections

class MyAdapter
    : ListAdapter<MyItem, MyAdapter.ViewHolder>(Callback){
    object Callback: DiffUtil.ItemCallback<MyItem>(){
        override fun areItemsTheSame(oldItem: MyItem, newItem: MyItem): Boolean {
            return oldItem.name == newItem.name
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: MyItem, newItem: MyItem): Boolean {
            return oldItem == newItem
        }
    }

    private val SHOW_MENU = 1
    private val HIDE_MENU = 2

    open class ViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root){
        open fun bind(data: MyItem){
        }
    }
    class MenuViewHolder(private val binding: ItemMenuBinding)
        : ViewHolder(binding){

    }

    class MyViewHolder(private val binding: ItemFoodBinding)
        :ViewHolder(binding){
            override fun bind(data: MyItem){
                binding.tvFoodname.text = data.name
            }
        fun setBackground(color: Int){
            binding.tvFoodname.setBackgroundColor(color)
        }

        fun getView(): View {
            return itemView
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(currentList[position].isShowMenu)  return SHOW_MENU
        else return HIDE_MENU
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when(viewType){
            SHOW_MENU -> {
                val binding =
                    ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MenuViewHolder(binding)
            }
            else -> {
                val binding =
                    ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
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

    fun showMenu(position: Int) {
        for (i in 0 until currentList.size) {
            currentList[i].isShowMenu = false
        }
        currentList[position].isShowMenu = true
        notifyDataSetChanged()
    }


    fun isMenuShown(): Boolean {
        for (i in 0 until currentList.size) {
            if (currentList[i].isShowMenu) {
                return true
            }
        }
        return false
    }

    fun closeMenu() {
        for (i in 0 until currentList.size) {
            currentList[i].isShowMenu = false
        }
        notifyDataSetChanged()
    }
}
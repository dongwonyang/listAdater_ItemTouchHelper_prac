package com.example.recyclerview_listadapter_prac

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview_listadapter_prac.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val adapter: MyAdapter by lazy{
        MyAdapter()
    }
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            val data = mutableListOf<MyItem>()
            FoodList.foodList.forEach{
                data.add(MyItem(it, false))
            }
            adapter.submitList(data)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            val dividerItemDecoration = DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
            recyclerView.addItemDecoration(dividerItemDecoration)

            val itemTouchCallback = ItemTouchHelper(ItemTouchCallback(recyclerView))
            itemTouchCallback.attachToRecyclerView(recyclerView)

            recyclerView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY -> adapter.closeMenu() }
        }
    }
}
package com.example.recyclerview_listadapter_prac

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
            adapter.submitList(FoodList.foodList)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            val dividerItemDecoration = DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
            recyclerView.addItemDecoration(dividerItemDecoration)

            val itemTouchCallback = ItemTouchHelper(ItemTouchCallback(recyclerView))
            itemTouchCallback.attachToRecyclerView(recyclerView)
        }
    }
}
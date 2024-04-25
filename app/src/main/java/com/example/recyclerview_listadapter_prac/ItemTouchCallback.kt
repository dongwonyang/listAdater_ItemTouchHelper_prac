package com.example.recyclerview_listadapter_prac

import android.graphics.Canvas
import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class ItemTouchCallback(
    private val recyclerView: RecyclerView
) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT
) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        (recyclerView.adapter as MyAdapter).moveItem(
            viewHolder.layoutPosition,
            target.layoutPosition
        )
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        (recyclerView.adapter as MyAdapter).removeItem(viewHolder.layoutPosition)
        (recyclerView.adapter as MyAdapter).showMenu(viewHolder.layoutPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        when (actionState) {
            ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.ACTION_STATE_SWIPE -> {
                (viewHolder as MyAdapter.MyViewHolder).setBackground(Color.RED)
            }
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        (viewHolder as MyAdapter.MyViewHolder).setBackground(Color.WHITE)
    }


    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val itemView = viewHolder.itemView
        with(itemView) {
            if (dX > 0) { // 오른쪽으로 스와이프한 경우
                background.setBounds(
                    left,
                    top,
                    left + dX.toInt(),
                    bottom
                )
            } else if (dX < 0) { // 왼쪽으로 스와이프한 경우
                background.setBounds(
                    right + dX.toInt(),
                    top,
                    right,
                    bottom
                )
            } else { // 스와이프하지 않은 경우
                background.setBounds(0, 0, 0, 0)
            }
            background.draw(c)
        }

        getDefaultUIUtil().onDraw( //고정
            c,
            recyclerView,
            itemView,
            150f,
            dY,
            actionState,
            isCurrentlyActive
        )
    }

}
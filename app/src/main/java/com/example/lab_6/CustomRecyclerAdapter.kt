package com.example.lab_6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

interface CellClickListener {
    fun onCellClickListener(data: AnswerItem)
}

class CustomRecyclerAdapter(
    private val values: List<AnswerItem>,
    private val cellClickListener: CellClickListener
) : RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textValue?.text = values[position].answer.toString()

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(values[position])
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textValue: TextView? = null

        init {
            textValue = itemView.findViewById(R.id.list_value)
        }
    }
}
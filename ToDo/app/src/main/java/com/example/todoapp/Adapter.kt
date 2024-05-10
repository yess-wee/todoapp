package com.example.todoapp

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ViewBinding

class Adapter(var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CardInfo) {
            binding.title.text = data.title
            binding.priority.text = data.priority

            when (data.priority.toLowerCase()) {
                "high" -> binding.mylayout.setBackgroundColor(Color.parseColor("#F05454"))
                "medium" -> binding.mylayout.setBackgroundColor(Color.parseColor("#EDC988"))
                else -> binding.mylayout.setBackgroundColor(Color.parseColor("#00917C"))
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, UpdateCard::class.java)
                intent.putExtra("id", adapterPosition) // use adapterPosition instead of position
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}

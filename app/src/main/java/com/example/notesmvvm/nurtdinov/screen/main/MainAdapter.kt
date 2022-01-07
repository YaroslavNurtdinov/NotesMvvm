package com.example.notesmvvm.nurtdinov.screen.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesmvvm.nurtdinov.R
import com.example.notesmvvm.nurtdinov.database.room.entity.AppNote

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainHolder>() {
    private var listNotes = emptyList<AppNote>()

    class MainHolder (view:View): RecyclerView.ViewHolder(view){
        val titleNote: TextView = view.findViewById(R.id.item_note_title)
        val descNote: TextView = view.findViewById(R.id.item_note_desc)
        val timeNote: TextView = view.findViewById(R.id.item_note_time)
    }

    override fun onViewAttachedToWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener {
            MainFragment.click(listNotes[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener(null)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.titleNote.text = listNotes[position].title
        holder.descNote.text = listNotes[position].desc
        holder.timeNote.text = listNotes[position].time
    }

    override fun getItemCount(): Int = listNotes.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<AppNote>) {
        listNotes = list
        notifyDataSetChanged()
    }
}
package com.example.resepmasakanindo_160417065.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.resepmasakanindo_160417065.R
import com.example.resepmasakanindo_160417065.model.Resep
import kotlinx.android.synthetic.main.card_resep.view.*
import kotlinx.android.synthetic.main.fragment_tambah_resep.view.*

class ResepListAdapter(val data: ArrayList<Resep>)
    : RecyclerView.Adapter<ResepListAdapter.ResepViewHolder>(){
    class ResepViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.favorite_title_textView
        val kategori: TextView = view.Kategori
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResepViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_resep, parent, false)
        return ResepViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResepViewHolder, position: Int) {
        holder.title.text = data[position].title
        holder.kategori.text = data[position].kategoriId.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateList(newList: List<Resep>) {
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
    }
}
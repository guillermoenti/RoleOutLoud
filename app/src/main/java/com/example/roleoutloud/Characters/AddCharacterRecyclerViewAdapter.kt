package com.example.roleoutloud.Characters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.roleoutloud.R
import com.example.roleoutloud.databinding.ItemListAddSheetBinding

class AddCharacterRecyclerViewAdapter(val context: Context, private val sheets: List<Uri>) : RecyclerView.Adapter<AddCharacterRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = ItemListAddSheetBinding.bind(view)

        val image: ImageView = binding.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_list_add_sheet, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sheet = sheets[position]

        holder.image.setImageURI(sheet)
    }

    override fun getItemCount(): Int = sheets.size


}
package com.example.roleoutloud.Characters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roleoutloud.R
import com.example.roleoutloud.databinding.ItemListCharacterBinding

class CharacterRecyclerViewAdapter(val context: Context, private val characters: List<Character>) :
    RecyclerView.Adapter<CharacterRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_list_character, parent, false)


        // Assignem el layout al ViewHolder
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]

        // Set name
        holder.name.text = character.name

        if(character.previewImage != null) {
            holder.image.setImageURI(character.previewImage)
        }
        else{
            holder.image.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_person_24))
        }

        holder.itemView.setOnClickListener{
            val intent = Intent(context, CharacterActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = characters.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemListCharacterBinding.bind(view)

        val name: TextView = binding.characterName
        val image: ImageView = binding.itemPrevImage


    }
}
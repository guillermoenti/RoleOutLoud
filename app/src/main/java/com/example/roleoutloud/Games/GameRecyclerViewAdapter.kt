package com.example.roleoutloud.Games

import android.content.Context
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.roleoutloud.R

class GameRecyclerViewAdapter(val context: Context, val games: List<Game>) :
    RecyclerView.Adapter<GameRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_list_game, parent, false)

        // Assignem el layout al ViewHolder
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Set name
        holder.render(games[position])

        // It converts the ID to the properly color and set it to the image
        //holder.image.setColorFilter(context.getColor(color))

    }

    override fun getItemCount(): Int = games.size

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
//        val binding = ItemListGameBinding.bind(view)
        fun render(game:Game){
            view.findViewById<TextView>(R.id.gameName).text = game.name
            view.findViewById<TextView>(R.id.game_description).text = game.description

            val cardView = view.findViewById<CardView>(R.id.base_cardview)
            val arrow = view.findViewById<ImageButton>(R.id.arrow_button)
            val hiddenView = view.findViewById<LinearLayout>(R.id.hidden_view)
            arrow.setOnClickListener{
                if (hiddenView?.visibility == View.VISIBLE) {

                    // The transition of the hiddenView is carried out
                    // by the TransitionManager class.
                    // Here we use an object of the AutoTransition
                    // Class to create a default transition.
                    TransitionManager.beginDelayedTransition(
                        cardView,
                        AutoTransition()
                    )
                    hiddenView.visibility = View.GONE
                    arrow?.setImageResource(R.drawable.ic_baseline_expand_more_24)
                } else {
                    TransitionManager.beginDelayedTransition(
                        cardView,
                        AutoTransition()
                    )
                    hiddenView?.visibility = View.VISIBLE
                    arrow?.setImageResource(R.drawable.ic_baseline_expand_less_24)
                }
            }

        }




    }
}
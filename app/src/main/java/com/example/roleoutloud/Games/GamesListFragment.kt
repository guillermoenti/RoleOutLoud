package com.example.roleoutloud.Games

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roleoutloud.R
import com.example.roleoutloud.databinding.FragmentGamesListBinding


import com.google.android.material.chip.Chip


class GamesListFragment : Fragment() {

    private lateinit var b: FragmentGamesListBinding
    private val shownGames = Games.map { it } as ArrayList
    private val filters = ArrayList<String>()

    private val games = Games.map { it.name } as ArrayList


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentGamesListBinding.inflate(inflater)


        //Lo movido empieza aquí
        //Antes estaba en onViewCreated
        val gamesListView = b.gamesList
        context?.let {
            gamesListView.layoutManager = LinearLayoutManager(it)
            gamesListView.adapter = GameRecyclerViewAdapter(it, shownGames)
        }



        b.gamesSearchBar.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.item_list_text,
                games
            )
        )

        b.gamesSearchBar.setOnItemClickListener { parent, _, position, _ ->
            val selection = parent.getItemAtPosition(position).toString()

            val chip = Chip(requireContext())
            chip.text = selection
            chip.chipIcon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_cancel_24)

            chip.setOnClickListener {
                b.gamesChipGroup.removeView(it)
                filters.remove(selection)

                filterListAndRefresh()
            }

            filters.add(selection)
            b.gamesChipGroup.addView(chip)
            b.gamesSearchBar.text.clear()

            filterListAndRefresh()
        }
        //Acaba aquí



        // Inflate the layout for this fragment
        //Antes el return del binding era diferente
        return b.root
    }


    private fun filterListAndRefresh() {
        shownGames.clear()

          filters.forEach { filter ->
            shownGames.addAll(Games.filter { it.name == filter })
          }

        if(shownGames.isEmpty()){
            shownGames.addAll(Games)
        }

        b.gamesList.adapter?.notifyDataSetChanged()
    }



    //override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    //    super.onViewCreated(view, savedInstanceState)
    //
    //
    //}

    override fun onResume() {
        super.onResume()

        games.clear()
        games.addAll(Games.map { it.name })

        //binding.gamesChipGroup.notifySubtreeAccessibilityStateChanged()

        filterListAndRefresh()
        b.gamesSearchBar.invalidate()
    }
}


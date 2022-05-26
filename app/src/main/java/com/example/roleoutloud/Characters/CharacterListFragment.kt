package com.example.roleoutloud.Characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roleoutloud.R
import com.example.roleoutloud.databinding.FragmentCharacterListBinding
import com.google.android.material.chip.Chip


class CharacterListFragment : Fragment() {

    private lateinit var b: FragmentCharacterListBinding
    private val shownCharacters = Characters.map { it } as ArrayList
    private val filters = ArrayList<String>()

    private val characters = Characters.map { it.name } as ArrayList


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentCharacterListBinding.inflate(inflater)


        //Lo movido empieza aquí
        //Antes estaba en onViewCreated
        val charactersListView = b.charactersList
        context?.let {
            charactersListView.layoutManager = LinearLayoutManager(it)
            charactersListView.adapter = CharacterRecyclerViewAdapter(it, shownCharacters)
        }



        b.charactersSearchBar.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.item_list_text,
                characters
            )
        )

        b.charactersSearchBar.setOnItemClickListener { parent, _, position, _ ->
            val selection = parent.getItemAtPosition(position).toString()

            val chip = Chip(requireContext())
            chip.text = selection
            chip.chipIcon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_cancel_24)

            chip.setOnClickListener {
                b.charactersChipGroup.removeView(it)
                filters.remove(selection)

                filterListAndRefresh()
            }

            filters.add(selection)
            b.charactersChipGroup.addView(chip)
            b.charactersSearchBar.text.clear()

            filterListAndRefresh()
        }
        //Acaba aquí



        // Inflate the layout for this fragment
        //Antes el return del binding era diferente
        return b.root
    }


    private fun filterListAndRefresh() {
        shownCharacters.clear()

        filters.forEach { filter ->
            shownCharacters.addAll(Characters.filter { it.name == filter })
        }

        if(shownCharacters.isEmpty()){
            shownCharacters.addAll(Characters)
        }

        b.charactersList.adapter?.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()

        characters.clear()
        characters.addAll(Characters.map { it.name })


        filterListAndRefresh()
        b.charactersSearchBar.invalidate()
    }
}
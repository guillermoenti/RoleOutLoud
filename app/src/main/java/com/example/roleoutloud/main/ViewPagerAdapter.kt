package com.example.roleoutloud.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.roleoutloud.Characters.CharacterListFragment
import com.example.roleoutloud.Games.GamesListFragment
import java.lang.IllegalStateException

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> GamesListFragment()
            1 -> CharacterListFragment()
            else -> throw IllegalStateException()
        }
    }


}
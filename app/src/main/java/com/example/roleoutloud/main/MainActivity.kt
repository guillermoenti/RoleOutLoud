package com.example.roleoutloud.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roleoutloud.Characters.AddCharacterActivity
import com.example.roleoutloud.Games.AddGameActivity
import com.example.roleoutloud.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private lateinit var adapter: ViewPagerAdapter
    private var positionTab = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)


        val tabLayout = b.tabLayout
        adapter = ViewPagerAdapter(this)

        val pager = b.viewPager
        pager.adapter = adapter

        val tabLayoutMediator = TabLayoutMediator(
            tabLayout,
            pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                positionTab = position
                when (position) {
                    0 -> {
                            tab.text = "Partidas"

                    }

                    1 -> {
                        tab.text = "Personajes"
                    }
                }
            })

        tabLayoutMediator.attach()


        b.floatingButtonAdd.setOnClickListener {
            val intent: Intent =
            when(tabLayout.selectedTabPosition){
                0->
                    Intent(this, AddGameActivity::class.java)
                1->
                    Intent(this, AddCharacterActivity::class.java)
                else->
                    throw IllegalStateException()
            }
            startActivity(intent)
        }


    }


}
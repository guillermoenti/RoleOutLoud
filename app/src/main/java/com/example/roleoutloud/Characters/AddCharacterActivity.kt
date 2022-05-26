package com.example.roleoutloud.Characters

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roleoutloud.Games.Games
import com.example.roleoutloud.R
import com.example.roleoutloud.databinding.ActivityAddCharacterBinding

class AddCharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCharacterBinding

    private var charImage: Uri? = null
    private var sheetImages: List<Uri> = listOf()
    lateinit var mRecyclerView: RecyclerView
    var mAdapter: AddCharacterRecyclerViewAdapter? = null

    private val activityForResultLauncherCharacter =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            binding.prevImage.setImageURI(uri)
            charImage = uri
        }

    private val activityForResultLauncherSheet =
        registerForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) { uriArray ->
            sheetImages = uriArray
            //Update the recycler view
            mAdapter?.updateData(sheetImages)
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Init recycler view

        mRecyclerView = binding.recyclerViewCharacterSheets
        //Horizontal RecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mAdapter = AddCharacterRecyclerViewAdapter(this, sheetImages)
        mRecyclerView.adapter = mAdapter

        //Click a imagen para añadir imagen del personaje
        binding.prevImage.setOnClickListener {

            activityForResultLauncherCharacter.launch(arrayOf("image/*"))
        }

        //Lo que tiene que ver con el selector de partidas

        val gamesList = arrayListOf<String>()

        Games.forEach {
            gamesList.add(it.name)
        }

        val option: ArrayList<String> = gamesList
        val adapter = ArrayAdapter(this, R.layout.item_selector_game, option)
        binding.autoCompleteText.setText(adapter.getItem(0).toString(), false)
        binding.autoCompleteText.setAdapter(adapter)

        //Botón para añadir hojas
        binding.addSheets.setOnClickListener {

            activityForResultLauncherSheet.launch(arrayOf("image/*"))
        }

        //Para completar la creación
        binding.addCharacterButton.setOnClickListener {
            val characterName = binding.characterNameInput.text.toString()

            if (binding.characterNameInput.text.toString() != "") {
                val tmpCharacter = Character(characterName)
                tmpCharacter.previewImage = charImage

                Characters.add(tmpCharacter)
                finish()
            } else {
                binding.characterNameInput.error = "Debes poner un nombre al Personaje"
            }
        }

        val addCharactersListView = binding.recyclerViewCharacterSheets


        //b.addCharacterList.adapter = AddCharacterRecyclerViewAdapter(this, sheetImages)


    }

    override fun onResume() {
        super.onResume()
    }


}
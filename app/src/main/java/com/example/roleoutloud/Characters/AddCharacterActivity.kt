package com.example.roleoutloud.Characters

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roleoutloud.Games.Games
import com.example.roleoutloud.R
import com.example.roleoutloud.databinding.ActivityAddCharacterBinding

class AddCharacterActivity : AppCompatActivity() {

    private lateinit var b: ActivityAddCharacterBinding

    private var charImage: Uri? = null
    private var sheetImages: List<Uri> = listOf()


    private val activityForResultLauncherCharacter =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            b.prevImage.setImageURI(uri)
            charImage = uri
        }

    private val activityForResultLauncherSheet =
        registerForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) { uriArray ->
            sheetImages = uriArray

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b = ActivityAddCharacterBinding.inflate(layoutInflater)
        setContentView(b.root)

        //Click a imagen para añadir imagen del personaje
        b.prevImage.setOnClickListener{

            activityForResultLauncherCharacter.launch(arrayOf("image/*"))
        }

        //Lo que tiene que ver con el selector de partidas

        val gamesList = arrayListOf<String>()

        Games.forEach{
            gamesList.add(it.name)
        }

        val option : ArrayList<String> =  gamesList
        val adapter = ArrayAdapter(this, R.layout.item_selector_game, option)
        b.autoCompleteText.setText(adapter.getItem(0).toString(), false)
        b.autoCompleteText.setAdapter(adapter)

        //Botón para añadir hojas
        b.addSheets.setOnClickListener{

            activityForResultLauncherSheet.launch(arrayOf("image/*"))
        }

        //Para completar la creación
        b.addCharacterButton.setOnClickListener {
            

            val characterName = b.characterNameInput.text.toString()

            if (b.characterNameInput.text.toString() != "") {
                val tmpCharacter = Character(characterName)
                tmpCharacter.previewImage = charImage

                Characters.add(tmpCharacter)
                finish()
            } else {
                b.characterNameInput.error = "Debes poner un nombre al Personaje"
            }
        }

        val addCharactersListView = b.addCharacterList
        this?.let {
            addCharactersListView.layoutManager = LinearLayoutManager(it)
            addCharactersListView.adapter = AddCharacterRecyclerViewAdapter(it, sheetImages)
        }

        //b.addCharacterList.adapter = AddCharacterRecyclerViewAdapter(this, sheetImages)


    }

    override fun onResume() {
        super.onResume()

        b.addCharacterList.adapter?.notifyDataSetChanged()
    }



}
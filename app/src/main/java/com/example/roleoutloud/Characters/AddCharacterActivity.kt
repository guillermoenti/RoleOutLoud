package com.example.roleoutloud.Characters

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Messenger
import android.view.*
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roleoutloud.Games.Games
import com.example.roleoutloud.R
import com.example.roleoutloud.databinding.ActivityAddCharacterBinding
import java.security.AccessController.getContext


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
                tmpCharacter.characterSheetImages = sheetImages

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


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
//        super.onActivityResult(requestCode, resultCode, data)
//        //if ok user selected a file
//        if (resultCode == RESULT_OK) {
//            val sourceTreeUri = data.data
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                getContext().getContentResolver().takePersistableUriPermission(
//                    sourceTreeUri,
//                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
//                )
//            }
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            val sourceTreeUri = data?.data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                sourceTreeUri?.let {
                    this.getContentResolver().takePersistableUriPermission(
                        it,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }
            }
        }
    }
}
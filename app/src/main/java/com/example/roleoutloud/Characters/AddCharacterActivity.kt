package com.example.roleoutloud.Characters

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.roleoutloud.databinding.ActivityAddCharacterBinding

class AddCharacterActivity : AppCompatActivity() {

    private lateinit var b: ActivityAddCharacterBinding

    private var charImage: Uri? = null

    private val activityForResultLauncher =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            b.prevImage.setImageURI(uri)
            charImage = uri
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b = ActivityAddCharacterBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.prevImage.setOnClickListener{

            activityForResultLauncher.launch(arrayOf("image/*"))
        }

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
    }
}
package com.example.roleoutloud.Characters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roleoutloud.databinding.ActivityCharacterBinding

class CharacterActivity : AppCompatActivity() {

    private lateinit var b: ActivityCharacterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b = ActivityCharacterBinding.inflate(layoutInflater)

        setContentView(b.root)

        b.characterSheetImage.setImageURI(Characters[2].characterSheetImages[0])



    }
}
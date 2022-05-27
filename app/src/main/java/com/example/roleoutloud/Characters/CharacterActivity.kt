package com.example.roleoutloud.Characters

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.roleoutloud.databinding.ActivityCharacterBinding

class CharacterActivity : AppCompatActivity() {

    private lateinit var b: ActivityCharacterBinding

    var id = 0
    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b = ActivityCharacterBinding.inflate(layoutInflater)

        setContentView(b.root)

        id = intent.extras?.getInt("ID") ?: return


        if(Characters[id].characterSheetImages.isNotEmpty()) {
            ActualizeImage()
            b.textView2.visibility = View.INVISIBLE
        }


        b.nextButton.setOnClickListener {
            if(index != Characters[id].characterSheetImages.size){
                index++
                ActualizeImage()
            }
        }

        b.previousButton.setOnClickListener {
            if(index != Characters[id].characterSheetImages.size){
                index--
                ActualizeImage()
            }
        }
    }

    fun ActualizeImage(){
        b.characterSheetImage.setImageURI(Characters[id].characterSheetImages[index])
    }
}
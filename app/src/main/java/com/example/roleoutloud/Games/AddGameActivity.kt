package com.example.roleoutloud.Games

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roleoutloud.databinding.ActivityAddGameBinding

class AddGameActivity : AppCompatActivity() {

    private lateinit var b: ActivityAddGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b = ActivityAddGameBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.addGameButton.setOnClickListener {
            val gameName = b.gameNameInput.text.toString()
            val gameDescription = b.gameDescriptionInput.text.toString()

            if (b.gameNameInput.text.toString() != "") {
                if(b.gameDescriptionInput.text.toString() != ""){
                    Games.add(Game(gameName, gameDescription))
                }
                else{
                    Games.add(Game(gameName))
                }

                finish()
            } else {
                b.gameNameInput.error = "Debes poner un nombre a la Partida"
            }
        }
    }
}
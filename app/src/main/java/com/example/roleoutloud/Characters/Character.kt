package com.example.roleoutloud.Characters

import android.media.Image
import android.net.Uri
import com.example.roleoutloud.Games.Game

class Character (
    var name: String,
    var game: Game? = null,
    var previewImage: Uri? = null,
    var characterSheetImages: ArrayList<Image> = ArrayList()
)



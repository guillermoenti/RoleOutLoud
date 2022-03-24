package com.example.roleoutloud.Games

object Games: ArrayList<Game>() {

    init{
        add(Game("Partida 1 ya creada"))
        add(Game("Partida 2 ya creada", "Esta partida ya la tenías creada de antes"))
    }
}
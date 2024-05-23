package com.example.jobcpp.ViewModel.Service

import android.util.Log
import com.example.jobcpp.Model.DTO.GameState
import com.example.jobcpp.Model.DTO.State
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DatabaseGame {

    fun saveGameState(gameState: State) {
        // Obtén una referencia a la base de datos
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("gameState")

        // Guarda el objeto GameState en la base de datos
        myRef.setValue(gameState).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.e("SAVE STATE","GameState guardado exitosamente.")
            } else {
                Log.e("SAVE STATE","Error al guardar GameState: ${task.exception?.message}")
            }
        }
    }
}
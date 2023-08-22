package com.example.androidstructure.Misc

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.androidstructure.presentation.modules.base.StartGame
import com.google.api.Context
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object GameDetails {

    private lateinit var selectedCategories: MutableList<String>
    private lateinit var alphabets: MutableList<String>
    private var selectedRounds: Int = 0
    private var countdownValue: Int = 0
    private lateinit var createdName: String
    lateinit var playingPerson: String
    lateinit var roomID: String
    private val db = Firebase.firestore
    var matchNo: Int = 0;
    /// initiate db

    fun setItems(
        selectedRounds: Int,
        countdownValue: Int,
        createdName: String,
        selectedCategories: MutableList<String>,
        roomID: String,
        playingPerson: String = ""
    ) {
        this.selectedCategories = selectedCategories
        this.selectedRounds = selectedRounds
        this.countdownValue = countdownValue
        this.createdName = createdName
        this.roomID = roomID

        if (playingPerson == "") {
            this.playingPerson = createdName
        } else {
            this.playingPerson = playingPerson
        }

    }

    fun getRandomAlphabet(callback: (String) -> Unit) {
        val alphabet = ('a'..'z').toList()
        val randomIndex = (0 until alphabet.size).random()
        alphabets.add(alphabet[randomIndex].toString())

        val user = hashMapOf(
            "alphabets" to alphabets,
        )

        db.collection("match")
            .document(roomID)
            .set(user)
            .addOnSuccessListener { documentReference ->
                callback(alphabet[randomIndex].toString())
                matchNo + 1;
            }
            .addOnFailureListener { e ->
                callback("")
                Log.w(ContentValues.TAG, "Error adding document", e)
            }
    }

    fun mixCheckers() {
// get players array from DB then check if there are more than 1
        //mix then save to other array in DB
        db.document("match/" + roomID)
            .get().addOnSuccessListener { document ->
                val a = document.get("players") as MutableList<String>
                if (a.size > 1) {
                    val mixedPlayersArray = a.shuffle()
                    db.document("match/" + roomID)
                        .set(mixedPlayersArray)
                        .addOnSuccessListener { document ->
                            Log.w(TAG, "adding mixedplayers success")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                } else {
                    showToast("Already Exists")
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }


    private fun showToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun putData(
        name: String,
        place: String,
        animal: String,
        thing: String,
        food: String,
        bird: String
    ) {
        val results = hashMapOf(
            "name" to name,
            "place" to place,
            "animal" to animal,
            "thing" to thing,
            "food" to food,
            "bird" to bird,
        )
        db.collection("match/" + roomID)
            .document(matchNo.toString())
            .set(results)
            .addOnSuccessListener { documentReference ->
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}
package com.example.androidstructure.presentation.modules.base

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.example.androidstructure.Misc.GameDetails
import com.example.androidstructure.R
import com.example.androidstructure.databinding.CreateGameBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateGame : AppCompatActivity() {

    private val db = Firebase.firestore

    private lateinit var binding: CreateGameBinding

    private val selectedCategories = mutableListOf<String>()
    private var selectedRounds: Int = 2
    private var countdownValue: Int = 0
    private lateinit var roomID: String
    private var createdName: String = ""
    private val players = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createdName = binding.createdName.text.toString()
        binding.but2.setOnClickListener {
            if (validateFields()) {
                insertInDB()
            }
        }
    }

    private fun validateFields(): Boolean {
        val nameText = binding.createdName.text.toString()
        val validName = nameText.isNotBlank()

        val checkedCheckboxes = listOf(
            binding.checkBox,
            binding.checkBox2,
            binding.checkBox3,
            binding.checkBox4,
            binding.checkBox5,
            binding.checkBox6
        ).filter { it.isChecked }
        val validCheckboxCount = checkedCheckboxes.size == 4

        val validRadioButton = binding.radioButton.isChecked ||
                binding.radioButton4.isChecked || binding.radioButton5.isChecked

        countdownValue = binding.countdownTime.text.toString().toIntOrNull() ?: 0
        createdName = nameText

        val validCountdown = countdownValue >= 10

        roomID =  binding.roomId.text.toString()
        val validGameId = roomID.count() == 4

        if (!validName) {
            Toast.makeText(this, "Please enter a valid name", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!validCheckboxCount) {
            Toast.makeText(this, "Select exactly 4 categories", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!validRadioButton) {
            Toast.makeText(this, "Select one number of rounds", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!validCountdown) {
            Toast.makeText(this, "Countdown timer must be 10 seconds", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!validGameId) {
            Toast.makeText(this, "Game ID must be a 4-digit number", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun insertInDB() {
        // Add a new document with a generated ID
        listOf(
            binding.checkBox,
            binding.checkBox2,
            binding.checkBox3,
            binding.checkBox4,
            binding.checkBox5,
            binding.checkBox6
        ).forEach { checkbox ->
            if (checkbox.isChecked) {
                selectedCategories.add(checkbox.text.toString())
            }
        }

        selectedRounds = when {
            binding.radioButton.isChecked -> 5
            binding.radioButton4.isChecked -> 7
            binding.radioButton5.isChecked -> 10
            else -> 2 // Default value if none is selected
        }

//initiate array
//        add host to array
//                save array to DB

        players.add(createdName)

        val user = hashMapOf(
            "Categories" to selectedCategories,
            "countdownTime" to countdownValue,
            "createdBy" to createdName,
            "rounds" to selectedRounds,
            "players" to  players
        )

        db.collection("match")
            .document(binding.roomId.text.toString())
            .set(user)
            .addOnSuccessListener { documentReference ->
                GameDetails.setItems(selectedRounds, countdownValue, createdName, selectedCategories, roomID)
                val intent = Intent(this, StartGame::class.java)
                startActivity(intent)
                Log.d(TAG, "DocumentSnapshot added with ID: "+binding.roomId.text.toString())
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Insertion into DB Failed", Toast.LENGTH_SHORT).show()
                Log.w(TAG, "Error adding document", e)
            }
    }
}
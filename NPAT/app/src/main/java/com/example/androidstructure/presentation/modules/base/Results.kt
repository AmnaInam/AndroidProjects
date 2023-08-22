package com.example.androidstructure.presentation.modules.base

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidstructure.Misc.GameDetails
import com.example.androidstructure.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results)

//TODO start result activity
//    then get those 2 array from DB
//    get index of playing Person
//    get person from mixedPerson by giving that index
//    get document of that person

        val db = Firebase.firestore
        val textName: TextView = findViewById(R.id.textName)
        val textPlace: TextView = findViewById(R.id.place)
        val textAnimal: TextView = findViewById(R.id.animal)
        val textThing: TextView = findViewById(R.id.thing)
        val textFood: TextView = findViewById(R.id.food)
        val score: TextView = findViewById(R.id.score_total)
        val scoreNameDisplay: TextView = findViewById(R.id.name_disp)
        val scorePlaceDisplay: TextView = findViewById(R.id.place_disp)
        val scoreAnimalDisplay: TextView = findViewById(R.id.animal_disp)
        val scoreThingDisplay: TextView = findViewById(R.id.thing_disp)
        val scoreFoodDisplay: TextView = findViewById(R.id.food_disp)
        val scoreBirdDisplay: TextView = findViewById(R.id.bird_disp)
        val nameTF: EditText = findViewById(R.id.nameTF)
        val placeTF: EditText = findViewById(R.id.placeTF)
        val animalTF: EditText = findViewById(R.id.animalTF)
        val thingTF: EditText = findViewById(R.id.thingTF)
        val foodTF: EditText = findViewById(R.id.foodTF)
        val birdTF: EditText = findViewById(R.id.birdTF)

        val scores = mutableListOf<String>()

        val nameScore = nameTF.text.toString().toIntOrNull() ?: 0
        val placeScore = placeTF.text.toString().toIntOrNull() ?: 0
        val animalScore = animalTF.text.toString().toIntOrNull() ?: 0
        val thingScore = thingTF.text.toString().toIntOrNull() ?: 0
        val foodScore = foodTF.text.toString().toIntOrNull() ?: 0
        val birdScore = birdTF.text.toString().toIntOrNull() ?: 0

        val totalScore = nameScore + placeScore + animalScore + thingScore + foodScore + birdScore

        score.text = totalScore.toString()

        scores.clear()
        scores.add(nameScore.toString())
        scores.add(placeScore.toString())
        scores.add(animalScore.toString())
        scores.add(thingScore.toString())
        scores.add(foodScore.toString())
        scores.add(birdScore.toString())

        scoreNameDisplay.text = scores[0]
        scorePlaceDisplay.text = scores[1]
        scoreAnimalDisplay.text = scores[2]
        scoreThingDisplay.text = scores[3]
        scoreFoodDisplay.text = scores[4]
        scoreBirdDisplay.text = scores[5]



        db.document("match/" + GameDetails.roomID)
            .get().addOnSuccessListener { document ->
                val players = document.get("players") as MutableList<String>
                val playersIndex = players.indexOf(GameDetails.playingPerson)

                val mixedPlayers = document.get("mixedPlayersArray") as MutableList<String>
                val checkPerson =  mixedPlayers[playersIndex]

                db.document("match/" + GameDetails.roomID+"/"+checkPerson+"/"+GameDetails.matchNo)
                    .get().addOnSuccessListener { document2 ->
                        textName.text = document2.getString("name")
                        textPlace.text = document2.getString("place")
                        textAnimal.text = document2.getString("animal")
                        textThing.text = document2.getString("thing")
                    }
                val score = hashMapOf(
                    "scores" to scores,
                )

                //TODO:get text from textfields when button is pressed then set to its relevant
                // textview then get all values from the textviews and set them to the scores
                // array then store the scores array to the DB
                db.document("match/" + GameDetails.roomID+"/"+checkPerson+"/"+GameDetails.matchNo)
                    .set(score).addOnSuccessListener { document3 ->

                    }
            }
            .addOnFailureListener {

            }


        //  TODO
        //      Add text fields for scoring
        //      save scores in Database in this path "match/" + GameDetails.roomID+"/"+checkPerson+"/"+GameDetails.matchNo
    }
}


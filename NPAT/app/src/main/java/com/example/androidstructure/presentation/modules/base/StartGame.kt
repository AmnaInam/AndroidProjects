package com.example.androidstructure.presentation.modules.base

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidstructure.Misc.GameDetails
import com.example.androidstructure.Misc.GameDetails.putData
import com.example.androidstructure.R

class StartGame : AppCompatActivity() {
//TODO    set playingPerson in (GAME_DETAIL)on createGame activity, and join game activity
//    before starting StartGame Atcivity add person to array (createGame, join game activity)
//    complete mix person function

    private lateinit var timer: TextView
    private lateinit var name: EditText
    private lateinit var place: EditText
    private lateinit var animal: EditText
    private lateinit var thing: EditText
    private lateinit var food: EditText
    private lateinit var bird: EditText
    private lateinit var submitButton: Button
    private lateinit var randomButton: Button

    private lateinit var countDownTimer: CountDownTimer
    private lateinit var randomText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_fields)

        timer = findViewById(R.id.timer)
        name = findViewById(R.id.name_field)
        place = findViewById(R.id.place_field)
        animal = findViewById(R.id.animal_field)
        thing = findViewById(R.id.thing_field)
        food = findViewById(R.id.food_field)

        randomText = findViewById(R.id.timer)
        submitButton = findViewById(R.id.submit)
        randomButton = findViewById(R.id.randomCharButton)

        submitButton.setOnClickListener {
            submitButton.isEnabled = false // Disable button after first click
            timer.visibility = TextView.VISIBLE
            startCountdown()
        }
        randomButton.setOnClickListener {
            GameDetails.mixCheckers()

            GameDetails.getRandomAlphabet { anything ->
                if ( anything.isEmpty()){
                    Toast.makeText(this, "No alphabet present", Toast.LENGTH_SHORT).show()
                }
                randomText.text = anything
            }
        }
    }

    private fun startCountdown() {
        val initialTime = 10
        countDownTimer = object : CountDownTimer((initialTime * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                val minutes = secondsRemaining / 60
                val seconds = secondsRemaining % 60
                timer.text = "CountDown: ${String.format("%02d:%02d", minutes, seconds)}"
            }
            override fun onFinish() {
                timer.text = "CountDown: 00:00"

                filledGameFields()
                navigateToNextActivity()
            }
        }

        countDownTimer.start()
    }

    private fun navigateToNextActivity() {
        val intent = Intent(this, Results::class.java)
        startActivity(intent)
        finish() // Optional, if you want to close this activity
    }

    private fun filledGameFields(){
        putData(name = name.text.toString(),
            place = place.text.toString(),
            animal = animal.text.toString(),
            thing = thing.text.toString(),
            food = food.text.toString(),
            bird = bird.text.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}

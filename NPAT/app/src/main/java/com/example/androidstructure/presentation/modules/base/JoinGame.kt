package com.example.androidstructure.presentation.modules.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.androidstructure.Misc.GameDetails
import com.example.androidstructure.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class JoinGame : AppCompatActivity() {
    private lateinit var join: Button
    private lateinit var room: EditText
    private lateinit var name: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.join_game)

        join = findViewById(R.id.join_button)
        room = findViewById(R.id.room)
        name = findViewById(R.id.name)

        join.setOnClickListener {
            fetchPlayers()
            val intent = Intent(this, StartGame::class.java)
            startActivity(intent)
        }
    }

    fun fetchPlayers() {
        val db = Firebase.firestore
        val players = mutableListOf<String>()

        db.document("match/" + room.text.toString())
            .get()
            .addOnSuccessListener { result ->
                var players = mutableListOf<String>()
                players = result.get("players?") as MutableList<String>
                GameDetails.setItems(
                    result.getLong("rounds")?.toInt() ?: 0,
                    result.getLong("countdownTime")?.toInt() ?: 0,
                    result.getString("createdBy").toString(),
                    result.get("Categories") as MutableList<String>,
                    room.text.toString(),
                    name.text.toString()
                    )
                players.add(name.text.toString())
                db.document("match/" + room.text.toString())
                    .set(players)
            }
    }
}


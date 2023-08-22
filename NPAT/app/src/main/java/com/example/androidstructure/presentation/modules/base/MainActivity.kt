package com.example.androidstructure.presentation.modules.base

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.androidstructure.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val joinGame: Button = findViewById(R.id.b1)
        val createGame: ImageView = findViewById(R.id.i1)


        joinGame.setOnClickListener {
            val intent = Intent(this, JoinGame::class.java)
            startActivity(intent)
        }

        createGame.setOnClickListener {
            val intent = Intent(this, CreateGame::class.java)
            startActivity(intent)
        }
    }
}
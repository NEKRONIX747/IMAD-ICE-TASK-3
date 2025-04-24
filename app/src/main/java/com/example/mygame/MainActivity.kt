package com.example.mygame

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var randomNumber = 0

    private fun generateRandomNumber() {
        randomNumber = Random.nextInt(1, 10)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editTextGuess = findViewById<EditText>(R.id.editTextPlay)
        val guessButton = findViewById<Button>(R.id.buttonPlay)
        val resultText = findViewById<TextView>(R.id.textViewResult)

        generateRandomNumber()

        guessButton.setOnClickListener {
            val userGuess = editTextGuess.text.toString().toIntOrNull()
            if (userGuess == null) {
                resultText.text = "Please enter a valid number!"
                return@setOnClickListener
            }
            when {
                userGuess < randomNumber -> resultText.text = "Too low! Try again."
                userGuess > randomNumber -> resultText.text = "Too high! Try again."
                else -> {
                    resultText.text = "Congratulations! You guessed it right!"
                    generateRandomNumber()
                }

            }
            editTextGuess.text.clear()

        }



    }
}
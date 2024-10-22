//MainActivity.kt

package com.example.kotlin_test

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var texts: MutableList<MutableList<TextView>>
    private var rowCount = 7  // default to medium level
    private val colCount = 5
    private var countGames = 0
    private var countWins = 0
    private lateinit var gameCore: GameCore
    private var startRowOffset = 0

  /*  private val EMOJI_CORRECT = "🟩"  // Green for correct position
    private val EMOJI_PRESENT = "🟨"  // Yellow for wrong position
    private val EMOJI_WRONG = "⬛"    // Black for not in word*/
  private lateinit var exitDialog: Dialog

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        // Initialize exit dialog
        initExitDialog()

        texts = MutableList(7) { mutableListOf() }
        setDifficultyLevel("easy")  // Start with easy mode
    }

    private fun initExitDialog() {
        exitDialog = Dialog(this).apply {
            setContentView(R.layout.exit_dialog)
            window?.setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setCancelable(false)
        }
    }

    private fun showExitDialog() {
        exitDialog.apply {
            // Update statistics
            findViewById<TextView>(R.id.textTotalGames).text = "Total Games: $countGames"
            findViewById<TextView>(R.id.textTotalWins).text = "Total Wins: $countWins"

            // Calculate and show win rate
            val winRate = if (countGames > 0) {
                String.format("%.1f", (countWins.toFloat() / countGames.toFloat()) * 100)
            } else "0.0"
            findViewById<TextView>(R.id.textWinRate).text = "Win Rate: $winRate%"

            // Set button click listeners
            findViewById<Button>(R.id.buttonContinue).setOnClickListener {
                dismiss()
            }

            findViewById<Button>(R.id.buttonConfirmExit).setOnClickListener {
                finish()
            }

            show()
        }
    }



    private fun setDifficultyLevel(level: String) {
        // First initialize the texts
        initTexts()

        when (level) {
            "easy" -> {
                rowCount = 7
                startRowOffset = 0
                gameCore = GameCore(rowCount, startRowOffset)
                showRows(0..6)
            }
            "medium" -> {
                rowCount = 5
                startRowOffset = 1
                gameCore = GameCore(rowCount, startRowOffset)
                showRows(1..5)
            }
            "hard" -> {
                rowCount = 4
                startRowOffset = 2
                gameCore = GameCore(rowCount, startRowOffset)
                showRows(2..5)
            }
        }

        // Set event listeners after initialization
        setEventListeners()
        newRound()
    }


  /*  private fun generateShareableResult(): String {
        val sb = StringBuilder()
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        try {
            sb.append("Wordle Clone - $currentDate\n")
            sb.append("${getDifficultyText()} mode\n")
            sb.append("${if (gameCore.getResult()) "Solved" else "Failed"} in ${gameCore.getCurRow() - startRowOffset + 1}/${rowCount} tries\n\n")

            // Add emoji grid
            for (row in startRowOffset until gameCore.getCurRow() + 1) {
                for (col in 0 until colCount) {
                    val result = when (gameCore.validateChar(row, col)) {
                        gameCore.IN_PLACE -> "🟩"
                        gameCore.IN_WORD -> "🟨"
                        else -> "⬛"
                    }
                    sb.append(result)
                }
                sb.append("\n")
            }

            sb.append("\nGames: $countGames, Wins: $countWins")

            Log.d(TAG, "Generated result: ${sb.toString()}")  // Debug log
            return sb.toString()

        } catch (e: Exception) {
            Log.e(TAG, "Error generating shareable result", e)
            return "Error generating result"
        }
    }
*/
  /*  private fun getDifficultyText(): String {
        return when {
            rowCount == 7 -> "Easy"
            rowCount == 5 -> "Medium"
            else -> "Hard"
        }
    }*/


    /*private fun shareResults() {
        // Animate the share button
        findViewById<Button>(R.id.buttonShare).apply {
            animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(100)
                .withEndAction {
                    animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100)
                        .start()
                }
                .start()
        }

        // Share intent
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, generateShareableResult())
        }
        startActivity(Intent.createChooser(shareIntent, "Share your results!"))
    }
*/

    //  a null check in showRows for extra safety
    private fun showRows(range: IntRange) {
        for (row in 0 until 7) {
            if (texts[row].isNotEmpty()) {  // Add this check
                for (col in 0 until colCount) {
                    texts[row][col].visibility = if (row in range) {
                        TextView.VISIBLE
                    } else {
                        TextView.INVISIBLE
                    }
                }
            }
        }
    }


 /*   private fun updateShareButtonState() {
        findViewById<Button>(R.id.buttonShare).isEnabled = gameCore.isPouse()
    }*/




// Call this in newRound() and after each enter press

    private fun setEventListeners() {
        for (c in 90 downTo 65) { // A-Z ASCII
            val resID = resources.getIdentifier("button${c.toChar()}", "id", packageName)
            val btn = findViewById<Button>(resID)
            btn.setOnClickListener {
                if (gameCore.isPouse()) {
                    gameCore.startOver()
                    newRound()
                }
                val row = gameCore.getCurRow()
                val col = gameCore.getCurCol()
                // Adjust row check to account for offset
                if (row >= startRowOffset && row < startRowOffset + rowCount) {
                    if (gameCore.setNextChar(c.toChar())) {
                        texts[row][col].text = c.toChar().toString()
                    }
                }

            }
        }

        findViewById<Button>(R.id.buttonExit).setOnClickListener {
            showExitDialog()
        }

        val btnEnter = findViewById<Button>(R.id.buttonEnter)
        btnEnter.setOnClickListener {
            if (gameCore.isPouse()) {
                gameCore.startOver()
                newRound()
            }
            val row = gameCore.getCurRow()
            if (gameCore.enter()) {
                for (col in 0 until colCount) {
                    val id = when (gameCore.validateChar(row, col)) {
                        gameCore.IN_WORD -> R.drawable.letter_in_word
                        gameCore.IN_PLACE -> R.drawable.letter_in_place
                        else -> R.drawable.letter_not_in
                    }
                    texts[row][col].background = ContextCompat.getDrawable(this, id)
                }
                if (gameCore.getResult()) {
                    countWins++
                }
            }
        }

        val btnErase = findViewById<Button>(R.id.buttonErase)
        btnErase.setOnClickListener {
            if (gameCore.isPouse()) {
                gameCore.startOver()
                newRound()
            }
            gameCore.erase()
            val row = gameCore.getCurRow()
            val col = gameCore.getCurCol()
            texts[row][col].text = " "
        }

        findViewById<Button>(R.id.buttonEasy).setOnClickListener { setDifficultyLevel("easy") }
        findViewById<Button>(R.id.buttonMedium).setOnClickListener { setDifficultyLevel("medium") }
        findViewById<Button>(R.id.buttonHard).setOnClickListener { setDifficultyLevel("hard") }
/*
        findViewById<Button>(R.id.buttonShare).setOnClickListener {
            if (gameCore.isPouse()) {  // Only allow sharing when game is complete
                shareResults()
            }
        }*/


    }

    private fun initTexts() {
        texts.forEach { it.clear() }
        for (row in 0 until 7) {
            for (col in 0 until colCount) {
                val resID = resources.getIdentifier("text${col + 1}col${row + 1}row", "id", packageName)
                texts[row].add(findViewById(resID))
            }
        }
    }

    private fun newRound() {
        gameCore.setWord()
        // Clear all visible rows based on offset and count
        for (row in startRowOffset until startRowOffset + rowCount) {
            for (col in 0 until colCount) {
                texts[row][col].background = ContextCompat.getDrawable(this, R.drawable.letter_border)
                texts[row][col].text = " "
            }
        }
        val textGames = findViewById<TextView>(R.id.games)
        val textWins = findViewById<TextView>(R.id.wins)
        textGames.text = "Games: $countGames"
        textWins.text = "Wins: $countWins"
        countGames++
    }
}
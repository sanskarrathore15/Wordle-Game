//GameCore.kt

package com.example.kotlin_test

import kotlin.random.Random

class GameCore(private var rowCount: Int = 7, private var startRowOffset: Int = 0) {
    val IN_WORD = 0
    val IN_PLACE = 1
    val NOT_IN = 2
    private var pouse = false
    private var curRow: Int = startRowOffset  // Initialize with offset
    private var curCol: Int = 0
    private var rows = mutableListOf<MutableList<Char>>()
    private lateinit var word: String


    init {
        resetRows()
    }

    private fun resetRows() {
        rows.clear()
        // Initialize all possible rows
        for (i in 0 until 7) {
            val row = MutableList(5) { ' ' }
            rows.add(row)
        }
    }

    fun getFinalWord(): String {
        return word
    }



    fun getResult(): Boolean {
        for (row in 0 until rowCount) {
            if (rows[row].joinToString(separator = "") == word) {
                pouse = true
                return true
            }
        }
        return false
    }

    fun isPouse(): Boolean {
        return pouse
    }

    fun startOver() {
        curCol = 0
        curRow = startRowOffset  // Reset to offset instead of 0
        pouse = false
        resetRows()
        setWord()
    }

    fun getChar(row: Int, col: Int): Char {
        if (row < 0 || row >= rowCount || col < 0 || col >= 5) {
            return ' '
        }
        return rows[row][col]
    }

    fun setNextChar(c: Char): Boolean {
        if (rows[curRow][curCol] == ' ') {
            rows[curRow][curCol] = c
            if (curCol < 4) {
                curCol++
            }
            return true
        }
        return false
    }

    fun erase() {
        if (curCol > 0 && rows[curRow][curCol] == ' ') {
            curCol--
        }
        rows[curRow][curCol] = ' '
    }

    fun enter(): Boolean {
        if (curCol == 4 && curRow < (startRowOffset + rowCount)) {
            curCol = 0
            curRow++
            if (curRow == (startRowOffset + rowCount)) {
                pouse = true
            }
            return true
        }
        return false
    }


    fun validateChar(row: Int, col: Int): Int {
        if (rows[row][col] == word[col]) {
            return IN_PLACE
        } else if (rows[row][col] in word) {
            return IN_WORD
        }
        return NOT_IN
    }

    fun getCurRow(): Int {
        return curRow
    }

    fun getCurCol(): Int {
        return curCol
    }

    fun setWord() {
        val words = listOf(
            "APPLE", "TIGER", "OCEAN", "ROBOT", "SNAIL", "PANDA", "IGLOO", "LEMON",
            "MOUSE", "PIZZA", "CHAIR", "EARTH", "PIANO", "RIVER", "EAGLE", "ZEBRA",
            "CLOWN", "CLOUD", "SPOON", "TRAIN", "CLOCK", "SHOES", "SOCKS", "MAGIC",
            "COMET", "WHALE", "JELLY", "SHIRT", "LEMON", "SMILE", "MOUSE", "ANGEL",
            "OCEAN", "ROBOT", "SWORD", "SUSHI", "SNAKE", "IGLOO", "APPLE", "JUPITER"
        )
        word = words[Random.nextInt(words.size)]
    }
}

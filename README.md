# Wordle App - Documentation

## Table of Contents

1. [Introduction](#introduction)
2. [Game Features](#game-features)
   - [Splash Screen](#splash-screen)
   - [Game Counters](#game-counters)
   - [Difficulty Levels](#difficulty-levels)
   - [Gameplay Buttons](#gameplay-buttons)
     - [Erase Button](#erase-button)
     - [Enter Button](#enter-button)
     - [Exit Button](#exit-button)
   - [Statistics Menu](#statistics-menu)
3. [Gameplay Instructions](#gameplay-instructions)
4. [Conclusion](#conclusion)

## Introduction

The **Wordle Game** is an interactive word-guessing game with multiple features aimed at enhancing user experience. The game offers a variety of difficulty levels and useful controls to make gameplay engaging and challenging.

## Game Features

### Splash Screen

- The game begins with a **splash screen** that displays the title "Wordle".
- A **loading bar** indicates the game's initialization process before taking the player to the main game screen.

### Game Counters

#### Total Games Counter

- This counter keeps track of how many games have been played so far.

#### Wins Counter

- This counter keeps track of the total number of wins.

#### Win Rate

- The **win rate** is automatically calculated as a ratio of the total number of wins to the total number of games played. This is displayed in the **Statistics Menu**.

### Difficulty Levels

- **Easy Mode**: Allows more attempts (e.g., 7 rows).
- **Medium Mode**: Moderate difficulty, reducing the number of attempts (e.g., 5 rows).
- **Hard Mode**: Higher difficulty with fewer attempts (e.g., 3 rows).

> The number of rows represents the number of attempts available to guess the word. As the difficulty increases, the number of rows decreases, making it more challenging for the player.

### Gameplay Buttons

#### Erase Button

- This button erases one character from the current guess. Players can correct mistakes before submitting their word.

#### Enter Button

- This button submits the current guess and progresses the game to the next round. Players cannot advance to the next round without completing the current one successfully.

#### Exit Button

- This button opens the **Statistics Menu**, which provides a detailed summary of gameplay.

### Statistics Menu

Upon pressing the **Exit Button**, the **Statistics Menu** appears. It includes:

- **Total Games**: The total number of games played.
- **Total Wins**: The total number of wins achieved.
- **Win Rate**: The player's win rate percentage.

The menu provides two options:

1. **Continue**: Resumes the game from the current round.
2. **Exit**: Exits the game completely.

## Gameplay Instructions

1. **Start the Game**: The game starts with a splash screen, after which the player selects the desired difficulty level.
2. **Make Guesses**: Use the keyboard to guess the word. After each guess, press the **Enter** button to submit the word.
3. **Erase Mistakes**: If needed, use the **Erase** button to remove characters from your guess before submission.
4. **Progression**: Complete the current round before moving to the next one. Clearing a round is mandatory to proceed.
5. **Exiting and Viewing Statistics**: Press the **Exit** button at any time to view your statistics. From the Statistics Menu, either continue playing or exit the game.

## Conclusion

The **Wordle Game** offers a user-friendly interface with engaging difficulty levels and helpful gameplay features such as counters, statistics, and useful controls like **Erase**, **Enter**, and **Exit** buttons. The game's real-time feedback and progression system make it an enjoyable and challenging experience for players of all skill levels.

package com.wordguess.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {

    private String word;
    private char[] revealedWord;
    private boolean[] correctChar;
    private int attemptsLeft;

    public GameService() {
        initializeGame();
    }

    public void initializeGame() {
        word = words();
        revealedWord = new char[word.length()];
        correctChar = new boolean[word.length()];
        attemptsLeft = 6;

        Random random = new Random();

        for (int i = 0; i < word.length(); i++) {
            revealedWord[i] = '_';
        }

        for (int i = 0; i < 2; i++) {
            int k;
            do {
                k = random.nextInt(word.length());
            } while (correctChar[k]);
            correctChar[k] = true;
            revealedWord[k] = word.charAt(k);
        }
    }

    public String getRevealedWord() {
        return new String(revealedWord);
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }
    public String guessCharacter(char c) {
        boolean guess = false;
        for (int i = 0; i < word.length(); i++) {
            if (c == word.charAt(i) && !correctChar[i]) {
                revealedWord[i] = c;
                correctChar[i] = true;
                guess = true;
            }
        }
        if (guess) {
            if (isCompleted()) {
                return "Congratulations! You've completed the game!";
            }
        } else {
            attemptsLeft--;
            if (attemptsLeft == 0) {
                return "Game over! The word was: " + word;
            }
        }
        return getRevealedWord();
    }

    private String words() {
        String[] words={"apple", "beach", "chair", "dance", "earth",
                "faith", "grace", "heart", "image", "juice", "knife", "light",
                "music", "night", "ocean", "peace", "queen",
                "river", "smile", "train", "uncle",
                "value", "water", "xylophone", "yellow", "zebra", "abyss",
                "brace", "chime", "dream", "eagle", "flame", "grace", "hatch",
                "island", "jewel", "karma", "lemon", "magic", "noble", "ocean",
                "pearl", "quilt", "river", "sable", "tango", "unify", "valor",
                "waltz", "xenon", "yacht", "zesty"};
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }
    private boolean isCompleted() {
        for (boolean b : correctChar) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}



package com.wordguess.controller;

import com.wordguess.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/home-page")
    public String showGamingHomePage(Model model) {
        gameService.initializeGame();
        model.addAttribute("word", gameService.getRevealedWord());
        model.addAttribute("attemptsLeft", gameService.getAttemptsLeft());
        return "HomePage";
    }

    @PostMapping("/guess")
    public String guessCharacter(@RequestParam char character, Model model) {
        String result = gameService.guessCharacter(character);
        model.addAttribute("word", gameService.getRevealedWord());
        model.addAttribute("attemptsLeft", gameService.getAttemptsLeft());
        model.addAttribute("message", result);
        return "HomePage";
    }
}

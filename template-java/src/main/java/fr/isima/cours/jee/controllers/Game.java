package fr.isima.cours.jee.controllers;

import fr.isima.cours.jee.business.Grille;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/game")
public class Game {
    private Grille grille;

    public Game() {
        grille = new Grille();
    }

    @GetMapping
    public ModelAndView displayGrille(){
        grille = new Grille();

        return new ModelAndView("grille", "Grille", grille);
    }

}

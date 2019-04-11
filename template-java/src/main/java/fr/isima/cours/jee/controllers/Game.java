package fr.isima.cours.jee.controllers;


import fr.isima.cours.jee.business.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Controller
@RequestMapping("/game")
public class Game {

    static String DB_DRIVER = "org.h2.Driver";
    static String DB_CONNECTION = "jdbc:h2:mem:test";
    static String DB_USER = "sa";
    static String DB_PASSWORD = "";

    private GameEntity game;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView createGrille(int player1, int player2){
        //Création de la grille de jeu
        game = new GameEntity();

        try {
            //Enregistrement en base
            Class.forName(DB_DRIVER);
            Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

            String sql = "INSERT INTO game (IdPlayer1, IdPlayer2, IdPlaying) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setInt(1,player1);
            preparedStatement.setInt(2,player2);
            preparedStatement.setInt(3,player1);
            preparedStatement.execute();

            sql = "SELECT MAX(Id) AS LastID FROM games";

            preparedStatement = dbConnection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            int gameID = 0;
            while(rs.next())    {

                gameID = rs.getInt("LastID");
            }

            sql = "INSERT INTO grid (gameid, cell_x, cell_y, value) VALUES ";

            for (Line l: game.getGrille().getGrille()) {
                for (Cell c: l.ligne) {
                    sql += "("+ gameID +", " + Integer.toString(c.line) + ", " + Integer.toString(c.column) + ", 0),";
                }
            }

            sql = sql.substring(0, sql.length()-1);
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.execute();


            dbConnection.commit();
            dbConnection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("game", "Grille", game.getGrille().getGrille());
    }

    @RequestMapping(value = "/grid", method = RequestMethod.GET)
    public ModelAndView getGrid(int id){
        try {
            //Enregistrement en base
            Class.forName(DB_DRIVER);
            Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

            String sql = "SELECT cell_x, cell_y, value FROM grid WHERE gameid = ? ORDER BY cell_x, cell_y ASC";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            int gameID = 0;
            while(rs.next()) {
                int x = rs.getInt("cell_x");
                int y = rs.getInt("cell_y");
                int v = rs.getInt("value");

                game.getGrille().getCell(x, y).setValue(v);
            }

            dbConnection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("grille", "Grille", game.getGrille().getGrille());
    }

    @RequestMapping(value = "/play", method = RequestMethod.GET)
    public boolean play(int id, int cell_x, int cell_y, int color){
        if(game.isLegalPlay(cell_x, cell_y, color)) {
            game.applyPlay(cell_x, cell_y, color);

            try {
                Class.forName(DB_DRIVER);
                Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

                String sql = "";
                PreparedStatement preparedStatement;
                for (Line l: game.getGrille().getGrille()) {
                    for (Cell c: l.ligne) {
                        sql = "UPDATE grid SET value = ? WHERE gameid = ? AND cell_x = ? AND cell_y = ?";
                        preparedStatement = dbConnection.prepareStatement(sql);
                        preparedStatement.setInt(1, color);
                        preparedStatement.setInt(2, id);
                        preparedStatement.setInt(3, cell_x);
                        preparedStatement.setInt(4, cell_y);
                        preparedStatement.execute();
                    }
                }

                dbConnection.commit();
                dbConnection.close();

                return true;

            } catch (Exception e) {
                e.printStackTrace(); return false;
            }
        }
        return false;
    }
}

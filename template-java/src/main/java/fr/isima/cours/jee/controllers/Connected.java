package fr.isima.cours.jee.controllers;

import fr.isima.cours.jee.business.UserDisplay;
import fr.isima.cours.jee.server.controllers.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class Connected {

    @Autowired
    private Environment env;

    static String DB_DRIVER = "org.h2.Driver";
    static String DB_CONNECTION = "jdbc:h2:mem:test";
    static String DB_USER = "sa";
    static String DB_PASSWORD = "";

    public String users;

    @GetMapping
    public ModelAndView displayGrille(HttpServletRequest request){
        ArrayList<UserDisplay> users = new ArrayList<UserDisplay>();

        try {
            Class.forName(DB_DRIVER);
            Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

            String playerName= (String)request.getSession().getAttribute("username");
            UserDisplay player = new UserDisplay(playerName, 0);
            users.add(player);


            String selectQuery = "SELECT id, username FROM users WHERE attenteJeu > 0;";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(selectQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())    {
                String username = rs.getNString("username");
                int Id = rs.getInt("id");
                if(username.equals(playerName))
                    users.set(0, new UserDisplay(username, Id));
                else
                    users.add(new UserDisplay(username, Id));
                System.out.println("playerName : " + playerName + " / user : " + username);
            }

            dbConnection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return new ModelAndView("home", "users", users);
    }
}

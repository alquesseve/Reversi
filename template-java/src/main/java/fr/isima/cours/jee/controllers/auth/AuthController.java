package fr.isima.cours.jee.server.controllers.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Controller
@RequestMapping("/auth")
public class AuthController {

    static String DB_DRIVER = "org.h2.Driver";
    static String DB_CONNECTION = "jdbc:h2:mem:test";
    static String DB_USER = "sa";
    static String DB_PASSWORD = "";


    @GetMapping("{user}")
    @ResponseBody
    public String auth(@PathVariable String user, HttpSession httpSession) {
        //if ("Henri".equals(user)) {
            httpSession.setAttribute("user", new User(user));
            httpSession.setAttribute("username", user);

            //On met à jour le fait que l'on attend une partie
            try {
                Class.forName(DB_DRIVER);
                Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

                String selectQuery = "UPDATE users SET attenteJeu = 1 WHERE username = ?";
                PreparedStatement preparedStatement = dbConnection.prepareStatement(selectQuery);
                preparedStatement.setString(1, user);
                preparedStatement.executeQuery();
                System.out.println("session : " + user);
                dbConnection.close();

                return "ok";
            } catch (Exception e) {
                e.printStackTrace();
                return "ko";
            }
        //}
        //return "ko";
    }
}

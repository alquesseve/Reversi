package fr.isima.cours.jee.server.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Configuration
public class UserAuthConfiguration {


    @Bean
    @SessionScope
    @Autowired
    public User user(HttpSession session) {
        return Objects.requireNonNull((User) session.getAttribute("user"));
    }
}

package fr.isima.cours.jee.controllers.auth;

import fr.isima.cours.jee.controllers.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Provider;

@Controller
@RequestMapping("/withAuth")
public class AuthentifiedController {

    private final Provider<User> userProvider;

    @Autowired
    public AuthentifiedController(Provider<User> userProvider) {
        this.userProvider = userProvider;
    }

    @GetMapping
    @ResponseBody
    public String getUser() {
        return userProvider.get().getName();
    }
}

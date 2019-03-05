package fr.isima.cours.jee.controllers.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("{user}")
    @ResponseBody
    public String auth(@PathVariable String user, HttpSession httpSession) {
        if ("Henri".equals(user)) {
            httpSession.setAttribute("user", new User(user));
            return "ok";
        }
        return "ko";
    }
}

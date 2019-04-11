package fr.isima.cours.jee.server;

import fr.isima.cours.jee.business.UserDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    static String DB_DRIVER = "org.h2.Driver";
    static String DB_CONNECTION = "jdbc:h2:mem:test";
    static String DB_USER = "sa";
    static String DB_PASSWORD = "";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler((request, response, authentication) -> {
                    request.getSession().setAttribute("username", authentication.getName());
                    response.sendRedirect("/home");
                }).permitAll();
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        try {
            Class.forName(DB_DRIVER);
            Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

            String selectQuery = "SELECT id, username, password FROM users";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(selectQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())    {
                auth.inMemoryAuthentication().withUser(User.withDefaultPasswordEncoder().username(rs.getNString("username")).password(rs.getNString("password")).roles("USER"));
            }

            dbConnection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

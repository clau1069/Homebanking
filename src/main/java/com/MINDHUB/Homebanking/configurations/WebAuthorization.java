package com.MINDHUB.Homebanking.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization extends WebSecurityConfigurerAdapter { //para configurar la autorización WebAuthorization hereda del mismo para sobrescribir el método
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/loans/create").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/clients", "/api/login", "/api/logout").permitAll()
                .antMatchers(HttpMethod.POST, "/api/clients/current/account", "/api/transactions", "/api/loans").hasAnyAuthority("CLIENT", "ADMIN")
                .antMatchers(HttpMethod.PATCH, "/clients/current/cards", "/clients/current/accounts").hasAnyAuthority("CLIENT", "ADMIN")
                .antMatchers("/index.html", "/index.css", "/index.js").permitAll()
                .antMatchers("/api/clients/current/**").hasAuthority("CLIENT")
                .antMatchers(  "/web/**", "/api/loans", "/clients/current/cards", "/clients/current/accounts").hasAnyAuthority("CLIENT", "ADMIN")

                .antMatchers("/admin/**", "/rest/**", "/h2-console", "/api/**").hasAuthority("ADMIN")
                .antMatchers("/**").permitAll()
                ;




        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login");


        //http.rememberMe().key("uniqueAndSecret"); //para el remember me
        /*http.rememberMe().
                key("rem-me-key").
                rememberMeParameter("remember-me-param").
                rememberMeCookieName("my-remember-me").
                tokenValiditySeconds(86400);*/
        http.logout()
                .logoutUrl("/api/logout");
                //.deleteCookies("JSESSIONID");//para el remember me (ultima parte)
        http.csrf().disable();
        //disabling frameOptions so h2-console can be accessed
        http.headers().frameOptions().disable();
        // if user is not authenticated, just send an authentication failure response
        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
        // if login is successful, just clear the flags asking for authentication
        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));
        // if login fails, just send an authentication failure response
        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
        // if logout is successful, just send a success response
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}

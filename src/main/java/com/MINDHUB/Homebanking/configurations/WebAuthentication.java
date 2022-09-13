package com.MINDHUB.Homebanking.configurations;

import com.MINDHUB.Homebanking.models.Client;
import com.MINDHUB.Homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //le indica a spring que debe crear un objeto de este tipo cuando se está iniciando la aplicación para que cuando se configure el módulo de spring utilice ese objeto ya creado.
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    ClientRepository clientRepository;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputEmail-> {
            Client client = clientRepository.findByEmail(inputEmail);
            if (client != null) {
                if(client.getEmail().contains("admin")){
                    return new User(client.getEmail(), client.getPassword(),
                            AuthorityUtils.createAuthorityList("ADMIN"));
                }else{
                    return new User(client.getEmail(), client.getPassword(),
                            AuthorityUtils.createAuthorityList("CLIENT"));
                }
            } else {
                throw new UsernameNotFoundException("Unknown client: " + inputEmail);
            }

        });

    }
}

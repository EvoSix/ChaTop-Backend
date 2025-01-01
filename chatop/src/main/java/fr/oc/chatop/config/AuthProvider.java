package fr.oc.chatop.config;
import fr.oc.chatop.entity.User;
import fr.oc.chatop.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
@Component
public class AuthProvider  implements AuthenticationProvider {

    @Autowired
    private UserRepos userRepos;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();


        User user = userRepos.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));




        return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }


}

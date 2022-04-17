package ru.learnup.java2.antipn.spring.boot.operasales.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.learnup.java2.antipn.spring.boot.operasales.entity.User;
import ru.learnup.java2.antipn.spring.boot.operasales.services.UserService;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    @Autowired
    public MyAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        final User targetUser = userService.searchByLoginAndPass(name, password);
        if (targetUser != null)
            return new UsernamePasswordAuthenticationToken(targetUser, null, targetUser.getRoles());
        throw new BadCredentialsException("Incorrect credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}

package net.masich.logserver.server.ui.service.user.impl;

import net.masich.logserver.server.ui.domain.user.entity.UserEntity;
import net.masich.logserver.server.ui.service.user.UserService;
import net.masich.logserver.server.ui.service.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            UserEntity user = userService.findByEmail(email);

            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ADMIN"));

            return new User(user.getEmail(), user.getPassword(), authorities);
        } catch (NotFound e) {
            throw new BadCredentialsException("User was not found, please verify entered credentials.", e);
        }
    }

}

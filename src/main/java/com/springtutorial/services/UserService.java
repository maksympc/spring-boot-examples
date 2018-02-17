package com.springtutorial.services;

import com.google.common.collect.ImmutableList;
import com.springtutorial.model.Role;
import com.springtutorial.model.User;
import com.springtutorial.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void init() {
        if (!userDao.findByUserName("user").isPresent()) {
            userDao.save(User.builder()
                    .username("user")
                    .authorities(ImmutableList.of(Role.USER))
                    .password(new BCryptPasswordEncoder().encode("password"))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build()
            );
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("user " + username + " was not found!"));
    }
}

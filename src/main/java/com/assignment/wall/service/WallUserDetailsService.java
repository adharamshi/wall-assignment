package com.assignment.wall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.assignment.wall.model.User;
import com.assignment.wall.repository.UserRepository;

@Service
public class WallUserDetailsService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findOne(username);
        if(user==null){
            throw new UsernameNotFoundException(username+" is not found.");
        }
        return new WallUserDetails(user);
    }
}

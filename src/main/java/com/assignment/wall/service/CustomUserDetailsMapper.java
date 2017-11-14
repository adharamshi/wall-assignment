package com.assignment.wall.service;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

import java.util.Collection;

/**
 * Created by adharamshi on 11/11/17.
 */
public class CustomUserDetailsMapper extends LdapUserDetailsMapper {

    private UserDetailsService userDetailService;

    public CustomUserDetailsMapper(UserDetailsService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx,
                                          String username, Collection<? extends GrantedAuthority> authorities) {

        return (UserDetails) this.userDetailService.loadUserByUsername(username);
    }
}

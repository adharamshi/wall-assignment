package com.assignment.wall.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import com.assignment.wall.model.User;

import java.util.Collection;

public class WallUserDetails implements UserDetails {
    private final User user;

    public WallUserDetails(User user) {
        this.user = user;
    }
    public User getuser(){
        return user;
    }

    //Authorities
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return AuthorityUtils.createAuthorityList("ROLE_"+this.user.getRoleName().name());
    }

    //password
    @Override
    public String getPassword() {
        return "[PROTECTED]";
    }

    //userId
    @Override
    public String getUsername() {
        return this.user.getUserId();
    }

    //
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //
    @Override
    public boolean isEnabled() {
        return true;
    }

}

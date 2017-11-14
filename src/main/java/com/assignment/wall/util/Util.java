package com.assignment.wall.util;

import com.assignment.wall.model.User;
import com.assignment.wall.service.WallUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

public class Util {

    public static User getLoginuserFromPrincipal(Principal principal){
        Authentication authentication=(Authentication)principal;
        WallUserDetails userDetails=WallUserDetails.class.cast(authentication.getPrincipal());
        return userDetails.getuser();
        /*WallUserDetails userDetails=(WallUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return userDetails.getuser();*/
    }
    public static void updateAuthenticate(Principal principal, User newUser) {
        Authentication oldAuth= (Authentication) principal;
        Authentication newAuth=new UsernamePasswordAuthenticationToken(new WallUserDetails(newUser),oldAuth.getCredentials(),oldAuth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

}

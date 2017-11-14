package com.assignment.wall.util;

import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;

/**
 * Created by adharamshi on 11/11/17.
 */
public class PasswordEncoder {
    private static final int SHA_LENGTH = 20;
    public static void main(String[] args) {
        LdapShaPasswordEncoder encoder = new LdapShaPasswordEncoder();
        String encodedPassword = "{SHA}nFCebWjxfaLbHHG1Qk5UU4trbvQ=";
        String rawPassword = "password";
        String shaPassword = encoder.encodePassword(rawPassword, null);
        System.out.println("Encoded password: " + shaPassword);


    }
}

package com.pet.project.springsecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by БОСС ЗДЕСЬ on 13.05.2016.
 */
public class QuickPasswordEncodingGenerator {
    public static void main(String[] args) {
        String password = "kek";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(password));
    }
}

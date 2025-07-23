package com.example.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new PasswordEncoder() {
	        @Override
	        public String encode(CharSequence charSequence) {
	            return getCipherHash(charSequence.toString());
	        }

	        @Override
	        public boolean matches(CharSequence charSequence, String s) {
	            return getCipherHash(charSequence.toString()).equals(s);
	        }
	    };
	}

	public static String getCipherHash(String input) {
	    try {
	        // Static getInstance method is called with hashing SHA
	        MessageDigest md = MessageDigest.getInstance("SHA-1");

	        // digest() method called
	        // to calculate message digest of an input
	        // and return array of byte
	        byte[] messageDigest = md.digest(input.getBytes());

	        // Convert byte array into signum representation
	        BigInteger no = new BigInteger(1, messageDigest);

	        // Convert message digest into hex value
	        String hashtext = no.toString(16);

	        while (hashtext.length() < 32) {
	            hashtext = "0" + hashtext;
	        }

	        return hashtext;
	    }

	    // For specifying wrong message digest algorithms
	    catch (NoSuchAlgorithmException e) {
	        System.out.println("Exception thrown"
	                + " for incorrect algorithm: " + e);
	        return null;
	    }
	}
	
    @Id
    private String username;
    private String email;
    private String password;
    private String role;

    // Getter dan Setter

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = getCipherHash(password);
    }
}

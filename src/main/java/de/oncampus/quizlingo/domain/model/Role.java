package de.oncampus.quizlingo.domain.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
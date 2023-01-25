package de.oncampus.quizlingo.domain.dto;

import de.oncampus.quizlingo.validation.ValidPassword;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    @NotNull
    @Size(min = 1, message = "{Size.userDto.username}")
    private String username;

    @ValidPassword
    private String password;

//    @ValidEmail
//    @NotNull
//    @Size(min = 1, message = "{Size.userDto.email}")
//    private String email;

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(final String email) {
//        this.email = email;
//    }
//
//    private Integer role;
//
//    public Integer getRole() {
//        return role;
//    }
//
//    public void setRole(final Integer role) {
//        this.role = role;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

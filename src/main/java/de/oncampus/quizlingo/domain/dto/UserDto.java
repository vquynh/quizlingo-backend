package de.oncampus.quizlingo.domain.dto;

import de.oncampus.quizlingo.validation.ValidPassword;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class UserDto {

    @NotNull
    @Size(min = 1, message = "{Size.userDto.username}")
    private String username;

    @ValidPassword
    private String password;

    private String name;
    private String imageURL;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}

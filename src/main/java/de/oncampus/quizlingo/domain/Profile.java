package de.oncampus.quizlingo.domain;

import java.time.LocalDate;

public class Profile {

    private final String username;
    private String firstName;
    private String lastName;
    private String profilePicUrl;
    private String email;
    private String phoneNumber;
    private boolean emailVerified;
    private String country;
    private LocalDate birthday;

    public Profile(String username){
        this.username = username;
    }

    public Profile firstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public Profile lastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public Profile profilePic(String profilePicUrl){
        this.profilePicUrl = profilePicUrl;
        return this;
    }

    public Profile email(String email){
        this.email = email;
        return this;
    }

    public Profile phoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Profile emailVerified(boolean emailVerified){
        this.emailVerified = emailVerified;
        return this;
    }

    public Profile country(String country){
        this.country = country;
        return this;
    }

    public Profile birthday(LocalDate birthday){
        this.birthday = birthday;
        return this;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePicUrl='" + profilePicUrl + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailVerified=" + emailVerified +
                ", country='" + country + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

package de.oncampus.quizlingo.domain.dto;

import de.oncampus.quizlingo.domain.Profile;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ProfileTest {

    @Test
    public void testProfile(){
        Profile profile = new Profile("testProfile")
                .firstName("Test")
                .lastName("Profile")
                .profilePic("http://some.pic")
                .email("test@profile.mail")
                .phoneNumber("1234567")
                .emailVerified(true)
                .country("DE")
                .birthday(LocalDate.parse("2005-05-05"));

        System.out.println(profile);
    }
}
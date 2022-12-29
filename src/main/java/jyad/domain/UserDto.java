package jyad.domain;

import java.time.LocalDate;

public record UserDto(long version, String firstName, String lastName, Gender gender, String email,
                      LocalDate birthday, String phoneNumber) {
    public void update(UserApp user) {
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setGender(gender);
        user.setBirthday(birthday);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
    }
}

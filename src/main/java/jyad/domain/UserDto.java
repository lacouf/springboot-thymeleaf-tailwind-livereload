package jyad.domain;

import java.time.LocalDate;

public record UserDto(String firstName, String lastName, Gender gender, String email,
                      LocalDate birthday, String phoneNumber) {
}

package jyad.web;

import jyad.domain.Gender;
import jyad.domain.UserDto;
import jyad.web.validation.ValidationGroupOne;
import jyad.web.validation.ValidationGroupTwo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@NotExistingUser(groups = ValidationGroupTwo.class)
@Data
public class CreateUserFormData {

    @NotBlank
    @Size(min = 1, max = 200, groups = ValidationGroupOne.class)
    protected String firstName;
    @NotBlank
    @Size(min = 1, max = 200, groups = ValidationGroupOne.class)
    protected String lastName;
    @NotNull
    protected Gender gender;
    @NotBlank
    @Email(groups = ValidationGroupOne.class)
    protected String email;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected LocalDate birthday;
    @NotBlank
    @Pattern(regexp = "[0-9.\\-() x/+]+", groups = ValidationGroupOne.class)
    protected String phoneNumber;

    public UserDto toDto() {
        return new UserDto(0, firstName, lastName, gender,
                email,birthday, phoneNumber);
    }
}

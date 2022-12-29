package jyad.util;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import jyad.domain.UserDto;
import jyad.domain.Gender;
import jyad.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

@Component
@Profile("init-db") //<.>
public class DatabaseInitializer implements CommandLineRunner { //<.>
    static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);
    private final Faker faker = new Faker(); //<.>
    private final UserService userService;

    public DatabaseInitializer(UserService userService) { //<.>
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        logger.info("inserting users...");
        for (int i = 0; i < 50; i++) { //<.>
            UserDto userDto = newRandomUserParameters();
            userService.createUser(userDto);
        }
    }

    private UserDto newRandomUserParameters() {
        Name name = faker.name();
        String firstName = name.firstName();
        String lastName = name.lastName();
        Gender gender = faker.bool().bool() ? Gender.M : Gender.F;
        LocalDate birthday = LocalDate.ofInstant(faker.date().birthday(18, 65).toInstant(), ZoneId.systemDefault());
        String email = (faker.internet().emailAddress(generateEmailLocalPart(firstName, lastName)));
        String phoneNumber = faker.phoneNumber().phoneNumber();
        return new UserDto(0, firstName, lastName, gender, email, birthday, phoneNumber);
    }

    private String generateEmailLocalPart(String firstName, String lastName) {
        return String.format("%s.%s",
                             StringUtils.remove(firstName.toLowerCase(), "'"),
                             StringUtils.remove(lastName.toLowerCase(), "'"));
    }
}

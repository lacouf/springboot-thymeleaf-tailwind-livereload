package jyad.web;

import jyad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotExistingUserValidator implements ConstraintValidator<NotExistingUser, CreateUserFormData> {

    private final UserService userService;

    @Autowired
    public NotExistingUserValidator(UserService userService) { //<.>
        this.userService = userService;
    }

    public void initialize(NotExistingUser constraint) {
        // intentionally empty
    }

    // tag::isValid[]
    public boolean isValid(CreateUserFormData formData, ConstraintValidatorContext context) {
        if (userService.userWithEmailExists(formData.getEmail())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{UserAlreadyExisting}")
                   .addPropertyNode("email")
                   .addConstraintViolation();

            return false;
        }

        return true;
    }
    // end::isValid[]
}

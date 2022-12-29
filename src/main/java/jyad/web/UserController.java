package jyad.web;


import jyad.domain.Gender;
import jyad.domain.UserApp;
import jyad.domain.UserNotFoundException;
import jyad.service.UserService;
import jyad.web.validation.EditUserValidationGroupSequence;
import jyad.web.validation.ValidationGroupSequence;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model,
                        @SortDefault.SortDefaults({
                                @SortDefault("lastName"),
                                @SortDefault("firstName")}) Pageable pageable) {
        model.addAttribute("users", service.getUsers(pageable));
        return "users/list";
    }

    // tag::create-get[]
    @GetMapping("/create") //<.>
    public String createUserForm(Model model) { //<.>
        model.addAttribute("user", new CreateUserFormData()); //<.>
        model.addAttribute("genders", List.of(Gender.M, Gender.F, Gender.O)); //<.>
        return "users/edit"; //<.>
    }
    // end::create-get[]

    // tag::create-post[]
    @PostMapping("/create")
    public String doCreateUser(@Validated(ValidationGroupSequence.class) @ModelAttribute("user") CreateUserFormData formData,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", List.of(Gender.M, Gender.F, Gender.O));
            return "users/edit";
        }

        service.createUser(formData.toDto());

        return "redirect:/users";
    }
    // end::create-post[]
    @GetMapping("/{id}")
    public String editUserForm(@PathVariable("id") Long userId,
                               Model model) {
        UserApp user = service.getUser(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        model.addAttribute("user", EditUserFormData.fromUser(user));
        model.addAttribute("genders", List.of(Gender.M, Gender.F, Gender.O));
        model.addAttribute("editMode", EditMode.UPDATE);
        return "users/edit";
    }

    @PostMapping("/{id}")
    public String doEditUser(@PathVariable("id") Long userId,
                             @Validated(EditUserValidationGroupSequence.class)
                             @ModelAttribute("user") EditUserFormData formData,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", List.of(Gender.M, Gender.F, Gender.O));
            model.addAttribute("editMode", EditMode.UPDATE);
            return "users/edit";
        }

        service.editUser(userId, formData.toDto());

        return "redirect:/users";
    }
}

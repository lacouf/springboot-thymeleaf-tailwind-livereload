package jyad.service;

import jyad.domain.UserApp;
import jyad.domain.UserDto;
import jyad.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserApp createUser(UserDto userDto) {
        UserApp user = UserApp.builder()
                .firstName(userDto.firstName())
                .lastName(userDto.lastName())
                .email(userDto.email())
                .gender(userDto.gender())
                .birthday(userDto.birthday())
                .phoneNumber(userDto.phoneNumber())
                .build();

        return repository.save(user);
    }

    // tag::getUsers[]
    public Page<UserApp> getUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public boolean userWithEmailExists(String email) {
        return repository.existsByEmail(email);
    }
}

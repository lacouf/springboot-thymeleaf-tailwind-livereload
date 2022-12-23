package jyad.service;

import jyad.domain.User;
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

    public User createUser(UserDto userDto) {
        User user = User.builder()
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
    public Page<User> getUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }
}

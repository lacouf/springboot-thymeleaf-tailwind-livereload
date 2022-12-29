package jyad.service;

import jyad.domain.UserApp;
import jyad.domain.UserDto;
import jyad.domain.UserNotFoundException;
import jyad.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public UserApp editUser(long id, UserDto userDto) {
        UserApp  userApp = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        if (userDto.version() != userApp.getVersion()) {
            throw new ObjectOptimisticLockingFailureException(UserApp.class, userApp.getId());
        }
        userDto.update(userApp);
        return repository.save(userApp);
    }

    // tag::getUsers[]
    public Page<UserApp> getUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public boolean userWithEmailExists(String email) {
        return repository.existsByEmail(email);
    }

    public Optional<UserApp> getUser(long userId) {
        return repository.findById(userId);
    }
}

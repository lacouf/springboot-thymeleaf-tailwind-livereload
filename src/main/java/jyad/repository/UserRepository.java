package jyad.repository;

import jyad.domain.UserApp;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserRepository extends PagingAndSortingRepository<UserApp, Long> {
    boolean existsByEmail(String email);
}

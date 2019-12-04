package com.github.owl4soul.shopping_service.repository;

import com.github.owl4soul.shopping_service.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    boolean existsByUsername(String username);

}

package com.gft.manager.repository;

import com.gft.manager.model.Role;
import com.gft.manager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String emailOrUseName,String emailOrUserName);

    Boolean existsByUsername(String username);
    Page<User> findByRoles(Role role, Pageable pageable);
    Page<User>findAllByUsernameStartingWith(String username, Pageable pageable);
}

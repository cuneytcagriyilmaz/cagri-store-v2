package com.cagri.ecommerce.cagristore.repository;

import com.cagri.ecommerce.cagristore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);

//    boolean authenticate(String email, String password);
//
//    User findByEmailAndPassword(String email, String password);


    @Query("SELECT r.authority FROM User u JOIN u.roles r WHERE u.email = :email")
    Optional<String> findAuthorityByEmail(String email);


}

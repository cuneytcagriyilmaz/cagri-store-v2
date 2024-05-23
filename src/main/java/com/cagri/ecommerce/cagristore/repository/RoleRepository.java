package com.cagri.ecommerce.cagristore.repository;

import com.cagri.ecommerce.cagristore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.authority = :authority")
    Optional<Role> findByAuthority(String authority);
    // authority ==> role tablosundaki name kolonu kısaca.


}


package com.cagri.ecommerce.cagristore.service;

import com.cagri.ecommerce.cagristore.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();

    Optional<Role> findById(Long id);

    Optional<Role> findByAuthority(String authority);

    Role save(Role role);

    void deleteById(Long id);

    void initDefaultRoles();
}

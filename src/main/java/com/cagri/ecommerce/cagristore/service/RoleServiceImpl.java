package com.cagri.ecommerce.cagristore.service;

import com.cagri.ecommerce.cagristore.entity.Role;
import com.cagri.ecommerce.cagristore.exception.StoreException;
import com.cagri.ecommerce.cagristore.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

//    @Override
//    public Optional<Role> findById(Long id) {
//        return roleRepository.findById(id);
//    }

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.ofNullable(roleRepository.findById(id).orElseThrow(() -> new StoreException("Role is not found. ID: " + id, HttpStatus.NOT_FOUND)));
    }

//    @Override
//    public Optional<Role> findByAuthority(String authority) {
//        return roleRepository.findByAuthority(authority);
//    }

    @Override
    public Optional<Role> findByAuthority(String authority) {
        return Optional.ofNullable(roleRepository.findByAuthority(authority).orElseThrow(() -> new StoreException("authority is not found: " + authority, HttpStatus.NOT_FOUND)));
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

//    @Override
//    public void deleteById(Long id) {
//        roleRepository.deleteById(id);
//    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    @PostConstruct
    public void initDefaultRoles() {

        if (roleRepository.findByAuthority("ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setAuthority("ADMIN");
            roleRepository.save(adminRole);
        }


        if (roleRepository.findByAuthority("USER").isEmpty()) {
            Role userRole = new Role();
            userRole.setAuthority("USER");
            roleRepository.save(userRole);
        }
    }


}

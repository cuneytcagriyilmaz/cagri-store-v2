package com.cagri.ecommerce.cagristore.config;

import com.cagri.ecommerce.cagristore.entity.Role;
import com.cagri.ecommerce.cagristore.entity.User;
import com.cagri.ecommerce.cagristore.repository.RoleRepository;
import com.cagri.ecommerce.cagristore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        Role adminRole = roleRepository.findByAuthority("ADMIN")
                .orElseThrow(() -> new RuntimeException("Role not found: ADMIN"));

        Role userRole = roleRepository.findByAuthority("USER")
                .orElseThrow(() -> new RuntimeException("Role not found: USER"));

        User adminUser = userRepository.findUserByEmail("admin@example.com").orElse(null);
        if (adminUser == null) {
            adminUser = new User();
            adminUser.setEmail("admin@example.com");
            adminUser.setPassword(passwordEncoder.encode("123456"));
            adminUser.setName("Admin User");
            adminUser.getRoles().add(adminRole);
            userRepository.save(adminUser);
        }

        User normalUser = userRepository.findUserByEmail("user@example.com").orElse(null);
        if (normalUser == null) {
            normalUser = new User();
            normalUser.setEmail("user@example.com");
            normalUser.setPassword(passwordEncoder.encode("123456"));
            normalUser.setName("Normal User");
            normalUser.getRoles().add(userRole);
            userRepository.save(normalUser);
        }
    }

}

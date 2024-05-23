//package com.cagri.ecommerce.cagristore.service;
//
//import com.cagri.ecommerce.cagristore.entity.Role;
//import com.cagri.ecommerce.cagristore.entity.User;
//import com.cagri.ecommerce.cagristore.repository.RoleRepository;
//import com.cagri.ecommerce.cagristore.repository.UserRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//@Service
//public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {
//
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public AuthenticationServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @PostConstruct
//    public void initDefaultUsersAndRoles() {
//        // Admin kullanıcı var mı kontrol et, yoksa ekle
//        if (userRepository.findUserByEmail("admin@admin.com").isEmpty()) {
//            User adminUser = new User();
//            adminUser.setName("Admin");
//            adminUser.setEmail("admin@admin.com");
//            adminUser.setPassword(passwordEncoder.encode("123456"));
//
//            Set<Role> adminRoles = new HashSet<>();
//            adminRoles.add(roleRepository.findByAuthority("ADMIN").get());
//            adminUser.setRoles(adminRoles);
//
//            userRepository.save(adminUser);
//        }
//
//        // Normal kullanıcı var mı kontrol et, yoksa ekle
//        if (userRepository.findUserByEmail("user@user.com").isEmpty()) {
//            User normalUser = new User();
//            normalUser.setName("User");
//            normalUser.setEmail("user@user.com");
//            normalUser.setPassword(passwordEncoder.encode("123456"));
//
//            Set<Role> userRoles = new HashSet<>();
//            userRoles.add(roleRepository.findByAuthority("USER").get());
//            normalUser.setRoles(userRoles);
//
//            userRepository.save(normalUser);
//        }
//    }
//
//
//
//
//    @Override
//    public User register(String name, String email, String password) {
//        String encodedPassword = passwordEncoder.encode(password);
//
//        Optional<Role> userRoleOptional = roleRepository.findByAuthority("USER");
//        if (userRoleOptional.isEmpty()) {
//            throw new RuntimeException("Role 'USER' not found");
//        }
//        Role userRole = userRoleOptional.get();
//
//        Set<Role> roles = new HashSet<>();
//        roles.add(userRole);
//
//        User user = new User();
//        user.setName(name);
//        user.setEmail(email);
//        user.setRoles(roles);
//        user.setPassword(encodedPassword);
//
//        return userRepository.save(user);
//    }
//
//    public boolean authenticate(String email, String password) {
//        Optional<User> optionalUser = userRepository.findUserByEmail(email);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            return passwordEncoder.matches(password, user.getPassword());
//        }
//        return false;
//    }
//
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findUserByEmail(username)
//                .orElseThrow(() -> {
//                    System.out.println("User credentials are not valid");
//                    return new UsernameNotFoundException("User credentials are not valid");
//                });
//    }
//
//
//
//}
//
//
//package com.cagri.ecommerce.cagristore.service;
//
//import com.cagri.ecommerce.cagristore.entity.Role;
//import com.cagri.ecommerce.cagristore.entity.User;
//import com.cagri.ecommerce.cagristore.repository.RoleRepository;
//import com.cagri.ecommerce.cagristore.repository.UserRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//@Service
//public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {
//
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public AuthenticationServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @PostConstruct
//    public void initDefaultUsersAndRoles() {
//        // Admin kullanıcı var mı kontrol et, yoksa ekle
//        if (userRepository.findUserByEmail("admin@admin.com").isEmpty()) {
//            User adminUser = new User();
//            adminUser.setName("Admin");
//            adminUser.setEmail("admin@admin.com");
//            adminUser.setPassword(passwordEncoder.encode("123456"));
//
//            Set<Role> adminRoles = new HashSet<>();
//            adminRoles.add(roleRepository.findByAuthority("ADMIN").get());
//            adminUser.setRoles(adminRoles);
//
//            userRepository.save(adminUser);
//        }
//
//        // Normal kullanıcı var mı kontrol et, yoksa ekle
//        if (userRepository.findUserByEmail("user@user.com").isEmpty()) {
//            User normalUser = new User();
//            normalUser.setName("User");
//            normalUser.setEmail("user@user.com");
//            normalUser.setPassword(passwordEncoder.encode("123456"));
//
//            Set<Role> userRoles = new HashSet<>();
//            userRoles.add(roleRepository.findByAuthority("USER").get());
//            normalUser.setRoles(userRoles);
//
//            userRepository.save(normalUser);
//        }
//    }
//
//    @Override
//    public User register(String name, String email, String password) {
//        String encodedPassword = passwordEncoder.encode(password);
//
//        Optional<Role> userRoleOptional = roleRepository.findByAuthority("USER");
//        if (userRoleOptional.isEmpty()) {
//            throw new RuntimeException("Role 'USER' not found");
//        }
//        Role userRole = userRoleOptional.get();
//
//        Set<Role> roles = new HashSet<>();
//        roles.add(userRole);
//
//        User user = new User();
//        user.setName(name);
//        user.setEmail(email);
//        user.setRoles(roles);
//        user.setPassword(encodedPassword);
//
//        return userRepository.save(user);
//    }
//
//    public boolean authenticate(String email, String password) {
//        Optional<User> optionalUser = userRepository.findUserByEmail(email);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            return passwordEncoder.matches(password, user.getPassword());
//        }
//        return false;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findUserByEmail(username)
//                .orElseThrow(() -> {
//                    System.out.println("User credentials are not valid");
//                    return new UsernameNotFoundException("User credentials are not valid");
//                });
//    }
//}


package com.cagri.ecommerce.cagristore.service;

import com.cagri.ecommerce.cagristore.entity.Order;
import com.cagri.ecommerce.cagristore.entity.Role;
import com.cagri.ecommerce.cagristore.entity.User;
import com.cagri.ecommerce.cagristore.exception.StoreException;
import com.cagri.ecommerce.cagristore.repository.OrderRepository;
import com.cagri.ecommerce.cagristore.repository.RoleRepository;
import com.cagri.ecommerce.cagristore.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;


    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.orderRepository = orderRepository;
    }


    @Override
    public User register(String name, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);

        Role userRole = roleRepository.findByAuthority("USER")
                .orElseThrow(() -> new StoreException("Role 'USER' not found", HttpStatus.NOT_FOUND));

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);


        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRoles(roles);
        user.setPassword(encodedPassword);

        Order order = new Order();
        order.setOrderAmount(0.0);
        order.setTotalAmount(0.0);
        order.setUser(user);

        return userRepository.save(user);
    }

//    @Override
//    public User registerDefaultAdmin() {
//        String name = "TestAdmin";
//        String email = "admintest@testadmin.com";
//        String password = "123456";
//        Optional<User> existingUser = userRepository.findUserByEmail(email);
//        if (existingUser.isPresent()) {
//            return existingUser.get();
//        } else {
//
//            String encodedPassword = passwordEncoder.encode(password);
//
//            Role userRole = roleRepository.findByAuthority("ADMIN")
//                    .orElseThrow(() -> new RuntimeException("Role 'ADMIN' not found"));
//
//            Set<Role> roles = new HashSet<>();
//            roles.add(userRole);
//
//            User user = new User();
//            user.setName(name);
//            user.setEmail(email);
//            user.setRoles(roles);
//            user.setPassword(encodedPassword);
//            return userRepository.save(user);
//        }
//
//    }
//
//    @Override
//    public User registerDefaultUser() {
//        String name = "TestUser";
//        String email = "usertest@testuser.com";
//        String password = "123456";
//        Optional<User> existingUser = userRepository.findUserByEmail(email);
//        if (existingUser.isPresent()) {
//            return existingUser.get();
//        } else {
//
//            String encodedPassword = passwordEncoder.encode(password);
//
//            Role userRole = roleRepository.findByAuthority("USER")
//                    .orElseThrow(() -> new RuntimeException("Role 'USER' not found"));
//
//            Set<Role> roles = new HashSet<>();
//            roles.add(userRole);
//
//            User user = new User();
//            user.setName(name);
//            user.setEmail(email);
//            user.setRoles(roles);
//            user.setPassword(encodedPassword);
//            return userRepository.save(user);
//        }
//
//    }


    @Override
    public Long findUserIdByEmail(String email) {
        Optional<User> existingUser = userRepository.findUserByEmail(email);
        if (existingUser.isPresent()) {
            return existingUser.get().getId();
        } else {
            throw new StoreException("not found mail: " + email, HttpStatus.NOT_FOUND);
        }


    }


    public boolean authenticate(String email, String password) {
        return userRepository.findUserByEmail(email)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new StoreException("Wrong email or Password", HttpStatus.NOT_FOUND));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }


    @Override
    public boolean isAdmin(String email) {
        Optional<String> authorityOptional = userRepository.findAuthorityByEmail(email);
        return authorityOptional.map(authority -> authority.equals("ADMIN")).orElse(false);
    }


}

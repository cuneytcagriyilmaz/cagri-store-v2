package com.cagri.ecommerce.cagristore.service;

import com.cagri.ecommerce.cagristore.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    User register(String name, String email, String password);

//    User registerDefaultAdmin();
//
//    User registerDefaultUser();


    boolean authenticate(String email, String password);

    UserDetails loadUserByUsername(String username);


    boolean isAdmin(String email);

    Long findUserIdByEmail(String email);


}

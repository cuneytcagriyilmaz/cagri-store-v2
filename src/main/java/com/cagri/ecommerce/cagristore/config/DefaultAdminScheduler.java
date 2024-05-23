package com.cagri.ecommerce.cagristore.config;

import com.cagri.ecommerce.cagristore.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class DefaultAdminScheduler {

    private final AuthenticationService authenticationService;

    @Autowired
    public DefaultAdminScheduler(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

//    @Scheduled(initialDelay = 1000, fixedDelay = Long.MAX_VALUE)
//    public void sendRequestsAfterDelay() {
//        authenticationService.registerDefaultAdmin();
//        authenticationService.registerDefaultUser();
//    }
}

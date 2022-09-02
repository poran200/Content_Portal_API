package com.gft.manager.util;



import com.gft.manager.model.Role;
import com.gft.manager.model.RoleName;
import com.gft.manager.model.User;
import com.gft.manager.repository.RoleRepository;
import com.gft.manager.repository.UserRepository;
import com.gft.manager.service.impl.RoleService;
import com.gft.manager.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
class DbInit {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    @PostConstruct
    private void postConstruct() {


//        userRepository.deleteAll();
//        roleRepository.deleteAll();
        if (roleRepository.findByRole(RoleName.ROLE_USER).isEmpty()){
            roleService.create(new Role(RoleName.ROLE_USER));
        }
        if(roleRepository.findByRole(RoleName.ROLE_ADMIN).isEmpty()) {
            roleService.create(new Role(RoleName.ROLE_ADMIN));
        }
        Optional<User> byEmail = userRepository.findByEmail("admin");
        if (byEmail.isEmpty()){
            User user= new User();
            user.setEmail("admin");
            user.setUsername("admin");
            user.setFullName("admin user");
            user.setPassword(passwordEncoder.encode("root"));
            user.setActive(true);
            user.setEmailVerified(true);
            userService.saveWithRole(user,RoleName.ROLE_ADMIN);
        }
//        questionsRepository.deleteAll();
        String qs1 = "Which destination do you have content for?";
        String qs2 = "Which destination do you have radar?";
        String qs3 = "Describe yur style";


    }
}

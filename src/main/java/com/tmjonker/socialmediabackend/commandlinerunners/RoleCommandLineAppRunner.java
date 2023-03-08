package com.tmjonker.socialmediabackend.commandlinerunners;

import com.tmjonker.socialmediabackend.constants.Constants;
import com.tmjonker.socialmediabackend.entities.role.Role;
import com.tmjonker.socialmediabackend.services.CustomUserDetailsService;
import com.tmjonker.socialmediabackend.services.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class RoleCommandLineAppRunner implements CommandLineRunner {

    private CustomUserDetailsService customUserDetailsService;
    private RoleService roleService;

    @Lazy
    public RoleCommandLineAppRunner(CustomUserDetailsService customUserDetailsService, RoleService roleService) {

        this.customUserDetailsService = customUserDetailsService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {

        Role admin = new Role(Constants.ADMIN);
        Role user = new Role(Constants.USER);

        if (!roleService.roleExists(admin)) {
            roleService.saveRole(admin);
            roleService.saveRole(user);
        }
    }
}

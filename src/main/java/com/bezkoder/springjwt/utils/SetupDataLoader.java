package com.bezkoder.springjwt.utils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
 
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
 
    private boolean alreadySetup = false;
 
    @Autowired
    private UserRepository userRepository;
 
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder encoder;
    
    @Value("${bezkoder.app.default.password}")
    private String password;
    
    @Value("${bezkoder.app.default.username}")
    private String username;
    
    @Value("${bezkoder.app.default.displayname}")
    private String displayname;
    
    @Value("${bezkoder.app.default.email}")
    private String email;
 
    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
 
        // Create user roles
//        var userRole = createRoleIfNotFound(ERole.ROLE_USER);
//        var modRole = createRoleIfNotFound(ERole.ROLE_MODERATOR);
//        var adminRole = createRoleIfNotFound(ERole.ROLE_ADMIN);
 
        // create roles set
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(ERole.ROLE_ADMIN));
        roles.add(new Role(ERole.ROLE_MODERATOR));
        roles.add(new Role(ERole.ROLE_USER));
        
        
        // Create users
        createUserIfNotFound(username, email, encoder.encode(password), displayname, roles);
 
        alreadySetup = true;
    }
 
    @Transactional
    private final Role createRoleIfNotFound(ERole roleUser) {
        Optional<Role> role = roleRepository.findByName(roleUser);
        Role roleSaved = null;
        if (!role.isPresent()) {
            Role roleSaver = new Role(roleUser);
            roleSaved = roleRepository.save(roleSaver);
        }
        return roleSaved;
    }
 
    @Transactional
    private final User createUserIfNotFound(String username, String email, String password, String displayname, Set<Role> roles) {
    	Optional<User> user = userRepository.findByUsername(username);
    	User userSaved = null;
    	User userCreated = null;
        if (!user.isPresent()) {
            userCreated = new User(username,email,password,displayname);
            userCreated.setRoles(roles);
            userSaved = userRepository.save(userCreated);
        }
        return userSaved;
    }
}
package se.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import se.auth.model.Role;
import se.auth.model.User;
import se.auth.repository.RoleRepository;
import se.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SignUpController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @RequestMapping("/")
    public String index(Model model) {

        return "/index";
    }

    @RequestMapping("/auth/signup/")
    public String toAddUser() {
        return "/signup";
    }

    @RequestMapping("/signup")
    public String addUser(String name,String password,String role) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(password);
        User user = new User(name,encodePassword);
        List<Role> roles = new ArrayList<>();
        Role role1 = roleRepository.findByRolename(role);
        roles.add(role1);
        user.setRoles(roles);
        userRepository.save(user);
        return "/index";
    }

}
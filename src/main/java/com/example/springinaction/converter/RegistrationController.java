package com.example.springinaction.converter;

import com.example.springinaction.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserRepository useRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository useRepo, PasswordEncoder passwordEncoder) {
        this.useRepo = useRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

//    @PostMapping
//    public String processRegistration(RegistrationForm form) {
//        useRepo.save(form.toUser(passwordEncoder));
//        return  "redirect:/login";
//    }
}
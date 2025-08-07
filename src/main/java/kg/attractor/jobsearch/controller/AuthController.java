package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;


    @GetMapping("/register")
    public String showRegister(Model model ){
        model.addAttribute("userDto", new UserDto());
        return "/auth/register";
    }

    @GetMapping("/login")
    public String login(){
        return "/auth/login";
    }

}

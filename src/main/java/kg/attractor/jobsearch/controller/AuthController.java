package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.exceptions.EmailAlreadyExistsException;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/register")
    public String register( @Valid UserDto userDto,  BindingResult bindingResult, Model model) throws EmailAlreadyExistsException {
        if(!bindingResult.hasErrors()){
            userService.saveUser(userDto);
            return "redirect:/auth/login";
        }

        model.addAttribute("userDto", userDto);
        return "/auth/register";
    }

    @GetMapping("/login")
    public String login(){
        return "/auth/login";
    }

}

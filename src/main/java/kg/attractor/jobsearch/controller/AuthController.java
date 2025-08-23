package kg.attractor.jobsearch.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.ResetPasswordFormDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.exceptions.EmailAlreadyExistsException;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

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

    @GetMapping("forgot_password")
    public String showForgotPasswordForm() {
        return "auth/forgot_password_form";
    }

    @PostMapping("forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model){
        try{
            userService.makeResetPasswdLink(request);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
        } catch (UsernameNotFoundException | UnsupportedEncodingException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (MessagingException ex) {
            model.addAttribute("error", "Error while sending email");
        }
        return "auth/forgot_password_form";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(
            @RequestParam String token,
            Model model

    ) {
        model.addAttribute("form", new ResetPasswordFormDto());
        try {
            userService.getUserByResetPasswordToken(token);
            model.addAttribute("token", token);
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("error", "Invalid token");
        }
        return "auth/reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(@Valid @ModelAttribute("form") ResetPasswordFormDto form, BindingResult bindingResult, Model model) {
        if(!bindingResult.hasErrors()){
            String token = form.getToken();
            String password = form.getPassword();
            try {
                User user = userService.getUserByResetPasswordToken(token);
                userService.updatePassword(user, password);
                model.addAttribute("message", "You have successfully changed your password.");
            } catch (UsernameNotFoundException ex) {
                model.addAttribute("message", "Invalid Token");
            }
            return "auth/message";
        }
        model.addAttribute("token", form.getToken());
        model.addAttribute("form", form);
        return "auth/reset_password_form";

    }

}

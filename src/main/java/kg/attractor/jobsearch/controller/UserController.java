package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exceptions.EmailAlreadyExistsException;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @GetMapping("/profile")
    public String profile(@PageableDefault(size = 6) Pageable pageable,
                          Model model,
                          Authentication auth) {
        String username = auth.getName();
        UserDto userDto = userService.findByEmail(username);
        if(userDto.getAccountType().equalsIgnoreCase("employer")){
            Page<VacancyDto> vacancies = vacancyService.findByAuthor(username, pageable);
            model.addAttribute("items", vacancies);
        }else if(userDto.getAccountType().equalsIgnoreCase("applicant")){
            Page<ResumeDto> resumes = resumeService.findByAuthor(username, pageable);
            model.addAttribute("items", resumes);
        }
        model.addAttribute("user",userDto);
        return "user/profile";
    }

    @GetMapping("edit")
    public String showEditUser(Model model, Authentication auth) {
        String username = auth.getName();
        UserEditDto userEditDto = userService.findUserEditTypeByEmail(username);
        model.addAttribute("userEditDto", userEditDto);
        return "user/edit";
    }

    @PostMapping("edit")
    public String editUser(@Valid UserEditDto user, BindingResult bindingResult, Model model, Authentication auth) throws EmailAlreadyExistsException {
        if(!bindingResult.hasErrors()){
            userService.editUserByEmail(user, auth.getName());
            return "redirect:/users/profile";
        }
        model.addAttribute("userEditDto", user);
        return "user/edit";
    }
}

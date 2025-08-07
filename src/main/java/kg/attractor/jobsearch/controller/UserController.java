package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String profile(Model model, Authentication auth) {
        String username = auth.getName();
        UserDto userDto = userService.findByEmail(username);
        if(userDto.getAccountType().equalsIgnoreCase("employer")){
            List<VacancyDto> vacancies = vacancyService.findByAuthor(username);
            model.addAttribute("items", vacancies);
        }else if(userDto.getAccountType().equalsIgnoreCase("applicant")){
            List<ResumeDto> resumes = resumeService.findByAuthor(username);
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
        return "auth/edit";
    }
}

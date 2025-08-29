package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.config.SecurityConfig;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.model.Role;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.service.RoleService;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final VacancyService vacancyService;
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public String vacancies(@PageableDefault(size = 5) Pageable pageable,
                            Authentication auth,
                            Model model) {
        model.addAttribute("vacancies", vacancyService.findAllActive(pageable));

        if (auth != null) {
            UserDto user = userService.findByEmail(auth.getName());
            Role role = roleService.findRoleById(user.getRoleId());

            if (role.getRole().equals("employer")) {
                return "redirect:/resumes";
            }
        }

        return "employer/vacancies";
    }
}

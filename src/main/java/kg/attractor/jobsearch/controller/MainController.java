package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final VacancyService vacancyService;

    @GetMapping
    public String vacancies(@PageableDefault(size = 5) Pageable pageable,
                            Model model) {
        model.addAttribute("vacancies", vacancyService.findAllActive(pageable));
        return "employer/vacancies";
    }
}

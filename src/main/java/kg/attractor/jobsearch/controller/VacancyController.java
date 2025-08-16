package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.CategoryDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.CategoryService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final CategoryService categoryService;

    @GetMapping
    public String vacancies(@PageableDefault(size = 5) Pageable pageable,
                            Model model) {
        model.addAttribute("vacancies", vacancyService.findAllActive(pageable));
        return "employer/vacancies";
    }

    @GetMapping("create")
    public String createVacancy(Model model) {
        VacancyDto vacancyDto = new VacancyDto();
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories );
        model.addAttribute("vacancyDto", vacancyDto);
        return "employer/createVacancy";
    }

    @PostMapping("create")
    public String createVacancy(@Valid VacancyDto vacancyDto, BindingResult bindingResult, Model model, Authentication auth) {
        if(!bindingResult.hasErrors()){
            vacancyService.create(vacancyDto, auth.getName());
            return "redirect:/vacancies";
        }
        model.addAttribute("vacancyDto", vacancyDto);
        model.addAttribute("categories", categoryService.getCategories());
        return "employer/createVacancy";
    }

    @GetMapping("edit/{vacancyId}")
    public String editVacancy(@PathVariable Long vacancyId, Model model) {
        model.addAttribute("vacancyDto", vacancyService.findVacancyById(vacancyId));
        model.addAttribute("categories", categoryService.getCategories());
        return "employer/editVacancy";
    }

    @PostMapping("edit")
    public String editVacancy(@Valid VacancyDto vacancyDto, BindingResult bindingResult, Model model, Authentication auth) {
        if (!bindingResult.hasErrors()) {
            vacancyService.editById( vacancyDto, vacancyDto.getId(), auth.getName());
            return "redirect:/users/profile";
        }
        model.addAttribute("vacancyDto", vacancyDto);
        model.addAttribute("categories", categoryService.getCategories());
        return "employer/editVacancy";
    }
}

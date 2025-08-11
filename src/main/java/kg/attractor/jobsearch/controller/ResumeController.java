package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.CategoryDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.service.CategoryService;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final CategoryService categoryService;

    @GetMapping
    public String resumes(Model model) {
        model.addAttribute("resumes", resumeService.findAllActive());
        return "applicant/resumes";
    }

    @GetMapping("create")
    public String createResume(Model model) {
        List<CategoryDto> categories = categoryService.getCategories();

        model.addAttribute("resumeDto", new ResumeDto());
        model.addAttribute("categories", categories);
        return "applicant/createResume";
    }
    @PostMapping("create")
    public String createResume(@Valid ResumeDto resumeDto, BindingResult bindingResult, Model model, Authentication auth) {
        if (!bindingResult.hasErrors()) {
            resumeService.addResume(resumeDto, auth);
            return "redirect:/users/profile";
        }
        model.addAttribute("resumeDto", resumeDto);
        model.addAttribute("categories", categoryService.getCategories());
        return "applicant/createResume";
    }

    @GetMapping("edit/{resumeId}")
    public String editResume(@PathVariable Long resumeId, Model model) {
        model.addAttribute("resumeDto", resumeService.findResumeById(resumeId));
        model.addAttribute("categories", categoryService.getCategories());
        return "applicant/editResume";
    }

    @PostMapping("edit")
    public String editResume(@Valid ResumeDto resumeDto, BindingResult bindingResult, Model model, Authentication auth) {
        if (!bindingResult.hasErrors()) {
            String email = auth.getName();
            resumeService.editById(resumeDto, resumeDto.getId(), email);
            return "redirect:/users/profile";
        }
        model.addAttribute("resumeDto", resumeDto);
        model.addAttribute("categories", categoryService.getCategories());
        return "applicant/editResume";
    }
}

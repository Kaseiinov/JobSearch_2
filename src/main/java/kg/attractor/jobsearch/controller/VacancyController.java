package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping
    public ResponseEntity<List<VacancyDto>> findByUserResponse(@RequestParam(required = false) String email) {
        List<VacancyDto> vacancyDto = vacancyService.findVacanciesByUserResponse(email);
        return ResponseEntity.ok(vacancyDto);
    }

    @PostMapping("create")
    public ResponseEntity<Void> create(@RequestBody VacancyDto vacancyDto) {
        vacancyService.create(vacancyDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Void> editById(@RequestBody VacancyDto vacancyDto, @PathVariable Long id) {
        vacancyService.editById(vacancyDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        vacancyService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<VacancyDto>> findAllActive(@RequestParam(required = false) boolean active) {
        List<VacancyDto> vacancyDto = vacancyService.findAllActive(active);
        return ResponseEntity.ok(vacancyDto);
    }

    @GetMapping
    public ResponseEntity<List<VacancyDto>> findAll() {
        List<VacancyDto> vacancyDto = vacancyService.findAll();
        return ResponseEntity.ok(vacancyDto);
    }

    @GetMapping
    public ResponseEntity<List<VacancyDto>> findByCategoryId(@RequestParam(required = false) String category) {
        List<VacancyDto> vacancyDto = vacancyService.findByCategory(category);
        return ResponseEntity.ok(vacancyDto);
    }

    @GetMapping("responders")
    public ResponseEntity<List<UserDto>> findRespondersToVacancy(@RequestParam Long vacancyId) {
        List<UserDto> userDtos = vacancyService.findRespondersToVacancyById(vacancyId);
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<VacancyDto> findVacancyById(@PathVariable Long id) {
        VacancyDto vacancyDto = vacancyService.findVacancyById(id);
        return ResponseEntity.ok(vacancyDto);
    }

}

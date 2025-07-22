package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping
    public ResponseEntity<List<VacancyDto>> getAllVacancies(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String userEmail) {

        if (userEmail != null) {
            return ResponseEntity.ok(vacancyService.findVacanciesByUserResponse(userEmail));
        }
        if (category != null) {
            return ResponseEntity.ok(vacancyService.findByCategory(category));
        }
        if (active != null) {
            return ResponseEntity.ok(vacancyService.findAllActive(active));
        }
        return ResponseEntity.ok(vacancyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacancyDto> getVacancyById(@PathVariable Long id) {
        return ResponseEntity.ok(vacancyService.findVacancyById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createVacancy(@RequestBody @Valid VacancyDto vacancyDto) {
        vacancyService.create(vacancyDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVacancy(
            @PathVariable Long id,
            @RequestBody @Valid VacancyDto vacancyDto) {
        vacancyService.editById(vacancyDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable Long id) {
        vacancyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/responders")
    public ResponseEntity<List<UserDto>> getVacancyResponders(@PathVariable Long id) {
        return ResponseEntity.ok(vacancyService.findRespondersToVacancyById(id));
    }
}
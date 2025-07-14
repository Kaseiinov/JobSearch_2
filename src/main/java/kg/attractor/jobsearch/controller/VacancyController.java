package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacancy")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @PostMapping("create")
    public ResponseEntity<Void> create(@RequestBody VacancyDto vacancyDto) {
        vacancyService.create(vacancyDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("editById/{id}")
    public ResponseEntity<Void> editById(@RequestBody VacancyDto vacancyDto, @PathVariable Long id) {
        vacancyService.editById(vacancyDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        vacancyService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("findAllActive")
    public ResponseEntity<List<VacancyDto>> findAllActive() {
        List<VacancyDto> vacancyDto = vacancyService.findAllActive();
        return ResponseEntity.ok(vacancyDto);
    }

    @GetMapping("findByCategory/{id}")
    public ResponseEntity<List<VacancyDto>> findByCategoryId(@PathVariable Long id) {
        List<VacancyDto> vacancyDto = vacancyService.findByCategoryId(id);
        return ResponseEntity.ok(vacancyDto);
    }

    @GetMapping("findRespondersToVacancyById/{id}")
    public ResponseEntity<List<VacancyDto>> findRespondersToVacancy(@PathVariable Long id) {
        List<VacancyDto> responders = vacancyService.findRespondersToVacancyById(id);
        return ResponseEntity.ok(responders);
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<VacancyDto> findVacancyById(@PathVariable Long id) {
        VacancyDto vacancyDto = vacancyService.findVacancyById(id);
        return ResponseEntity.ok(vacancyDto);
    }

}

package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping("create")
    public ResponseEntity<Void> create(@RequestBody ResumeDto resumeDto) {
        resumeService.create(resumeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("editById/{id}")
    public ResponseEntity<Void> editById(@RequestBody ResumeDto resumeDto, @PathVariable Long id) {
        resumeService.editById(resumeDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        resumeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("findAll")
    public ResponseEntity<List<ResumeDto>> findAll() {
        List<ResumeDto> resumeDto = resumeService.findAll();
        return ResponseEntity.ok(resumeDto);
    }

    @GetMapping("findByCategory/{category}")
    public ResponseEntity<List<ResumeDto>> findByCategoryId(@PathVariable String category) {
        List<ResumeDto> resumeDto = resumeService.findByCategory(category);
        return ResponseEntity.ok(resumeDto);
    }

    @GetMapping("findByAuthor/{email}")
    public ResponseEntity<List<ResumeDto>> findByAuthor(@PathVariable String email) {
        List<ResumeDto> resumeDto = resumeService.findByAuthor(email);
        return ResponseEntity.ok(resumeDto);
    }

    @GetMapping("findRespondersToVacancyById/{id}")
    public ResponseEntity<List<ResumeDto>> findRespondersToVacancy(@PathVariable Long id) {
        List<ResumeDto> responders = resumeService.findRespondersToVacancyById(id);
        return ResponseEntity.ok(responders);
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<ResumeDto> findResumeById(@PathVariable Long id) {
        ResumeDto resumeDto = resumeService.findResumeById(id);
        return ResponseEntity.ok(resumeDto);
    }
}

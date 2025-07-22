package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<Void> createResume(@RequestBody @Valid ResumeDto resumeDto) {
        resumeService.create(resumeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateResume(
            @PathVariable Long id,
            @RequestBody @Valid ResumeDto resumeDto) {
        resumeService.editById(resumeDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id) {
        resumeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ResumeDto>> getAllResumes(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String authorEmail) {

        if (category != null) {
            return ResponseEntity.ok(resumeService.findByCategory(category));
        }
        if (authorEmail != null) {
            return ResponseEntity.ok(resumeService.findByAuthor(authorEmail));
        }
        return ResponseEntity.ok(resumeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResumeDto> getResumeById(@PathVariable Long id) {
        return ResponseEntity.ok(resumeService.findResumeById(id));
    }

    @GetMapping("/{id}/responders")
    public ResponseEntity<List<ResumeDto>> getVacancyResponders(@PathVariable Long id) {
        return ResponseEntity.ok(resumeService.findRespondersToVacancyById(id));
    }
}
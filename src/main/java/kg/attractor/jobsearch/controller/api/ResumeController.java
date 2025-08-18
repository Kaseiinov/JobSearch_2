package kg.attractor.jobsearch.controller.api;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.ContactsInfoDto;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restResume")
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<Void> createResume(@RequestBody @Valid ResumeDto resumeDto) {
        resumeService.create(resumeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/education")
    public ResponseEntity<Void> createEducation(@RequestBody @Valid EducationInfoDto educationDto) {
        resumeService.createEducation(educationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/experience")
    public ResponseEntity<Void> createExperience(@RequestBody @Valid WorkExperienceInfoDto expDto) {
        resumeService.createExperience(expDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/contact")
    public ResponseEntity<Void> createContact(@RequestBody @Valid ContactsInfoDto contactDto) {
        resumeService.createContact(contactDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/education/{id}")
    public ResponseEntity<Void> updateEducationById(@PathVariable Long id , @RequestBody @Valid EducationInfoDto educationDto) {
        resumeService.updateEducationById(educationDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/experience/{id}")
    public ResponseEntity<Void> updateExperienceById(@PathVariable Long id , @RequestBody @Valid WorkExperienceInfoDto expDto) {
        resumeService.updateExperienceById(expDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/contact/{id}")
    public ResponseEntity<Void> updateContactBy(@PathVariable Long id , @RequestBody @Valid ContactsInfoDto contactDto) {
        resumeService.updateContactById(contactDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateResume(
//            @PathVariable Long id,
//            @RequestBody @Valid ResumeDto resumeDto) {
//        resumeService.editById(resumeDto, id);
//        return ResponseEntity.ok().build();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id) {
        resumeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping
//    public ResponseEntity<List<ResumeDto>> getAllResumes(
//            @RequestParam(required = false) String category,
//            @RequestParam(required = false) String authorEmail) {
//
//        if (category != null) {
//            return ResponseEntity.ok(resumeService.findByCategory(category));
//        }
//        if (authorEmail != null) {
//            return ResponseEntity.ok(resumeService.findByAuthor(authorEmail));
//        }
//        return ResponseEntity.ok(resumeService.findAll());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ResumeDto> getResumeById(@PathVariable Long id) {
        return ResponseEntity.ok(resumeService.findResumeById(id));
    }

    @GetMapping("/{id}/responders")
    public ResponseEntity<List<ResumeDto>> getVacancyResponders(@PathVariable Long id) {
        return ResponseEntity.ok(resumeService.findRespondersToVacancyById(id));
    }
}
package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeDto;

import java.util.List;

public interface ResumeService {
    void create(ResumeDto resumeDto);

    void editById(ResumeDto resumeDto, Long id);

    void deleteById(Long id);

    List<ResumeDto> findAll();

    List<ResumeDto> findByCategory(String category);

    List<ResumeDto> findByAuthor(String email);

    List<ResumeDto> findRespondersToVacancyById(Long id);

    ResumeDto findResumeById(Long id);
}

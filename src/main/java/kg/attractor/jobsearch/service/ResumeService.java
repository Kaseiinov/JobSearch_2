package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ContactsInfoDto;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.model.Resume;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ResumeService {
    void addResume(ResumeDto resumeDto, Authentication auth);

    void updateExperienceById(WorkExperienceInfoDto expDto, Long id);

    void updateEducationById(EducationInfoDto educationDto, Long id);

    void updateContactById(ContactsInfoDto contactDto, Long id);

    void createExperience(WorkExperienceInfoDto expDto);

    void createEducation(EducationInfoDto educationDto);

    void createContact(ContactsInfoDto contactDto);

    void create(ResumeDto resumeDto);

    void editById(ResumeDto resumeDto, Long id, String email);

    void deleteById(Long id);

    List<ResumeDto> findAllActive();

    List<ResumeDto> findAll();

    List<ResumeDto> findByCategory(String category);

    List<ResumeDto> findByAuthor(String email);

    List<ResumeDto> findRespondersToVacancyById(Long id);

    ResumeDto findResumeById(Long id);

    Resume findModelResumeById(Long id);
}

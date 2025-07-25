package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dto.ContactsInfoDto;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.exceptions.ResumeNotFoundException;
import kg.attractor.jobsearch.model.ContactInfo;
import kg.attractor.jobsearch.model.EducationInfo;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.WorkExperienceInfo;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;

    @Override
    public void create(ResumeDto resumeDto){
        Resume resume = resumeDtoBuilderToModel(resumeDto);
        resumeDao.create(resume);
    }

    @Override
    public void editById(ResumeDto resumeDto, Long id){
        Resume resume = resumeDtoBuilderToModel(resumeDto);

        resumeDao.updateResumeById(id, resume);

    }

    @Override
    public void deleteById(Long id){
        resumeDao.deleteById(id);
    }

    @Override
    public List<ResumeDto> findAll(){
        return null;
    }

    @Override
    public List<ResumeDto> findByCategory(String category){
        List<Resume> resumes = resumeDao.findByCategory(category);
        return resumeBuilder(resumes);
    }

    @Override
    public List<ResumeDto> findByAuthor(String email){
        List<Resume> resumes = resumeDao.findByAuthor(email);
        return resumeBuilder(resumes);
    }

    @Override
    public List<ResumeDto> findRespondersToVacancyById(Long id){
        return null;
    }

    @Override
    public ResumeDto findResumeById(Long id){
        Resume resume = resumeDao.findById(id).orElseThrow(ResumeNotFoundException::new);
        return resumeBuilder(resume);
    }

    public Resume resumeDtoBuilderToModel(ResumeDto resumeDto){
        return Resume.builder()
                .applicantId(resumeDto.getApplicantId())
                .name(resumeDto.getName())
                .categoryId(resumeDto.getCategoryId())
                .salary(resumeDto.getSalary())
                .isActive(resumeDto.getIsActive())
                .createdDate(LocalDateTime.now())
                .educations(Optional.ofNullable(resumeDto.getEducations())
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .filter(Objects::nonNull)
                        .map(edu -> EducationInfo.builder()
                                .resumeId(edu.getResumeId())
                                .institution(edu.getInstitution())
                                .program(edu.getProgram())
                                .startDate(edu.getStartDate())
                                .endDate(edu.getEndDate())
                                .degree(edu.getDegree())
                                .build())
                        .toList())
                .experience(Optional.ofNullable(resumeDto.getWorkExperience())
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .map(exp -> WorkExperienceInfo
                                .builder()
                                .resumeId(exp.getResumeId())
                                .years(exp.getYears())
                                .companyName(exp.getCompanyName())
                                .position(exp.getPosition())
                                .responsibilities(exp.getResponsibilities())
                                .build()).toList())
                .contacts(Optional.ofNullable(resumeDto.getContacts())
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .map(con -> ContactInfo
                                .builder()
                                .typeId(con.getTypeId())
                                .resumeId(con.getResumeId())
                                .value(con.getValue())
                                .build()).toList())
                .build();
    }

    public List<ResumeDto> resumeBuilder(List<Resume> resumes){
        return resumes
                .stream()
                .map(r -> ResumeDto.builder()
                        .id(r.getId())
                        .applicantId(r.getApplicantId())
                        .name(r.getName())
                        .categoryId(r.getCategoryId())
                        .salary(r.getSalary())
                        .isActive(r.getIsActive())
                        .createdDate(r.getCreatedDate())
                        .updateTime(r.getUpdateTime())
                        .educations(r.getEducations()
                                .stream()
                                .map(edu -> EducationInfoDto
                                        .builder()
                                        .resumeId(edu.getResumeId())
                                        .institution(edu.getInstitution())
                                        .program(edu.getProgram())
                                        .startDate(edu.getStartDate())
                                        .endDate(edu.getEndDate())
                                        .degree(edu.getDegree())
                                        .build()).toList())
                        .workExperience(r.getExperience()
                                .stream()
                                .map(exp -> WorkExperienceInfoDto
                                        .builder()
                                        .resumeId(exp.getResumeId())
                                        .years(exp.getYears())
                                        .companyName(exp.getCompanyName())
                                        .position(exp.getPosition())
                                        .responsibilities(exp.getResponsibilities())
                                        .build()).toList())
                        .contacts(r.getContacts()
                                .stream()
                                .map(con -> ContactsInfoDto
                                        .builder()
                                        .typeId(con.getTypeId())
                                        .resumeId(con.getResumeId())
                                        .value(con.getValue())
                                        .build()).toList())
                        .build())
                .toList();

    }

    public ResumeDto resumeBuilder(Resume resume){
        return ResumeDto.builder()
                .id(resume.getId())
                .applicantId(resume.getApplicantId())
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .salary(resume.getSalary())
                .isActive(resume.getIsActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .educations(resume.getEducations()
                        .stream()
                        .map(edu -> EducationInfoDto
                                .builder()
                                .resumeId(edu.getResumeId())
                                .institution(edu.getInstitution())
                                .program(edu.getProgram())
                                .startDate(edu.getStartDate())
                                .endDate(edu.getEndDate())
                                .degree(edu.getDegree())
                                .build()).toList())
                .workExperience(resume.getExperience()
                        .stream()
                        .map(exp -> WorkExperienceInfoDto
                                .builder()
                                .resumeId(exp.getResumeId())
                                .years(exp.getYears())
                                .companyName(exp.getCompanyName())
                                .position(exp.getPosition())
                                .responsibilities(exp.getResponsibilities())
                                .build()).toList())
                .contacts(resume.getContacts()
                        .stream()
                        .map(con -> ContactsInfoDto
                                .builder()
                                .typeId(con.getTypeId())
                                .resumeId(con.getResumeId())
                                .value(con.getValue())
                                .build()).toList())
                .build();

    }
}

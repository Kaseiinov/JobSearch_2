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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;

    @Override
    public void updateExperienceById(WorkExperienceInfoDto expDto, Long id) {
        WorkExperienceInfo exp = expBuilderToModel(expDto);
        resumeDao.updateExperience(exp, id);
    }

    @Override
    public void updateEducationById(EducationInfoDto educationDto, Long id){
        if(educationDto.getStartDate().isAfter(educationDto.getEndDate()) ){
            throw new DateTimeException("End date can't be before start date");
        }
        EducationInfo education = educationBuilderToModel(educationDto);
        resumeDao.updateEducations(education, id);
    }

    @Override
    public void updateContactById(ContactsInfoDto contactDto, Long id){
        ContactInfo contact = contactDtoBuilderToModel(contactDto);
        resumeDao.updateContactInfo(contact, id);
    }

    @Override
    public void createExperience(WorkExperienceInfoDto expDto) {
        WorkExperienceInfo exp = expBuilderToModel(expDto);
        resumeDao.createExperience(exp);
        log.info("Experience created");
    }

    @Override
    public void createEducation(EducationInfoDto educationDto){
        if(educationDto.getStartDate().isAfter(educationDto.getEndDate()) ){
            throw new DateTimeException("End date can't be before start date");
        }
        EducationInfo education = educationBuilderToModel(educationDto);
        resumeDao.createEducation(education);
        log.info("Education created");
    }

    @Override
    public void createContact(ContactsInfoDto contactDto){
        ContactInfo contact = contactDtoBuilderToModel(contactDto);
        resumeDao.createContact(contact);
        log.info("Contact created");
    }

    @Override
    public void create(ResumeDto resumeDto){
        Resume resume = resumeDtoBuilderToModel(resumeDto);
        resumeDao.create(resume);
        log.info("Resume created");
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

    public ContactInfo contactDtoBuilderToModel(ContactsInfoDto contactDto){
        return ContactInfo
                .builder()
                .typeId(contactDto.getTypeId())
                .resumeId(contactDto.getResumeId())
                .value(contactDto.getValue())
                .build();
    }

    public WorkExperienceInfo expBuilderToModel(WorkExperienceInfoDto expDto) {
        return WorkExperienceInfo
                .builder()
                .resumeId(expDto.getResumeId())
                .years(expDto.getYears())
                .companyName(expDto.getCompanyName())
                .position(expDto.getPosition())
                .responsibilities(expDto.getResponsibilities())
                .build();
    }

    public EducationInfo educationBuilderToModel(EducationInfoDto educationDto){
        return EducationInfo
                .builder()
                .resumeId(educationDto.getResumeId())
                .institution(educationDto.getInstitution())
                .program(educationDto.getProgram())
                .startDate(educationDto.getStartDate())
                .endDate(educationDto.getEndDate())
                .degree(educationDto.getDegree())
                .build();
    }

    public Resume resumeDtoBuilderToModel(ResumeDto resumeDto){
        return Resume.builder()
                .applicantId(resumeDto.getApplicantId())
                .name(resumeDto.getName())
                .categoryId(resumeDto.getCategoryId())
                .salary(resumeDto.getSalary())
                .isActive(resumeDto.getIsActive())
                .createdDate(LocalDateTime.now())
//                .educations(Optional.ofNullable(resumeDto.getEducations())
//                        .orElseGet(Collections::emptyList)
//                        .stream()
//                        .filter(Objects::nonNull)
//                        .map(edu -> EducationInfo.builder()
//                                .resumeId(edu.getResumeId())
//                                .institution(edu.getInstitution())
//                                .program(edu.getProgram())
//                                .startDate(edu.getStartDate())
//                                .endDate(edu.getEndDate())
//                                .degree(edu.getDegree())
//                                .build())
//                        .toList())
//                .experience(Optional.ofNullable(resumeDto.getWorkExperience())
//                        .orElseGet(Collections::emptyList)
//                        .stream()
//                        .map(exp -> WorkExperienceInfo
//                                .builder()
//                                .resumeId(exp.getResumeId())
//                                .years(exp.getYears())
//                                .companyName(exp.getCompanyName())
//                                .position(exp.getPosition())
//                                .responsibilities(exp.getResponsibilities())
//                                .build()).toList())
//                .contacts(Optional.ofNullable(resumeDto.getContacts())
//                        .orElseGet(Collections::emptyList)
//                        .stream()
//                        .map(con -> ContactInfo
//                                .builder()
//                                .typeId(con.getTypeId())
//                                .resumeId(con.getResumeId())
//                                .value(con.getValue())
//                                .build()).toList())
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
//                        .educations(r.getEducations()
//                                .stream()
//                                .map(edu -> EducationInfoDto
//                                        .builder()
//                                        .resumeId(edu.getResumeId())
//                                        .institution(edu.getInstitution())
//                                        .program(edu.getProgram())
//                                        .startDate(edu.getStartDate())
//                                        .endDate(edu.getEndDate())
//                                        .degree(edu.getDegree())
//                                        .build()).toList())
//                        .workExperience(r.getExperience()
//                                .stream()
//                                .map(exp -> WorkExperienceInfoDto
//                                        .builder()
//                                        .resumeId(exp.getResumeId())
//                                        .years(exp.getYears())
//                                        .companyName(exp.getCompanyName())
//                                        .position(exp.getPosition())
//                                        .responsibilities(exp.getResponsibilities())
//                                        .build()).toList())
//                        .contacts(r.getContacts()
//                                .stream()
//                                .map(con -> ContactsInfoDto
//                                        .builder()
//                                        .typeId(con.getTypeId())
//                                        .resumeId(con.getResumeId())
//                                        .value(con.getValue())
//                                        .build()).toList())
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
//                .educations(resume.getEducations()
//                        .stream()
//                        .map(edu -> EducationInfoDto
//                                .builder()
//                                .resumeId(edu.getResumeId())
//                                .institution(edu.getInstitution())
//                                .program(edu.getProgram())
//                                .startDate(edu.getStartDate())
//                                .endDate(edu.getEndDate())
//                                .degree(edu.getDegree())
//                                .build()).toList())
//                .workExperience(resume.getExperience()
//                        .stream()
//                        .map(exp -> WorkExperienceInfoDto
//                                .builder()
//                                .resumeId(exp.getResumeId())
//                                .years(exp.getYears())
//                                .companyName(exp.getCompanyName())
//                                .position(exp.getPosition())
//                                .responsibilities(exp.getResponsibilities())
//                                .build()).toList())
//                .contacts(resume.getContacts()
//                        .stream()
//                        .map(con -> ContactsInfoDto
//                                .builder()
//                                .typeId(con.getTypeId())
//                                .resumeId(con.getResumeId())
//                                .value(con.getValue())
//                                .build()).toList())
                .build();

    }
}

package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.*;
import kg.attractor.jobsearch.exceptions.ResumeNotFoundException;
import kg.attractor.jobsearch.model.*;
import kg.attractor.jobsearch.repository.ResumeRepository;
import kg.attractor.jobsearch.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final WorkExperienceService workExperienceService;
    private final EducationInfoService educationInfoService;
    private final ContactInfoService contactInfoService;
    private final ContactTypeService contactTypeService;

    @Override
    public void addResume(ResumeDto resumeDto, Authentication auth){
        User user = userService.findModelUserByEmail(auth.getName());

        Resume resume = new Resume();
        resume.setApplicant(user);
        resume.setName(resumeDto.getName());
        resume.setCategory(categoryService.findModelCategoryById(resumeDto.getCategoryId()));
        resume.setSalary(resumeDto.getSalary());
        resume.setIsActive(resumeDto.getIsActive());
        resume.setCreatedDate(LocalDateTime.now());
        resume.setUpdateTime(null);

        resumeRepository.save(resume);


//        if (resumeDto.getWorkExperience() != null) {
//            WorkExperienceInfoDto experience = resumeDto.getWorkExperience();
//            experience.setResumeId(resume.getId());
//            addWorkExperienceInfo(experience);
//        }
//
//        if (resumeDto.getEducation() != null) {
//            EducationInfoDto education = resumeDto.getEducation();
//            education.setResumeId(resume.getId());
//            addEducationInfo(education);
//        }
//
//        if (resumeDto.getContacts() != null) {
//            List<ContactsInfoDto> contacts = resumeDto.getContacts().stream()
//                    .filter(contact -> contact.getValue() != null && !contact.getValue().isBlank())
//                    .toList();
//
//            contacts.forEach(contact -> {
//                contact.setResumeId(resume.getId());
//                addContactInfo(contact);
//            });
    }

    @Override
    public void updateExperienceById(WorkExperienceInfoDto expDto, Long id) {
        WorkExperienceInfo exp = expBuilderToModel(expDto);
        workExperienceService.save(exp);
    }

    @Override
    public void updateEducationById(EducationInfoDto educationDto, Long id){
        if(educationDto.getStartDate().isAfter(educationDto.getEndDate()) ){
            throw new DateTimeException("End date can't be before start date");
        }
        EducationInfo education = educationBuilderToModel(educationDto);
        educationInfoService.save(education);
    }

    @Override
    public void updateContactById(ContactsInfoDto contactDto, Long id){
        ContactInfo contact = contactDtoBuilderToModel(contactDto);
        contactInfoService.save(contact);
    }

    @Override
    public void createExperience(WorkExperienceInfoDto expDto) {
        WorkExperienceInfo exp = expBuilderToModel(expDto);
        workExperienceService.save(exp);
        log.info("Experience created");
    }

    @Override
    public void createEducation(EducationInfoDto educationDto){
        if(educationDto.getStartDate().isAfter(educationDto.getEndDate()) ){
            throw new DateTimeException("End date can't be before start date");
        }
        EducationInfo education = educationBuilderToModel(educationDto);
        educationInfoService.save(education);
        log.info("Education created");
    }

    @Override
    public void createContact(ContactsInfoDto contactDto){
        ContactInfo contact = contactDtoBuilderToModel(contactDto);
        contactInfoService.save(contact);
        log.info("Contact created");
    }

    @Override
    public void create(ResumeDto resumeDto){
        Resume resume = resumeDtoBuilderToModel(resumeDto);
        resumeRepository.save(resume);
        log.info("Resume created");
    }

    @Override
    public void editById(ResumeDto resumeDto, Long id, String email){
        Resume resume = resumeRepository.findById(id).orElseThrow(ResumeNotFoundException::new);
        resume.setName(resumeDto.getName());
        resume.setCategory(categoryService.findModelCategoryById(resumeDto.getCategoryId()));
        resume.setSalary(resumeDto.getSalary());
        resume.setIsActive(resumeDto.getIsActive());
        resume.setUpdateTime(LocalDateTime.now());

        resumeRepository.save(resume);

    }

    @Override
    public void deleteById(Long id){
        resumeRepository.deleteById(id);
    }

    @Override
    public Page<ResumeDto> findAllActive(Pageable pageable){
        Page<Resume> resumes = resumeRepository.findAllByIsActive(true, pageable);
        return resumePageBuilder(resumes);
    }

    @Override
    public List<ResumeDto> findAll(){
        return null;
    }

    @Override
    public List<ResumeDto> findByCategory(String category){
        List<Resume> resumes = resumeRepository.findByCategory_Name(category);
        return resumeBuilder(resumes);
    }

    @Override
    public Page<ResumeDto> findByAuthor(String email, Pageable pageable){
        Page<Resume> resumes = resumeRepository.findResumeByApplicant_Email(email, pageable);
        return resumePageBuilder(resumes);
    }

    @Override
    public List<ResumeDto> findRespondersToVacancyById(Long id){
        return null;
    }

    @Override
    public ResumeDto findResumeById(Long id){
        Resume resume = resumeRepository.findById(id).orElseThrow(ResumeNotFoundException::new);
        return resumeBuilder(resume);
    }

    @Override
    public Resume findModelResumeById(Long id){
        return resumeRepository.findById(id).orElseThrow(ResumeNotFoundException::new);

    }

    public ContactInfo contactDtoBuilderToModel(ContactsInfoDto contactDto){
        return ContactInfo
                .builder()
                .type(contactTypeService.findById(contactDto.getTypeId()))
                .resume(findModelResumeById(contactDto.getResumeId()))
                .value(contactDto.getValue())
                .build();
    }

    public WorkExperienceInfo expBuilderToModel(WorkExperienceInfoDto expDto) {
        return WorkExperienceInfo
                .builder()
                .resume(findModelResumeById(expDto.getResumeId()))
                .years(expDto.getYears())
                .companyName(expDto.getCompanyName())
                .position(expDto.getPosition())
                .responsibilities(expDto.getResponsibilities())
                .build();
    }

    public EducationInfo educationBuilderToModel(EducationInfoDto educationDto){
        return EducationInfo
                .builder()
                .resume(findModelResumeById(educationDto.getResumeId()))
                .institution(educationDto.getInstitution())
                .program(educationDto.getProgram())
                .startDate(educationDto.getStartDate())
                .endDate(educationDto.getEndDate())
                .degree(educationDto.getDegree())
                .build();
    }

    public Resume resumeDtoBuilderToModel(ResumeDto resumeDto){
        return Resume.builder()
                .applicant(userService.findModelUserById(resumeDto.getApplicantId()))
                .name(resumeDto.getName())
                .category(categoryService.findModelCategoryById(resumeDto.getCategoryId()))
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
                        .applicantId(r.getApplicant().getId())
                        .name(r.getName())
                        .categoryId(r.getCategory().getId())
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

    public Page<ResumeDto> resumePageBuilder(Page<Resume> resumes){
        List<ResumeDto> resumesDto =  resumes.getContent()
                .stream()
                .map(r -> ResumeDto.builder()
                        .id(r.getId())
                        .applicantId(r.getApplicant().getId())
                        .name(r.getName())
                        .categoryId(r.getCategory().getId())
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

        return new PageImpl<>(resumesDto, resumes.getPageable(), resumes.getTotalElements());

    }

    public ResumeDto resumeBuilder(Resume resume){
        return ResumeDto.builder()
                .id(resume.getId())
                .applicantId(resume.getApplicant().getId())
                .name(resume.getName())
                .categoryId(resume.getCategory().getId())
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

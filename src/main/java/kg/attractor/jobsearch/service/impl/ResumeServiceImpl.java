package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;

    @Override
    public void create(ResumeDto resumeDto){

    }

    @Override
    public void editById(ResumeDto resumeDto, Long id){

    }

    @Override
    public void deleteById(Long id){

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
        return null;
    }

    public List<ResumeDto> resumeBuilder(List<Resume> resumes){
        List<ResumeDto> resumeDto = resumes
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
                        .build()).toList();
        return resumeDto;

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
                .build();

    }
}

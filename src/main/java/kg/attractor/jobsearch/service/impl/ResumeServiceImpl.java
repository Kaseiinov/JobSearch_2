package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.service.ResumeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

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
    public List<ResumeDto> findAllActive(){
        return null;
    }

    @Override
    public List<ResumeDto> findByCategoryId(Long id){
        return null;
    }

    @Override
    public List<ResumeDto> findRespondersToVacancyById(Long id){
        return null;
    }

    @Override
    public ResumeDto findResumeById(Long id){
        return null;
    }
}

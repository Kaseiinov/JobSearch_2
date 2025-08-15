package kg.attractor.jobsearch.service.impl;


import kg.attractor.jobsearch.model.WorkExperienceInfo;
import kg.attractor.jobsearch.repository.WorkExperienceRepository;
import kg.attractor.jobsearch.service.WorkExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkExperienceServiceImpl implements WorkExperienceService {
    private final WorkExperienceRepository workExperienceRepository;

    @Override
    public void save(WorkExperienceInfo workExperienceInfo){
        workExperienceRepository.save(workExperienceInfo);
    }
}

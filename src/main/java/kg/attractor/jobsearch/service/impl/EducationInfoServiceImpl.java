package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.model.EducationInfo;
import kg.attractor.jobsearch.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceImpl implements EducationInfoService {
    private final EducationRepository educationRepository;

    @Override
    public void save(EducationInfo educationInfo){
        educationRepository.save(educationInfo);
    }
}

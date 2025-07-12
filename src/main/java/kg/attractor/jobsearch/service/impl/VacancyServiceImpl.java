package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.VacancyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyServiceImpl implements VacancyService {

    @Override
    public void create(VacancyDto vacancyDto){

    }

    @Override
    public void editById(VacancyDto vacancyDto, Long id){

    }

    @Override
    public void deleteById(Long id){

    }

    @Override
    public List<VacancyDto> findAllActive(){
        return null;
    }

    @Override
    public List<VacancyDto> findByCategoryId(Long id){
        return null;
    }

    @Override
    public List<VacancyDto> findRespondersToVacancyById(Long id){
        return null;
    }

    @Override
    public VacancyDto findVacancyById(Long id){
        return null;
    }
}

package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.VacancyDto;

import java.util.List;

public interface VacancyService {
    void create(VacancyDto vacancyDto);

    void editById(VacancyDto vacancyDto, Long id);

    void deleteById(Long id);

    List<VacancyDto> findAllActive();

    List<VacancyDto> findByCategoryId(Long id);

    List<VacancyDto> findRespondersToVacancyById(Long id);

    VacancyDto findVacancyById(Long id);
}

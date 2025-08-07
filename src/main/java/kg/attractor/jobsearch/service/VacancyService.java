package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;

import java.util.List;

public interface VacancyService {
    void create(VacancyDto vacancyDto);

    void editById(VacancyDto vacancyDto, Long id);

    void deleteById(Long id);

    List<VacancyDto> findByAuthor(String email);

    List<VacancyDto> findVacanciesByUserResponse(String email);

    List<VacancyDto> findAll();

    List<VacancyDto> findAllActive();

    List<VacancyDto> findByCategory(String category);

    List<UserDto> findRespondersToVacancyById(Long id);

    VacancyDto findVacancyById(Long id);
}

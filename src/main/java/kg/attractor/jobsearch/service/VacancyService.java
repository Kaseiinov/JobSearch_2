package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VacancyService {
    void create(VacancyDto vacancyDto, String email);

    void editById(VacancyDto vacancyDto, Long id, String email);

    void deleteById(Long id);

    List<VacancyDto> findByAuthor(String email);

//    List<VacancyDto> findVacanciesByUserResponse(String email);

    List<VacancyDto> findAll();

    Page<VacancyDto> findAllActive(Pageable pageable);

    List<VacancyDto> findByCategory(String category);

//    List<UserDto> findRespondersToVacancyById(Long id);

    VacancyDto findVacancyById(Long id);
}

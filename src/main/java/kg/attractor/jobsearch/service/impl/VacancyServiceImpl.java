package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.VacancyDao;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.model.Vacancy;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;
    private final UserService userService;

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
    public List<VacancyDto> findVacanciesByUserResponse(String email){
        List<Vacancy> vacancies = vacancyDao.findVacanciesByUserResponse(email);
        return vacancyBuilder(vacancies);
    }

    @Override
    public List<VacancyDto> findAll(){
        List<Vacancy> vacancies = vacancyDao.findAll();
        return vacancyBuilder(vacancies);
    }

    @Override
    public List<VacancyDto> findAllActive(boolean state){
        return null;
    }

    @Override
    public List<VacancyDto> findByCategory(String category){
        List<Vacancy> vacancies = vacancyDao.findByCategory(category);
        return vacancyBuilder(vacancies);
    }

    @Override
    public List<UserDto> findRespondersToVacancyById(Long id){
        List<User> responders = vacancyDao.findRespondersToVacancyById(id);
        return userService.userBuilder(responders);
    }

    @Override
    public VacancyDto findVacancyById(Long id){
        return null;
    }

    public List<VacancyDto> vacancyBuilder(List<Vacancy> vacancies){
        List<VacancyDto> vacancyDtos = vacancies
                .stream()
                .map(r -> VacancyDto.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .description(r.getDescription())
                        .categoryId(r.getCategoryId())
                        .salary(r.getSalary())
                        .isActive(r.getIsActive())
                        .expFrom(r.getExpFrom())
                        .expTo(r.getExpTo())
                        .authorId(r.getAuthorId())
                        .createdDate(r.getCreatedDate())
                        .updateTime(r.getUpdateTime())
                        .build()).toList();
        return vacancyDtos;

    }

    public VacancyDto vacancyBuilder(Vacancy vacancy){
        return VacancyDto.builder()
                .id(vacancy.getId())
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .categoryId(vacancy.getCategoryId())
                .salary(vacancy.getSalary())
                .isActive(vacancy.getIsActive())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .authorId(vacancy.getAuthorId())
                .createdDate(vacancy.getCreatedDate())
                .updateTime(vacancy.getUpdateTime())
                .build();


    }
}

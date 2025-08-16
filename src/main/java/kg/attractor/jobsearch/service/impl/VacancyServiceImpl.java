package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exceptions.VacancyNotFoundException;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.model.Vacancy;
import kg.attractor.jobsearch.repository.CategoryRepository;
import kg.attractor.jobsearch.repository.VacancyRepository;
import kg.attractor.jobsearch.service.CategoryService;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final UserService userService;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final VacancyRepository vacancyRepository;

    @Override
    public void create(VacancyDto vacancyDto, String email){
        if(vacancyDto.getExpFrom() >= vacancyDto.getExpTo()){
            throw new NumberFormatException();
        }

        Vacancy vacancy = Vacancy.builder()
                .id(vacancyDto.getId())
                .name(vacancyDto.getName())
                .description(vacancyDto.getDescription())
                .category(categoryService.findModelCategoryById(vacancyDto.getCategoryId()))
                .salary(vacancyDto.getSalary())
                .expFrom(vacancyDto.getExpFrom())
                .expTo(vacancyDto.getExpTo())
                .isActive(vacancyDto.getIsActive())
                .author(userService.findModelUserByEmail(email))
                .createdDate(LocalDateTime.now())
                .build();

        vacancyRepository.save(vacancy);
        log.info("Vacancy created: {}", vacancy.getName());
    }

    @Override
    public void editById(VacancyDto vacancyDto, Long id, String email){
        if(vacancyDto.getExpFrom() >= vacancyDto.getExpTo()){
            throw new NumberFormatException();
        }
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(VacancyNotFoundException::new);
        vacancy.setName(vacancyDto.getName());
        vacancy.setDescription(vacancyDto.getDescription());
        vacancy.setCategory(categoryService.findModelCategoryById(vacancyDto.getCategoryId()));
        vacancy.setSalary(vacancyDto.getSalary());
        vacancy.setExpFrom(vacancyDto.getExpFrom());
        vacancy.setExpTo(vacancyDto.getExpTo());
        vacancy.setIsActive(vacancyDto.getIsActive());
        vacancy.setUpdateTime(LocalDateTime.now());

        vacancyRepository.save(vacancy);
    }

    @Override
    public void deleteById(Long id){
        vacancyRepository.deleteById(id);
    }

    @Override
    public List<VacancyDto> findByAuthor(String email){
        List<Vacancy> vacancies = vacancyRepository.findVacanciesByAuthor_Email(email);
        return vacancyBuilder(vacancies);
    }

//    @Override
//    public List<VacancyDto> findVacanciesByUserResponse(String email){
//        List<Vacancy> vacancies = vacancyDao.findVacanciesByUserResponse(email);
//        return vacancyBuilder(vacancies);
//    }

    @Override
    public List<VacancyDto> findAll(){
        List<Vacancy> vacancies = vacancyRepository.findAll();
        return vacancyBuilder(vacancies);
    }

    @Override
    public Page<VacancyDto> findAllActive(Pageable pageable){
        return vacancyPageBuilder(vacancyRepository.findVacanciesByIsActive(true, pageable));
    }

    @Override
    public List<VacancyDto> findByCategory(String category){
        List<Vacancy> vacancies = vacancyRepository.findByCategory_Name(category);
        return vacancyBuilder(vacancies);
    }

//    @Override
//    public List<UserDto> findRespondersToVacancyById(Long id){
//        List<User> responders = vacancyDao.findRespondersToVacancyById(id);
////        return userService.userBuilder(responders);
//        return null;
//    }

    @Override
    public VacancyDto findVacancyById(Long id){
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(VacancyNotFoundException::new);
        return vacancyBuilder(vacancy);
    }

    public Page<VacancyDto> vacancyPageBuilder(Page<Vacancy> vacancies){
        List<VacancyDto> vacancyDtos = vacancies
                .stream()
                .map(r -> VacancyDto.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .description(r.getDescription())
                        .categoryId(r.getCategory().getId())
                        .salary(r.getSalary())
                        .isActive(r.getIsActive())
                        .expFrom(r.getExpFrom())
                        .expTo(r.getExpTo())
                        .authorId(r.getAuthor().getId())
                        .createdDate(r.getCreatedDate())
                        .updateTime(r.getUpdateTime())
                        .build()).toList();

        return new PageImpl<>(vacancyDtos, vacancies.getPageable(), vacancies.getTotalElements());

    }

    public List<VacancyDto> vacancyBuilder(List<Vacancy> vacancies){
        return vacancies
                .stream()
                .map(r -> VacancyDto.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .description(r.getDescription())
                        .categoryId(r.getCategory().getId())
                        .salary(r.getSalary())
                        .isActive(r.getIsActive())
                        .expFrom(r.getExpFrom())
                        .expTo(r.getExpTo())
                        .authorId(r.getAuthor().getId())
                        .createdDate(r.getCreatedDate())
                        .updateTime(r.getUpdateTime())
                        .build()).toList();

    }

    public VacancyDto vacancyBuilder(Vacancy vacancy){
        return VacancyDto.builder()
                .id(vacancy.getId())
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .categoryId(vacancy.getCategory().getId())
                .salary(vacancy.getSalary())
                .isActive(vacancy.getIsActive())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .authorId(vacancy.getAuthor().getId())
                .createdDate(vacancy.getCreatedDate())
                .updateTime(vacancy.getUpdateTime())
                .build();


    }
}

package kg.attractor.jobsearch.repository;

import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findVacanciesByAuthor_Email(String authorEmail);

    List<Vacancy> findVacanciesByIsActive(Boolean isActive);

    List<Vacancy> findByCategory_Name(String categoryName);
}

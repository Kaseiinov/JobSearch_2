package kg.attractor.jobsearch.repository;

import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    Page<Vacancy> findVacanciesByAuthor_Email(String authorEmail, Pageable pageable);

    Page<Vacancy> findVacanciesByIsActive(Boolean isActive, Pageable pageable);

    List<Vacancy> findByCategory_Name(String categoryName);
}

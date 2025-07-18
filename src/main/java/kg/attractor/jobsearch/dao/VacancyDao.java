package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.mappers.ResumeMapper;
import kg.attractor.jobsearch.mappers.UserMapper;
import kg.attractor.jobsearch.mappers.VacancyMapper;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Vacancy> findVacanciesByUserResponse(String email) {
        String sql = "SELECT v.* FROM vacancies v " +
                "JOIN responded_applicants ra ON v.id = ra.vacancy_id " +
                "JOIN resumes r ON ra.resume_id = r.id " +
                "JOIN users u ON r.applicant_id = u.id " +
                "WHERE LOWER(u.email) = LOWER(?)";
        return jdbcTemplate.query(sql, new VacancyMapper(), email);
    }

    public List<Vacancy> findAll(){
        String sql = "select * from vacancies";
        return jdbcTemplate.query(sql, new VacancyMapper());
    }

    public List<Vacancy> findByCategory(String category){
        String sql = "select v.* from vacancies v " +
                "join CATEGORIES c on c.id = v.CATEGORY_ID " +
                "where lower(c.NAME) = lower(?)";
        return jdbcTemplate.query(sql, new VacancyMapper(), category);
    }

    public List<User> findRespondersToVacancyById(Long vacancyId) {
        String sql = "SELECT u.* FROM users u " +
                "JOIN resumes r ON u.id = r.applicant_id " +
                "JOIN responded_applicants ra ON ra.resume_id = r.id " +
                "WHERE ra.vacancy_id = ?";
        return jdbcTemplate.query(sql, new UserMapper(), vacancyId);
    }
}

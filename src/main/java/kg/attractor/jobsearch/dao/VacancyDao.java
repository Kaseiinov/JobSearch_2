package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.mappers.ResumeMapper;
import kg.attractor.jobsearch.mappers.UserMapper;
import kg.attractor.jobsearch.mappers.VacancyMapper;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void delete(Long id){
        String sql = "delete from vacancies where id = ?;";
        jdbcTemplate.update(sql, id);
    }

    public void create(Vacancy vacancy) {
        String sql = "INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date) " +
                "VALUES (:name, :description, (select id from CATEGORIES where id = :categoryId), :salary, :expFrom, :expTo, :is_active, (select id from users where id  = :authorId), :createdDate)";

        namedParameterJdbcTemplate.update(sql,
                new MapSqlParameterSource()
                        .addValue("name", vacancy.getName())
                        .addValue("description", vacancy.getDescription())
                        .addValue("categoryId", vacancy.getCategoryId())
                        .addValue("salary", vacancy.getSalary())
                        .addValue("expFrom", vacancy.getExpFrom())
                        .addValue("expTo", vacancy.getExpTo())
                        .addValue("is_active", vacancy.getIsActive())
                        .addValue("authorId", vacancy.getAuthorId())
                        .addValue("createdDate", vacancy.getCreatedDate())
        );
    }

    public void update(Long id, Vacancy vacancy){
        String sql = "UPDATE vacancies SET " +
                "name = :name, " +
                "description = :description, " +
                "category_id = :categoryId, " +
                "salary = :salary, " +
                "exp_from = :expFrom, " +
                "exp_to = :expTo, " +
                "is_active = :isActive, " +
                "update_time = :updateTime " +
                "WHERE id = :id";

        namedParameterJdbcTemplate.update(sql,
                new MapSqlParameterSource()
                        .addValue("name", vacancy.getName())
                        .addValue("description", vacancy.getDescription())
                        .addValue("categoryId", vacancy.getCategoryId())
                        .addValue("salary", vacancy.getSalary())
                        .addValue("expFrom", vacancy.getExpFrom())
                        .addValue("expTo", vacancy.getExpTo())
                        .addValue("isActive", vacancy.getIsActive())
                        .addValue("authorId", vacancy.getAuthorId())
                        .addValue("updateTime", vacancy.getUpdateTime())
                        .addValue("id", id)
        );
    }
    public Optional<Vacancy> findById(Long id){
        String sql = "select * from vacancies where id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new VacancyMapper(), id)
                )
        );
    }

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

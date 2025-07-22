package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.mappers.ResumeMapper;
import kg.attractor.jobsearch.mappers.UserMapper;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void create(Resume resume){
        String sql = "insert into resumes(applicant_id, name, category_id, salary, is_active, created_date)" +
                "values((select id from users where id = :userId), :name, (select id from categories where id = :categoryId), :salary, :is_active, :created_date)";

        namedParameterJdbcTemplate.update(sql,
                new MapSqlParameterSource()
                        .addValue("userId", resume.getApplicantId())
                        .addValue("name", resume.getName())
                        .addValue("categoryId", resume.getCategoryId())
                        .addValue("salary", resume.getSalary())
                        .addValue("is_active", resume.getIsActive())
                        .addValue("created_date", resume.getCreatedDate())
        );
    }

    public void updateResumeById(Long id, Resume resume) {
        String sql = "UPDATE resumes SET " +
                "name = :name, " +
                "category_id = :categoryId, " +
                "salary = :salary, " +
                "is_active = :isActive, " +
                "update_time = :updateTime " +
                "WHERE id = :id";

        namedParameterJdbcTemplate.update(sql,
                new MapSqlParameterSource()
                        .addValue("name", resume.getName())
                        .addValue("categoryId", resume.getCategoryId())
                        .addValue("salary", resume.getSalary())
                        .addValue("isActive", resume.getIsActive())
                        .addValue("updateTime", resume.getUpdateTime())
                        .addValue("id", id)
        );
    }
    public List<Resume> findByCategory(String category){
        String sql = "select r.* from resumes r\n" +
                "join categories c on r.category_id = c.id\n" +
                "where lower(c.name) = lower(?);";
        return jdbcTemplate.query(sql, new ResumeMapper(), category);
    }

    public List<Resume> findByAuthor(String email){
        String sql = "select r.* from resumes r\n" +
                "join users u on u.id = r.applicant_id\n" +
                "where lower(u.email) = lower(?);";
        return jdbcTemplate.query(sql, new ResumeMapper(), email);
    }

    public Optional<Resume> findById(Long id){
        String sql = "select * from resumes where id = ?;";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new ResumeMapper(), id)
                )
        );
    }

    public void deleteById(Long id){
        String sql = "delete from resumes where id = ?;";
        jdbcTemplate.update(sql, id);
    }
}

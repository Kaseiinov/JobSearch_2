package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.mappers.ResumeMapper;
import kg.attractor.jobsearch.mappers.UserMapper;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;

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
}

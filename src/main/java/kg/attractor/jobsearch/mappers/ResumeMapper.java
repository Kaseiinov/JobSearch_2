package kg.attractor.jobsearch.mappers;

import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResumeMapper implements RowMapper<Resume> {
    @Override
    public Resume mapRow(ResultSet rs, int rowNum) throws SQLException {
        Resume resume = new Resume();
        resume.setId(rs.getLong("id"));
        resume.setName(rs.getString("name"));
        resume.setApplicantId(rs.getLong("applicant_id"));
        resume.setCategoryId(rs.getLong("category_id"));
        resume.setSalary(rs.getDouble("salary"));
        resume.setIsActive(rs.getBoolean("is_active"));
        resume.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
        resume.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
        return resume;
    }
}

//package kg.attractor.jobsearch.model.mappers;
//
//import kg.attractor.jobsearch.model.Resume;
//import kg.attractor.jobsearch.model.User;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Optional;
//
//public class ResumeMapper implements RowMapper<Resume> {
//    @Override
//    public Resume mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Resume resume = new Resume();
//        resume.setId(rs.getLong("id"));
//        resume.setName(rs.getString("name"));
//        resume.setApplicant(rs.getLong("applicant_id"));
//        resume.setCategory(rs.getLong("category_id"));
//        resume.setSalary(rs.getDouble("salary"));
//        resume.setIsActive(rs.getBoolean("is_active"));
//        resume.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
//
//        Timestamp updateTime = rs.getTimestamp("update_time");
//        resume.setUpdateTime(updateTime != null ? updateTime.toLocalDateTime() : null);
//        return resume;
//    }
//}

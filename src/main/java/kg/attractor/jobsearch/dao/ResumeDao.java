package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.mappers.ResumeMapper;
import kg.attractor.jobsearch.model.ContactInfo;
import kg.attractor.jobsearch.model.EducationInfo;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.WorkExperienceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void create(Resume resume){
        String sql = "insert into resumes(applicant_id, name, category_id, salary, is_active, created_date)" +
                "values((select id from users where id = :userId), :name, (select id from categories where id = :categoryId), :salary, :is_active, :created_date);";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", resume.getApplicantId())
                .addValue("name", resume.getName())
                .addValue("categoryId", resume.getCategoryId())
                .addValue("salary", resume.getSalary())
                .addValue("is_active", resume.getIsActive())
                .addValue("created_date", resume.getCreatedDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, parameters, keyHolder);
        Long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        createEducations(resume.getEducations(), generatedId);
        createExperience(resume.getExperience(), generatedId);
        createContactInfo(resume.getContacts(), generatedId);
    }

    public void createEducations(List<EducationInfo> educations, Long id){
        String sql = "insert into education_info(resume_id, institution, program, start_date, end_date, degree)" +
                "values((select id from resumes where id = :resumeId), :institution, :program, :start_date, :end_date, :degree);";

        educations.forEach(education -> {
            namedParameterJdbcTemplate.update(sql,
                    new MapSqlParameterSource()
                            .addValue("resumeId", id)
                            .addValue("institution", education.getInstitution())
                            .addValue("program", education.getProgram())
                            .addValue("start_date", education.getStartDate())
                            .addValue("end_date", education.getEndDate())
                            .addValue("degree", education.getDegree())
                    ) ;
        });

    }

    public void createExperience(List<WorkExperienceInfo> exp, Long id){
        String sql = "insert into WORK_EXPERIENCE_INFO(resume_id, years, COMPANY_NAME, POSITION, RESPONSIBILITIES)" +
                "values((select id from resumes where id = :resumeId), :years, :companyName, :position, :responsibilities);";

        exp.forEach(e -> {
            namedParameterJdbcTemplate.update(sql,
                    new MapSqlParameterSource()
                            .addValue("resumeId", id)
                            .addValue("years", e.getYears())
                            .addValue("companyName", e.getCompanyName())
                            .addValue("position", e.getPosition())
                            .addValue("responsibilities", e.getResponsibilities())
            ) ;
        });
    }

    public void createContactInfo(List<ContactInfo> contact, Long id){
        String sql = "insert into CONTACTS_INFO(TYPE_ID, RESUME_ID, INFO_VALUE)" +
                "values(:typeId, (select id from resumes where id = :resumeId), :infoValue);";

        contact.forEach(c -> {
            namedParameterJdbcTemplate.update(sql,
                    new MapSqlParameterSource()
                            .addValue("resumeId", id)
                            .addValue("typeId", c.getTypeId())
                            .addValue("infoValue", c.getValue())
            ) ;
        });
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

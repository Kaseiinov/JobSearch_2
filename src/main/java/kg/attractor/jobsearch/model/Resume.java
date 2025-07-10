package kg.attractor.jobsearch.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class Resume {
    private Long id;
    private User applicant_id;
    private String name;
    private Category category_id;
    private Double salary;
    private Boolean is_active;
    private LocalDateTime created_date;
    private LocalDateTime update_time;
    private WorkExperienceInfo experience;
    private EducationInfo education;
    private List<ContactInfo> contacts;

}

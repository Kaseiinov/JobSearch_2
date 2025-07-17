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
    private User applicantId;
    private String name;
    private Category categoryId;
    private Double salary;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    private WorkExperienceInfo experience;
    private EducationInfo education;
    private List<ContactInfo> contacts;

}

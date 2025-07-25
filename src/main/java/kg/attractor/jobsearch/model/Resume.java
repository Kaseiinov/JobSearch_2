package kg.attractor.jobsearch.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    private Long id;
    private Long applicantId;
    private String name;
    private Long categoryId;
    private Double salary;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    private List<WorkExperienceInfo> experience;
    private List<EducationInfo> educations;
    private List<ContactInfo> contacts;

}

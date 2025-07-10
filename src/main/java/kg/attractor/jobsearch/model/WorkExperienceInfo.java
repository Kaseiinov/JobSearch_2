package kg.attractor.jobsearch.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkExperienceInfo {

    private Long id;
    private Resume resume_id;
    private Integer years;
    private String company_name;
    private String position;
    private String responsibilities;
}
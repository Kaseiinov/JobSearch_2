package kg.attractor.jobsearch.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class EducationInfo {
    private Long id;
    private Resume resume_id;
    private String institution;
    private String program;
    private LocalDate start_date;
    private LocalDate end_date;
    private String degree;
}

package kg.attractor.jobsearch.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RespondedApplicant {
    private Long id;
    private Resume resume_id;
    private Vacancy vacancy_id;
    private Boolean confirmation;
}
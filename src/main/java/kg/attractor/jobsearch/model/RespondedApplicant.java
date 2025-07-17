package kg.attractor.jobsearch.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RespondedApplicant {
    private Long id;
    private Resume resumeId;
    private Vacancy vacancyId;
    private Boolean confirmation;
}
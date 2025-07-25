package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceInfoDto {
    private Long id;
    @NotNull
    private Long resumeId;
    @NotNull
    private Integer years;
    @NotNull
    private String companyName;
    @NotNull
    private String position;
    @NotNull
    private String responsibilities;
}

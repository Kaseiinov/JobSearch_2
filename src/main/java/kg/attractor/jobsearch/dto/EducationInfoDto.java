package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationInfoDto {
    private Long id;
    @NotNull
    private Long resumeId;
    @NotNull
    private String institution;
    private String program;
    @NotNull
    @Past
    private LocalDate startDate;
    private LocalDate endDate;
    private String degree;
}

package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    @Positive
    private Long categoryId;
    @NotNull
    @PositiveOrZero
    private Double salary;
    @Positive
    private Integer expFrom;
    @Positive
    private Integer expTo;
    @NotNull
    private Boolean isActive;
    @NotNull
    @Positive
    private Long authorId;
    @Past
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

    public String getCreatedDateFormatted() {
        return createdDate != null
                ? createdDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
                : "";
    }

    public String getUpdateTimeFormatted() {
        return updateTime != null
                ? updateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
                : "";
    }
}

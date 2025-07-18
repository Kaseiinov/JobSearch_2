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
    private Long categoryId;
    @NotNull
    @PositiveOrZero
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    @NotNull
    private Boolean isActive;
    private Long authorId;
    @Past
    private LocalDateTime createdDate;
    @Future
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

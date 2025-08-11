package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDto {
    private Long id;
    @Positive
    private Long applicantId;
    @NotBlank
    private String name;
    @NotNull
    @Positive
    private Long categoryId;
    @NotNull
    @PositiveOrZero
    private Double salary;
    @NotNull
    private Boolean isActive;
    @Past
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    private List<WorkExperienceInfoDto> workExperience;
    private List<EducationInfoDto> educations;
    private List<ContactsInfoDto> contacts;


//    public String getCreatedDateFormatted() {
//        return createdDate != null
//                ? createdDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
//                : "";
//    }
//
//    public String getUpdateTimeFormatted() {
//        return updateTime != null
//                ? updateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
//                : "";
//    }
}

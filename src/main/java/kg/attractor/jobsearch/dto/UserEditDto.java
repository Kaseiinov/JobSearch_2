package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEditDto {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Min(18)
    private Integer age;
    @NotBlank
    @Size(min = 4, max = 24, message = "Length must be >= 4 and <= 24")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{4,24}$", message = "Should contain at least one uppercase letter and one lowercase letter and at least one number")
    private String password;
    @NotBlank
    private String phoneNumber;

    @NotBlank
    @Pattern(regexp = "(?i)^(applicant|employer)$", message = "Must be either 'applicant' or 'employer' (case-insensitive)")
    private String accountType;

}
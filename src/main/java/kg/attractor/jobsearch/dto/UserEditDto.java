package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEditDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Min(18)
    private Integer age;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String phoneNumber;
    private UserImageDto avatar;

    @NotBlank
    private String accountType;

    private Boolean enabled;
    private Long roleId;
}
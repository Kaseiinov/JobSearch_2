package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactsInfoDto {
    private Long id;
    @NotNull
    private Long typeId;
    @NotNull
    private Long resumeId;
    @NotBlank
    private String value;
}

package kg.attractor.jobsearch.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserImage {
    private Long id;
    private Long userId;
    private String fileName;
}

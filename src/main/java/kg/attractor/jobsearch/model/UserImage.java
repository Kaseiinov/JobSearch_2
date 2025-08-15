package kg.attractor.jobsearch.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserImage {

    private Long id;

    private User user;
    private String fileName;
}

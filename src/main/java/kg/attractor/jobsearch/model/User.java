package kg.attractor.jobsearch.model;

import lombok.*;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;
    private String phoneNumber;
    private String accountType;
    private UserImage avatar;
    private String avatarString;
    private Boolean enabled;
    private Long roleId;

}

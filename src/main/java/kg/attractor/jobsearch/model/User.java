package kg.attractor.jobsearch.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class User {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;
    private String phone_number;
    private String account_type;
    private String avatar;

}

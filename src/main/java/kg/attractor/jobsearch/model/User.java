package kg.attractor.jobsearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "account_type")
    private String accountType;
    @OneToOne(fetch = FetchType.LAZY)
    private UserImage avatar;
    private Boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Collection<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Collection<Vacancy> vacancies;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicant")
    private Collection<Resume> resumes;

}

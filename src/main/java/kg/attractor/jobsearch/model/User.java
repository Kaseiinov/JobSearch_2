package kg.attractor.jobsearch.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {
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
    private String avatar;
    private Boolean enabled;
    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usr_roles",
        joinColumns =@JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Collection<Vacancy> vacancies;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicant")
    private Collection<Resume> resumes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.iterator().next().getRole()));
    }

    @Override
    public String getUsername() {
        return "";
    }
}

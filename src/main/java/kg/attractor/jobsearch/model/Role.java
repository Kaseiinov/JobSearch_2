package kg.attractor.jobsearch.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter

public class Role {
    private Long id;
    private String role;
    private List<Authority> authorities;


}

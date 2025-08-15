package kg.attractor.jobsearch.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long parentId;

    @OneToMany(mappedBy = "category")
    private Collection<Vacancy> vacancies;

    @OneToMany(mappedBy = "category")
    private Collection<Resume> resumes;

}

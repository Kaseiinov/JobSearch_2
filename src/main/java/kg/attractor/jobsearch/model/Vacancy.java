package kg.attractor.jobsearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    private Boolean isActive;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "vacancy")
    private Collection<RespondedApplicant> responses;

}

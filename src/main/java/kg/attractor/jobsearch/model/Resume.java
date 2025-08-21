package kg.attractor.jobsearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private User applicant;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    private Double salary;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<WorkExperienceInfo> experience;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<EducationInfo> educations;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<ContactInfo> contacts;

    @OneToMany(mappedBy = "resume")
    private Collection<ContactInfo> contactInfos;

    @OneToMany(mappedBy = "resume")
    private Collection<RespondedApplicant> responses;

}

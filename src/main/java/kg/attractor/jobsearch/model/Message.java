package kg.attractor.jobsearch.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "responded_applicants_id")
    private Integer respondedApplicants;
    private String content;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
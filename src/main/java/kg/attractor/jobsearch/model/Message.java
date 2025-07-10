package kg.attractor.jobsearch.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private Long id;
    private Integer respondedApplicants;
    private String content;
    private LocalDateTime timestamp;
}
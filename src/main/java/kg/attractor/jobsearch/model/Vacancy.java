package kg.attractor.jobsearch.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter

public class Vacancy {
    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    private Boolean isActive;
    private Long authorId;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

}

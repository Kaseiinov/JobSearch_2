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
    private Category categoryId;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    private Boolean isActive;
    private User authorId;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

}

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
    private Category category_id;
    private Double salary;
    private Integer exp_from;
    private Integer exp_to;
    private Boolean is_active;
    private User author_id;
    private LocalDateTime created_date;
    private LocalDateTime update_time;

}

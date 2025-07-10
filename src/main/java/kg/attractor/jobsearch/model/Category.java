package kg.attractor.jobsearch.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Category {
    private Long id;
    private String name;
    private Long parentId;

}

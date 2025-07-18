package kg.attractor.jobsearch.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactInfo {
    private Long id;
    private ContactType type;
    private String value;
}

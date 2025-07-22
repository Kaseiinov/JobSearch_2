package kg.attractor.jobsearch.exceptions;

import java.util.NoSuchElementException;

public class VacancyNotFoundException extends NoSuchElementException {
    public VacancyNotFoundException() {
        super("Vacancy not found");
    }
}

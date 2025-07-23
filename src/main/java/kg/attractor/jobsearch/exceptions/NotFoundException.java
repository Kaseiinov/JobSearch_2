package kg.attractor.jobsearch.exceptions;

import java.util.NoSuchElementException;

public class NotFoundException extends NoSuchElementException {
    public NotFoundException() {
        super("Not found");
    }
}

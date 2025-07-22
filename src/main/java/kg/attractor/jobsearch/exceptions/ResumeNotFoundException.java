package kg.attractor.jobsearch.exceptions;

import java.util.NoSuchElementException;

public class ResumeNotFoundException extends NoSuchElementException {
    public ResumeNotFoundException() {
        super("Resume not found");
    }
}

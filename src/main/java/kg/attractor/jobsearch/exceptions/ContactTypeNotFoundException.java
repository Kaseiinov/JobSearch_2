package kg.attractor.jobsearch.exceptions;

import java.util.NoSuchElementException;

public class ContactTypeNotFoundException extends NoSuchElementException {
    public ContactTypeNotFoundException() {
        super("Contact type not found");
    }
}

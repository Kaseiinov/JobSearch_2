package kg.attractor.jobsearch.exceptions;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {
    public UserNotFoundException() {
        super("User not found");
    }
}

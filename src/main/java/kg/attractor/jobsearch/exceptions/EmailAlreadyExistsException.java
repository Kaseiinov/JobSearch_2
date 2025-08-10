package kg.attractor.jobsearch.exceptions;

public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException() {
        super("Email already exists");
    }
}

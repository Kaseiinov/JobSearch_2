package kg.attractor.jobsearch.exceptions;

import java.util.NoSuchElementException;

public class CategoryNotFoundException extends NoSuchElementException {
    public CategoryNotFoundException() {
        super("Category not found");
    }
}

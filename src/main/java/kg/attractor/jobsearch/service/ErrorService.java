package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.exceptions.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {
    ErrorResponseBody makeResponse(BindingResult bindingResult);
}

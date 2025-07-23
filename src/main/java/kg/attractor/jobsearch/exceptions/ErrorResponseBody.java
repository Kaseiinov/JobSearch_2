package kg.attractor.jobsearch.exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ErrorResponseBody {
    private String title;
    private Map<String, List<String>> response;
}

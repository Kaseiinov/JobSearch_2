package kg.attractor.jobsearch.exceptions;

import com.sun.net.httpserver.HttpServer;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearch.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.time.DateTimeException;
import java.util.NoSuchElementException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final ErrorService errorService;

//    @ExceptionHandler(NotFoundException.class)
//    public ErrorResponse handleNotFoundException(NotFoundException ex){
//        return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage()).build();
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponseBody> validationHandler (MethodArgumentNotValidException ex){
//        return new ResponseEntity<>(errorService.makeResponse(ex.getBindingResult()), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(NumberFormatException.class)
//    public ErrorResponse numberFormatHandler (NumberFormatException ex){
//        return ErrorResponse.builder(ex, HttpStatus.BAD_REQUEST, ex.getMessage()).build();
//    }
//    @ExceptionHandler(DateTimeException.class)
//    public ErrorResponse DateTimeExceptionHandler (DateTimeException ex){
//        return ErrorResponse.builder(ex, HttpStatus.BAD_REQUEST, ex.getMessage()).build();
//    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNotFoundException(HttpServletRequest request, Model model){
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
        model.addAttribute("details", request);

        return "errors/error";
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public String handleEmailAlreadyExists(Model model, HttpServletRequest request) {
        model.addAttribute("status", HttpStatus.CONFLICT.value());
        model.addAttribute("reason", HttpStatus.CONFLICT.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormatException(Model model, HttpServletRequest request) {
        model.addAttribute("status", HttpStatus.BAD_REQUEST);
        model.addAttribute("reason", HttpStatus.BAD_REQUEST.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseBody> validationHandler(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(errorService.makeResponse(e.getBindingResult()), HttpStatus.BAD_REQUEST);
    }
}


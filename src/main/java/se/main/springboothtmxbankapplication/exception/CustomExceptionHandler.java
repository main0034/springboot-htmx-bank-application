package se.main.springboothtmxbankapplication.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handling for the application. In reality a lot of different handlers should be defined here, but they are
 * omitted for simplicity.
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler
    protected ResponseEntity<String> handleConflict(Exception ex, WebRequest request) {
        LOGGER.atError()
                .setCause(ex)
                .log(() -> "Very expensive log message: " + ex.getMessage());
        return new ResponseEntity<>("Internal server error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

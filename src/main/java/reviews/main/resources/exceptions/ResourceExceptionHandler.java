package reviews.main.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reviews.main.resources.exceptions.customs.StandardError;
import reviews.main.resources.exceptions.customs.ValidationError;
import reviews.main.services.exceptions.DataIntegrityException;
import reviews.main.services.exceptions.ObjectNotFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandardError> objectNotFound(
      ObjectNotFoundException exception,
      HttpServletRequest request
  ) {
    StandardError error = new StandardError(
        HttpStatus.NOT_FOUND.value(),
        exception.getMessage(),
        System.currentTimeMillis()
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(DataIntegrityException.class)
  public ResponseEntity<StandardError> dataIntegrity(
      DataIntegrityException exception,
      HttpServletRequest request
  ) {
    StandardError error = new StandardError(
        HttpStatus.BAD_REQUEST.value(),
        exception.getMessage(),
        System.currentTimeMillis()
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationError> validation(
      MethodArgumentNotValidException exception,
      HttpServletRequest request
  ) {
    ValidationError error = new ValidationError(
        HttpStatus.BAD_REQUEST.value(),
        "Validation Error!",
        System.currentTimeMillis()
    );
    exception
        .getBindingResult()
        .getFieldErrors()
        .forEach(fieldError ->
            error.addError(fieldError.getField(), fieldError.getDefaultMessage())
        );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }
}

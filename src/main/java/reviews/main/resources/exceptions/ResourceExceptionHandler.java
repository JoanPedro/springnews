package reviews.main.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
}

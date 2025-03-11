package me.dio.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

   private final Logger logger = Logger.getLogger(String.valueOf(GlobalExceptionHandler.class));

   @ExceptionHandler(IllegalArgumentException.class)
   public ResponseEntity<String> handleBusinessException(IllegalArgumentException exception) {
      return new ResponseEntity<String>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
   }

   @ExceptionHandler(NoSuchElementException.class)
   public ResponseEntity<String> handleNotFoundException(NoSuchElementException exception) {
      return new ResponseEntity<String>("Resource ID not found", HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler(RuntimeException.class)
   public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
      logger.severe(exception.getMessage());
      logger.info(exception.getMessage());
      return new ResponseEntity<String>("Unexpected server error, see the logs", HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<String> handleException(Exception exception) {
      logger.severe(exception.getMessage());
      logger.info(exception.getMessage());
      return new ResponseEntity<String>("Unexpected server error, see the logs", HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
package br.com.giulianabezerra.picpaydesafiobackend.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransactionExceptionHandler {

  @ExceptionHandler(InvalidTransactionException.class)
  public ResponseEntity<Object> handle(InvalidTransactionException exception) {
    return ResponseEntity.badRequest().body(exception.getMessage());
  }
}

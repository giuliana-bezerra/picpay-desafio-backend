package br.com.giulianabezerra.picpaydesafiobackend.transaction;

public class UnauthorizedTransactionException extends RuntimeException {
  public UnauthorizedTransactionException(String message) {
    super(message);
  }
}

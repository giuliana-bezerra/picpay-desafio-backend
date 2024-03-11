package br.com.giulianabezerra.picpaydesafiobackend.transaction;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction")
public class TransactionController {
  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping
  public List<Transaction> list() {
    return transactionService.list();
  }

  @PostMapping
  public Transaction createTransaction(@RequestBody Transaction transaction) {
    return transactionService.create(transaction);
  }

}

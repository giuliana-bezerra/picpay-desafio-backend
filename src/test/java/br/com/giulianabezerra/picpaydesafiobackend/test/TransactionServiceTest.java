package br.com.giulianabezerra.picpaydesafiobackend.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.giulianabezerra.picpaydesafiobackend.authorization.AuthorizerService;
import br.com.giulianabezerra.picpaydesafiobackend.notification.NotificationService;
import br.com.giulianabezerra.picpaydesafiobackend.transaction.InvalidTransactionException;
import br.com.giulianabezerra.picpaydesafiobackend.transaction.Transaction;
import br.com.giulianabezerra.picpaydesafiobackend.transaction.TransactionRepository;
import br.com.giulianabezerra.picpaydesafiobackend.transaction.TransactionService;
import br.com.giulianabezerra.picpaydesafiobackend.wallet.Wallet;
import br.com.giulianabezerra.picpaydesafiobackend.wallet.WalletRepository;
import br.com.giulianabezerra.picpaydesafiobackend.wallet.WalletType;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
  @InjectMocks
  private TransactionService transactionService;

  @Mock
  private TransactionRepository transactionRepository;

  @Mock
  private WalletRepository walletRepository;

  @Mock
  private AuthorizerService authorizerService;

  @Mock
  private NotificationService notificationService;

  @Test
  public void testCreateTransactionSuccess() {
    var transaction = new Transaction(null, 1L, 2L, new BigDecimal(1000), null);
    var payee = new Wallet(transaction.payee(), null, null, null, null, WalletType.COMUM.getValue(),
        BigDecimal.ZERO, 1L);
    var payer = new Wallet(transaction.payer(), null, null, null, null, WalletType.COMUM.getValue(),
        new BigDecimal(1000), 1L);

    when(walletRepository.findById(transaction.payee())).thenReturn(Optional.of(payee));
    when(walletRepository.findById(transaction.payer())).thenReturn(Optional.of(payer));
    when(transactionRepository.save(transaction)).thenReturn(transaction);

    var newTransaction = transactionService.create(transaction);

    assertEquals(transaction, newTransaction);
  }

  @ParameterizedTest
  @MethodSource("providesInvalidTransactions")
  public void testCreateInvalidTransaction(Transaction transaction) {
    assertThrows(InvalidTransactionException.class,
        () -> transactionService.create(transaction));
  }

  private static Stream<Arguments> providesInvalidTransactions() {
    var transactionLojista = new Transaction(null, 2L, 1L, new BigDecimal(1000), null);
    var transactionInsuficientBalance = new Transaction(null, 1L, 2L, new BigDecimal(1001), null);
    var transactionPayerEqualsPayee = new Transaction(null, 1L, 1L, new BigDecimal(1000), null);
    var transactionUnexistingPayee = new Transaction(null, 1L, 11L, new BigDecimal(1000), null);
    var transactionUnexistingPayer = new Transaction(null, 11L, 1L, new BigDecimal(1000), null);

    return Stream.of(
        Arguments.of(transactionLojista),
        Arguments.of(transactionInsuficientBalance),
        Arguments.of(transactionPayerEqualsPayee),
        Arguments.of(transactionUnexistingPayee),
        Arguments.of(transactionUnexistingPayer));
  }

}

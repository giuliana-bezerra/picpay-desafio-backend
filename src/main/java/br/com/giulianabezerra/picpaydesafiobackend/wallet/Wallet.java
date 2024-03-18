package br.com.giulianabezerra.picpaydesafiobackend.wallet;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

@Table("WALLETS")
public record Wallet(
    @Id Long id,
    String fullName,
    Long cpf,
    String email,
    String password,
    int type,
    BigDecimal balance,
    @Version Long version) {

  public Wallet debit(BigDecimal value) {
    return new Wallet(id, fullName, cpf, email, password, type,
        balance.subtract(value), version);
  }

  public Wallet credit(BigDecimal value) {
    return new Wallet(id, fullName, cpf, email, password, type, balance.add(value), version);
  }
}

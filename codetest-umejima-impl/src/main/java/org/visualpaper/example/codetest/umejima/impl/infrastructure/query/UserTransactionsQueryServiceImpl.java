package org.visualpaper.example.codetest.umejima.impl.infrastructure.query;

import java.util.List;
import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.visualpaper.example.codetest.umejima.impl.domain.transaction.Transaction;
import org.visualpaper.example.codetest.umejima.impl.domain.transaction.TransactionRepository;
import org.visualpaper.example.codetest.umejima.impl.domain.user.User;
import org.visualpaper.example.codetest.umejima.impl.domain.user.UserTransactions;
import org.visualpaper.example.codetest.umejima.impl.domain.user.UserTransactionsQueryService;

@Repository
public class UserTransactionsQueryServiceImpl implements UserTransactionsQueryService {

  private final TransactionRepository repository;

  @Autowired
  public UserTransactionsQueryServiceImpl(@Nonnull TransactionRepository repository) {
    this.repository = repository;
  }

  @Nonnull
  @Override
  public UserTransactions query(@Nonnull User user) {
    List<Transaction> transactions = repository.allOfUserId(user.getUserId());

    return new UserTransactions(
        user,
        transactions
    );
  }
}

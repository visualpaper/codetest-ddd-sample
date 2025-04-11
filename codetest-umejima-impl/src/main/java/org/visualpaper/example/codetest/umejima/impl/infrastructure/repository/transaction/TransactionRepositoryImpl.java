package org.visualpaper.example.codetest.umejima.impl.infrastructure.repository.transaction;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.visualpaper.example.codetest.umejima.impl.domain.transaction.Amount;
import org.visualpaper.example.codetest.umejima.impl.domain.transaction.Transaction;
import org.visualpaper.example.codetest.umejima.impl.domain.transaction.TransactionRepository;
import org.visualpaper.example.codetest.umejima.impl.domain.user.UserId;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestException;
import org.visualpaper.example.codetest.umejima.impl.exceptions.infrastructure.DbUnexpectedException;
import org.visualpaper.example.codetest.umejima.impl.infrastructure.db.dao.TransactionsDao;
import org.visualpaper.example.codetest.umejima.impl.infrastructure.db.dto.TransactionsDto;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

  private final TransactionsDao dao;

  @Autowired
  public TransactionRepositoryImpl(@Nonnull TransactionsDao dao) {
    this.dao = dao;
  }

  @Nonnull
  @Override
  public List<Transaction> allOfUserId(@Nonnull UserId userId) {
    List<TransactionsDto> dtos = dao.findAllByUser(userId.getValue());

    return dtos.stream()
        .map(this::toTransaction)
        .collect(Collectors.toList());
  }

  @Override
  public void add(@Nonnull Transaction transaction) throws CodetestException {

    try {
      dao.save(toDto(transaction));
    } catch (DataAccessException e) {
      throw new DbUnexpectedException(e);
    }
  }

  @Nonnull
  private TransactionsDto toDto(@Nonnull Transaction transaction) {
    return new TransactionsDto(
        null,
        transaction.getUserId().getValue(),
        transaction.getAmount().getValue(),
        transaction.getDescription()
    );
  }

  @Nonnull
  private Transaction toTransaction(@Nonnull TransactionsDto dto) {
    return new Transaction(
        UserId.of(dto.getUserId()),
        Amount.from(dto.getAmount()),
        dto.getDescription()
    );
  }
}

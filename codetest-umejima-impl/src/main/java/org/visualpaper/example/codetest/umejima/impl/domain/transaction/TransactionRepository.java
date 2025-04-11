package org.visualpaper.example.codetest.umejima.impl.domain.transaction;

import java.util.List;
import javax.annotation.Nonnull;
import org.visualpaper.example.codetest.umejima.impl.domain.user.UserId;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestException;

public interface TransactionRepository {

  @Nonnull
  List<Transaction> allOfUserId(@Nonnull UserId userId);

  void add(@Nonnull Transaction transaction) throws CodetestException;
}

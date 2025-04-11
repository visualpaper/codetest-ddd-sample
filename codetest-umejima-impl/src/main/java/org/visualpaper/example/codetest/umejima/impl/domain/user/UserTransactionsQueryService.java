package org.visualpaper.example.codetest.umejima.impl.domain.user;

import javax.annotation.Nonnull;

public interface UserTransactionsQueryService {

  @Nonnull
  UserTransactions query(@Nonnull User user);
}

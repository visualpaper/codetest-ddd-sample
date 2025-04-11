package org.visualpaper.example.codetest.umejima.impl.domain.user;

import javax.annotation.Nonnull;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestException;

public interface UserRepository {

  @Nonnull
  User ofIdForUpdateOrThrow(
      @Nonnull UserId userId
  ) throws CodetestException;
}

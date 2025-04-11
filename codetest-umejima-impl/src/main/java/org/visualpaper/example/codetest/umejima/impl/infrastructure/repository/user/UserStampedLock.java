package org.visualpaper.example.codetest.umejima.impl.infrastructure.repository.user;

import javax.annotation.Nonnull;
import org.visualpaper.example.codetest.umejima.impl.domain.user.User;
import org.visualpaper.example.codetest.umejima.impl.domain.user.UserId;
import org.visualpaper.example.codetest.umejima.impl.domain.user.UserRepository;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestException;
import org.visualpaper.example.codetest.umejima.impl.exceptions.application.ConflictException;
import org.visualpaper.example.codetest.umejima.impl.exceptions.infrastructure.CodetestLockException;
import org.visualpaper.example.codetest.umejima.impl.infrastructure.db.StampedLock;

public class UserStampedLock implements StampedLock<User> {

  private final UserRepository repository;
  private final UserId userId;

  public UserStampedLock(
      @Nonnull UserRepository repository,
      @Nonnull UserId userId
  ) {
    this.repository = repository;
    this.userId = userId;
  }

  @Nonnull
  public static UserStampedLock create(
      @Nonnull UserRepository repository,
      @Nonnull UserId userId
  ) {
    return new UserStampedLock(
        repository,
        userId
    );
  }

  @Nonnull
  @Override
  public User tryLock() throws CodetestException {

    try {
      return repository.ofIdForUpdateOrThrow(userId);
    } catch (CodetestLockException e) {
      throw new ConflictException(e);
    }
  }
}

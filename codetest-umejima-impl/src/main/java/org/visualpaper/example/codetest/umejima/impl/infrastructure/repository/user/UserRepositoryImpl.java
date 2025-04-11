package org.visualpaper.example.codetest.umejima.impl.infrastructure.repository.user;

import java.util.Optional;
import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Repository;
import org.visualpaper.example.codetest.umejima.impl.domain.user.User;
import org.visualpaper.example.codetest.umejima.impl.domain.user.UserId;
import org.visualpaper.example.codetest.umejima.impl.domain.user.UserRepository;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestException;
import org.visualpaper.example.codetest.umejima.impl.exceptions.infrastructure.DbLockConflictException;
import org.visualpaper.example.codetest.umejima.impl.infrastructure.db.dao.UsersDao;
import org.visualpaper.example.codetest.umejima.impl.infrastructure.db.dto.UsersDto;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private final UsersDao dao;

  @Autowired
  public UserRepositoryImpl(@Nonnull UsersDao dao) {
    this.dao = dao;
  }

  @Nonnull
  @Override
  public User ofIdForUpdateOrThrow(
      @Nonnull UserId userId
  ) throws CodetestException {
    Optional<UsersDto> optDto;

    try {
      optDto = dao.findById(userId.getValue());
    } catch (PessimisticLockingFailureException e) {
      throw new DbLockConflictException(e);
    }

    if (!optDto.isPresent()) {
      throw new DbLockConflictException();
    }
    UsersDto dto = optDto.get();

    return new User(
        UserId.of(dto.getId()),
        dto.getName(),
        dto.getApiKey()
    );
  }
}

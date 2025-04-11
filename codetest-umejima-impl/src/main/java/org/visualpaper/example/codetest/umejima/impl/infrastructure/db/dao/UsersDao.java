package org.visualpaper.example.codetest.umejima.impl.infrastructure.db.dao;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.visualpaper.example.codetest.umejima.impl.infrastructure.db.dto.UsersDto;

@Repository
public interface UsersDao extends JpaRepository<UsersDto, Integer> {

  @Nonnull
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<UsersDto> findById(Integer id);
}

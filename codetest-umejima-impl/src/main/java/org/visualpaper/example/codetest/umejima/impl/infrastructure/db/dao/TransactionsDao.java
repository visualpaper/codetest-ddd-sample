package org.visualpaper.example.codetest.umejima.impl.infrastructure.db.dao;

import java.util.List;
import javax.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.visualpaper.example.codetest.umejima.impl.infrastructure.db.dto.TransactionsDto;

@Repository
public interface TransactionsDao extends JpaRepository<TransactionsDto, Integer> {

  @Nonnull
  @Query(value =
      "  SELECT * "
          + "  FROM transactions "
          + " WHERE user_id = ?1 "
      , nativeQuery = true
  )
  List<TransactionsDto> findAllByUser(int userId);
}

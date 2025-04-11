package org.visualpaper.example.codetest.umejima.impl.exceptions.infrastructure;

import javax.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.visualpaper.example.codetest.umejima.impl.CodetestLogCode;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestException;

/**
 * DB Lock 取得時に衝突が発生.
 */
public class DbLockConflictException extends CodetestException {

  public DbLockConflictException() {
    super(CodetestLogCode.LOCK_CONFLICT, HttpStatus.CONFLICT);
  }

  public DbLockConflictException(@Nullable Throwable cause) {
    super(CodetestLogCode.LOCK_CONFLICT, HttpStatus.CONFLICT, cause);
  }
}

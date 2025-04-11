package org.visualpaper.example.codetest.umejima.impl.exceptions.infrastructure;

import javax.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.visualpaper.example.codetest.umejima.impl.CodetestLogCode;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestException;

/**
 * Lock 取得時エラー.
 */
public class CodetestLockException extends CodetestException {

  public CodetestLockException(
      @Nonnull CodetestLogCode logCode,
      @Nonnull HttpStatus status
  ) {
    super(logCode, status);
  }
}

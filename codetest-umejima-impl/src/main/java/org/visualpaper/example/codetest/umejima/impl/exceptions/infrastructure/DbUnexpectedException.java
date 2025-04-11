package org.visualpaper.example.codetest.umejima.impl.exceptions.infrastructure;

import javax.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.visualpaper.example.codetest.umejima.impl.CodetestLogCode;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestException;

/**
 * DB 操作時の予期せぬエラー.
 */
public class DbUnexpectedException extends CodetestException {

  public DbUnexpectedException() {
    super(CodetestLogCode.UNEXPECTED, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public DbUnexpectedException(@Nullable Throwable cause) {
    super(CodetestLogCode.UNEXPECTED, HttpStatus.INTERNAL_SERVER_ERROR, cause);
  }
}

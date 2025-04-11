package org.visualpaper.example.codetest.umejima.impl.exceptions;

import javax.annotation.Nonnull;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.visualpaper.example.codetest.umejima.impl.CodetestLogCode;

/**
 * 基盤検査例外.
 */
@Getter
public class CodetestException extends Exception {

  private final CodetestLogCode logCode;

  @NonNull
  private final HttpStatus status;

  public CodetestException(
      @Nonnull CodetestLogCode logCode,
      @Nonnull HttpStatus status
  ) {
    this.logCode = logCode;
    this.status = status;
  }

  public CodetestException(
      @Nonnull CodetestLogCode logCode,
      @Nonnull HttpStatus status,
      @Nullable Throwable cause
  ) {
    super(cause);
    this.logCode = logCode;
    this.status = status;
  }
}

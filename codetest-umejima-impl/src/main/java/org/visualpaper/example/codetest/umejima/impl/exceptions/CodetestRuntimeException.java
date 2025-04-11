package org.visualpaper.example.codetest.umejima.impl.exceptions;

import javax.annotation.Nonnull;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.visualpaper.example.codetest.umejima.impl.CodetestLogCode;

/**
 * 基盤非検査例外.
 */
@Getter
public class CodetestRuntimeException extends RuntimeException {

  private final CodetestLogCode logCode;

  @NonNull
  private final HttpStatus status;

  public CodetestRuntimeException(
      @Nonnull CodetestLogCode logCode,
      @Nonnull HttpStatus status
  ) {
    this.logCode = logCode;
    this.status = status;
  }

  public CodetestRuntimeException(
      @Nonnull CodetestLogCode logCode,
      @Nonnull HttpStatus status,
      @Nullable Throwable cause
  ) {
    super(cause);
    this.logCode = logCode;
    this.status = status;
  }
}

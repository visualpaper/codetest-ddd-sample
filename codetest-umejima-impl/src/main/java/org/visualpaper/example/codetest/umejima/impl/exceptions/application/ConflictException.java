package org.visualpaper.example.codetest.umejima.impl.exceptions.application;

import javax.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.visualpaper.example.codetest.umejima.impl.CodetestLogCode;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestRuntimeException;

/**
 * Conflict.
 */
public class ConflictException extends CodetestRuntimeException {

  public ConflictException() {
    super(CodetestLogCode.CONFLICT, HttpStatus.CONFLICT);
  }

  public ConflictException(@Nullable Throwable cause) {
    super(CodetestLogCode.CONFLICT, HttpStatus.CONFLICT, cause);
  }
}

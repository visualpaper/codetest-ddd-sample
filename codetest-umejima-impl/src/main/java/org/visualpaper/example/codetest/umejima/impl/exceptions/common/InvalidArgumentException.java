package org.visualpaper.example.codetest.umejima.impl.exceptions.common;

import javax.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.visualpaper.example.codetest.umejima.impl.CodetestLogCode;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestRuntimeException;

/**
 * 引数が不正.
 */
public class InvalidArgumentException extends CodetestRuntimeException {

  public InvalidArgumentException() {
    super(CodetestLogCode.ILLEGAL_ARGUMENTS, HttpStatus.BAD_REQUEST);
  }

  public InvalidArgumentException(@Nullable Throwable cause) {
    super(CodetestLogCode.ILLEGAL_ARGUMENTS, HttpStatus.BAD_REQUEST, cause);
  }
}

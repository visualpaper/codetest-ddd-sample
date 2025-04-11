package org.visualpaper.example.codetest.umejima.impl.exceptions.application;

import javax.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.visualpaper.example.codetest.umejima.impl.CodetestLogCode;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestRuntimeException;

/**
 * BadRequest.
 */
public class BadRequestException extends CodetestRuntimeException {

  public BadRequestException() {
    super(CodetestLogCode.ILLEGAL_ARGUMENTS, HttpStatus.BAD_REQUEST);
  }

  public BadRequestException(@Nullable Throwable cause) {
    super(CodetestLogCode.ILLEGAL_ARGUMENTS, HttpStatus.BAD_REQUEST, cause);
  }
}

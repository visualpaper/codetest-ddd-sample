package org.visualpaper.example.codetest.umejima.impl.exceptions.application;

import javax.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.visualpaper.example.codetest.umejima.impl.CodetestLogCode;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestRuntimeException;

/**
 * PaymentRequired.
 */
public class PaymentRequiredException extends CodetestRuntimeException {

  public PaymentRequiredException() {
    super(CodetestLogCode.PAYMENT_REQUIRED, HttpStatus.PAYMENT_REQUIRED);
  }

  public PaymentRequiredException(@Nullable Throwable cause) {
    super(CodetestLogCode.PAYMENT_REQUIRED, HttpStatus.PAYMENT_REQUIRED, cause);
  }
}

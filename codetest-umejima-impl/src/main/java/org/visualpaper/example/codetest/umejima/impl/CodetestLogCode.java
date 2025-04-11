package org.visualpaper.example.codetest.umejima.impl;

import javax.annotation.Nonnull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;

@Builder
@Getter
public class CodetestLogCode {

  /**
   * Request Related LogCode.
   */
  private static final String REQUEST_RELATED_CASE_CODE = "RERL";

  public static final CodetestLogCode BAD_REQUEST = CodetestLogCode.builder()
      .caseCode(REQUEST_RELATED_CASE_CODE)
      .detailCode(0)
      .logLevel(LogLevel.INFO)
      .message("Bad Request")
      .build();

  public static final CodetestLogCode PAYMENT_REQUIRED = CodetestLogCode.builder()
      .caseCode(REQUEST_RELATED_CASE_CODE)
      .detailCode(1)
      .logLevel(LogLevel.INFO)
      .message("Payment Required")
      .build();

  public static final CodetestLogCode CONFLICT = CodetestLogCode.builder()
      .caseCode(REQUEST_RELATED_CASE_CODE)
      .detailCode(2)
      .logLevel(LogLevel.INFO)
      .message("Conflict")
      .build();

  /**
   * Common LogCode.
   */
  private static final String COMMON_CASE_CODE = "CMCC";

  public static final CodetestLogCode UNEXPECTED = CodetestLogCode.builder()
      .caseCode(COMMON_CASE_CODE)
      .detailCode(0)
      .logLevel(LogLevel.ERROR)
      .message("Unexpected Error")
      .build();

  public static final CodetestLogCode ILLEGAL_ARGUMENTS = CodetestLogCode.builder()
      .caseCode(COMMON_CASE_CODE)
      .detailCode(1)
      .logLevel(LogLevel.WARN)
      .message("Illegal Argument Error")
      .build();

  /**
   * RDS LogCode.
   */
  private static final String RDS_CASE_CODE = "RDSC";

  public static final CodetestLogCode LOCK_CONFLICT = CodetestLogCode.builder()
      .caseCode(REQUEST_RELATED_CASE_CODE)
      .detailCode(0)
      .logLevel(LogLevel.INFO)
      .message("Lock Conflict")
      .build();

  private String caseCode;
  private int detailCode;
  private LogLevel logLevel;
  private String message;

  CodetestLogCode(
      @Nonnull String caseCode,
      int detailCode,
      @Nonnull LogLevel logLevel,
      @Nonnull String message
  ) {
    this.caseCode = caseCode;
    this.detailCode = detailCode;
    this.logLevel = logLevel;
    this.message = message;
  }

  @Nonnull
  public String getCode() {
    return caseCode + "-" + String.format("%04d", detailCode);
  }
}

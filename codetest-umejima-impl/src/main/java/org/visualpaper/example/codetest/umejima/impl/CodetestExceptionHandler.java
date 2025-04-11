package org.visualpaper.example.codetest.umejima.impl;

import javax.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestException;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestRuntimeException;
import org.visualpaper.example.codetest.umejima.impl.openapi.model.ErrorSchema;

@ControllerAdvice
public class CodetestExceptionHandler {

  private static final String LOG_PATTERN = "{} {}";
  private static final Logger LOGGER = LoggerFactory
      .getLogger(CodetestExceptionHandler.class);

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorSchema> openApiException(
      @Nonnull Throwable cause
  ) {
    CodetestLogCode logCode = CodetestLogCode.ILLEGAL_ARGUMENTS;

    // ロギングする。
    // ※ ここ以外でのロギングも log　ファイルに出力されるので、
    //    この場所での責務は例外が発生した際のロギングと見る。
    logging(logCode, cause);

    // エラーレスポンスを返却する。
    // ※ code と message を body に、HttpStatus をヘッダに置いている。
    // ※ エラーレスポンスとして見たときは取り急ぎ充分と見ている。
    return new ResponseEntity<>(
        new ErrorSchema()
            .code(logCode.getCode())
            .message(logCode.getMessage()),
        HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ErrorSchema> anotherException(
      @Nonnull Throwable cause
  ) {
    CodetestLogCode logCode = resolveLogCode(cause);
    HttpStatus status = resolveStatus(cause);

    // ロギングする。
    // ※ ここ以外でのロギングも log　ファイルに出力されるので、
    //    この場所での責務は例外が発生した際のロギングと見る。
    logging(logCode, cause);

    // エラーレスポンスを返却する。
    // ※ code と message を body に、HttpStatus をヘッダに置いている。
    // ※ エラーレスポンスとして見たときは取り急ぎ充分と見ている。
    return new ResponseEntity<>(
        new ErrorSchema()
            .code(logCode.getCode())
            .message(logCode.getMessage()),
        status
    );
  }

  @Nonnull
  private CodetestLogCode resolveLogCode(@Nonnull Throwable cause) {

    if (cause instanceof CodetestException) {
      return ((CodetestException) cause).getLogCode();
    } else if (cause instanceof CodetestRuntimeException) {
      return ((CodetestRuntimeException) cause).getLogCode();
    } else {
      return CodetestLogCode.UNEXPECTED;
    }
  }

  @Nonnull
  private HttpStatus resolveStatus(@Nonnull Throwable cause) {
    if (cause instanceof CodetestException) {
      return ((CodetestException) cause).getStatus();
    } else if (cause instanceof CodetestRuntimeException) {
      return ((CodetestRuntimeException) cause).getStatus();
    } else {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
  }

  private void logging(
      @Nonnull CodetestLogCode logCode,
      @Nonnull Throwable cause
  ) {

    switch (logCode.getLogLevel()) {
      case ERROR:
        LOGGER.error(LOG_PATTERN, logCode.getCode(), logCode.getMessage(), cause);
        break;
      case WARN:
        LOGGER.warn(LOG_PATTERN, logCode.getCode(), logCode.getMessage(), cause);
        break;
      case INFO:
        LOGGER.info(LOG_PATTERN, logCode.getCode(), logCode.getMessage(), cause);
        break;
      case DEBUG:
        LOGGER.debug(LOG_PATTERN, logCode.getCode(), logCode.getMessage(), cause);
        break;
    }
  }
}

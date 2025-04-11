package org.visualpaper.example.codetest.umejima.impl.domain.transaction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import java.util.stream.Stream;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.visualpaper.example.codetest.umejima.impl.exceptions.common.InvalidArgumentException;

class AmountTest {

  @TestInstance(PER_CLASS)
  @Nested
  class fromTest {

    @Nonnull
    Stream<Fixture> parameterizedTest() {

      /*
       * [正常系]
       * 1. 0 を指定した場合、正常に Amount が生成されること
       * 2. 1 を指定した場合、正常に Amount が生成されること
       * 3. 999 を指定した場合、正常に Amount が生成されること
       * 4. 1000 を指定した場合、正常に Amount が生成されること
       */
      return Stream.of(
          new Fixture(0, 0),
          new Fixture(1, 1),
          new Fixture(999, 999),
          new Fixture(1000, 1000)
      );
    }

    @ParameterizedTest
    @MethodSource
    void parameterizedTest(final Fixture fx) {
      assertThat(Amount.from(fx.actual).getValue()).isEqualTo(fx.expected);
    }

    @Test
    void from_異常系_1000を超えた値を指定した場合() {
      assertThrows(
          InvalidArgumentException.class,
          () -> Amount.from(1001)
      );
    }

    @AllArgsConstructor
    @Data
    class Fixture {

      private final int actual;
      private final int expected;
    }
  }
}

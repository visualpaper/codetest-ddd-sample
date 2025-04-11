package org.visualpaper.example.codetest.umejima.impl.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.visualpaper.example.codetest.umejima.impl.domain.transaction.Amount;
import org.visualpaper.example.codetest.umejima.impl.domain.transaction.Transaction;

class UserTransactionsTest {

  @TestInstance(PER_CLASS)
  @Nested
  class canAddTest {
    private final User USER = new User(UserId.of(1), "sample", "sample");

    @Nonnull
    Stream<Fixture> parameterizedTest() {

      return Stream.of(

          /*
           * 1. ユーザごとの取引情報が存在しない場合、
           *    1000 以下の金額を持つ取引情報を追加できること
           */
          new Fixture(
              createUserTransactions(
                  Lists.newArrayList()
              ),
              createTransaction(Amount.from(1000)),
              true
          ),

          /*
           * 2. ユーザごとの取引情報が 1 件のみ存在する場合、
           *    累計金額が 1000 以下となる金額を持つ取引情報を追加できること
           */
          new Fixture(
              createUserTransactions(
                  Lists.newArrayList(
                      Amount.from(100)
                  )
              ),
              createTransaction(Amount.from(100)),
              true
          ),

          /*
           * 3. ユーザごとの取引情報が 1 件のみ存在する場合、
           *    累計金額が 1000 を超える金額を持つ取引情報を追加できないこと
           */
          new Fixture(
              createUserTransactions(
                  Lists.newArrayList(
                      Amount.from(100)
                  )
              ),
              createTransaction(Amount.from(999)),
              false
          ),


          /*
           * 4. ユーザごとの取引情報が複数件存在する場合、
           *    累計金額が 1000 以下となる金額を持つ取引情報を追加できること
           */
          new Fixture(
              createUserTransactions(
                  Lists.newArrayList(
                      Amount.from(100),
                      Amount.from(100),
                      Amount.from(100),
                      Amount.from(100),
                      Amount.from(100),
                      Amount.from(100),
                      Amount.from(100),
                      Amount.from(100),
                      Amount.from(100),
                      Amount.from(99)
                  )
              ),
              createTransaction(Amount.from(1)),
              true
          ),

          /*
           * 5. ユーザごとの取引情報が複数件存在する場合、
           *    累計金額が 1000 を超える金額を持つ取引情報を追加できないこと
           */
          new Fixture(
              createUserTransactions(
                  Lists.newArrayList(
                      Amount.from(100),
                      Amount.from(100),
                      Amount.from(100),
                      Amount.from(100),
                      Amount.from(100),
                      Amount.from(100),
                      Amount.from(100),
                      Amount.from(100),
                      Amount.from(100),
                      Amount.from(99)
                  )
              ),
              createTransaction(Amount.from(2)),
              false
          )
      );
    }

    @Nonnull
    private UserTransactions createUserTransactions(@Nonnull List<Amount> amounts) {
      return new UserTransactions(
          USER,
          amounts.stream()
              .map(this::createTransaction)
              .collect(Collectors.toList())
      );
    }

    @Nonnull
    private Transaction createTransaction(@Nonnull Amount amount) {
      return Transaction.create(
          USER.getUserId(),
          amount,
          "sample"
      );
    }

    @ParameterizedTest
    @MethodSource
    void parameterizedTest(final Fixture fx) {
      assertThat(fx.userTransactions.canAdd(fx.transaction)).isEqualTo(fx.expected);
    }

    @AllArgsConstructor
    @Data
    class Fixture {

      private final UserTransactions userTransactions;
      private final Transaction transaction;
      private final boolean expected;
    }
  }
}

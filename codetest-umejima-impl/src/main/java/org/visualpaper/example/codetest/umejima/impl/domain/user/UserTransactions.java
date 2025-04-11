package org.visualpaper.example.codetest.umejima.impl.domain.user;

import java.util.List;
import javax.annotation.Nonnull;
import lombok.NonNull;
import lombok.Value;
import org.visualpaper.example.codetest.umejima.impl.domain.transaction.Transaction;

/**
 * ユーザーごとの取引情報.
 */
@Value
public class UserTransactions {

  /**
   * ユーザごとの取引合計金額最大値.
   */
  private static final int TOTAL_AMOUNT_MAXIMUM = 1000;

  @NonNull
  User user;

  /**
   * ユーザごとの取引情報.
   */
  @NonNull
  List<Transaction> transactions;

  public boolean canAdd(@Nonnull Transaction transaction) {
    int totalAmount = totalAmount();
    int amount = transaction.getAmount().getValue();

    // ユーザごとの取引合計金額最大 (1000) を超えて取引情報は追加できない
    if (totalAmount + amount > TOTAL_AMOUNT_MAXIMUM) {
      return false;
    }
    return true;
  }

  public int totalAmount() {
    return transactions.stream()
        .mapToInt(v -> v.getAmount().getValue())
        .sum();
  }
}

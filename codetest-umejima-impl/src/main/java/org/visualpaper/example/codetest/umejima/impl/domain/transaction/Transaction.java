package org.visualpaper.example.codetest.umejima.impl.domain.transaction;

import javax.annotation.Nonnull;
import lombok.NonNull;
import lombok.Value;
import org.visualpaper.example.codetest.umejima.impl.domain.user.UserId;

/**
 * 取引情報
 *
 * <pre>
 *   * 金額と商品説明からなる情報
 * </pre>
 */
@Value
public class Transaction {

  @NonNull
  UserId userId;

  @NonNull
  Amount amount;

  @NonNull
  String description;

  @Nonnull
  public static Transaction create(
      @Nonnull UserId userId,
      @Nonnull Amount amount,
      @Nonnull String description
  ) {
    return new Transaction(
        userId,
        amount,
        description
    );
  }
}

package org.visualpaper.example.codetest.umejima.impl.domain.transaction;

import lombok.NonNull;
import lombok.Value;
import org.visualpaper.example.codetest.umejima.impl.exceptions.common.InvalidArgumentException;

/**
 * 取引金額.
 *
 * <pre>
 *   * マイナス (赤伝みたいなもの) はないことを前提としている
 *     ※ Readme を見ても test コードを見ても見えなかったので、まず上記としている。
 *     ※ OAS レベルで minimum を 0 にもしている。
 *     ☆ 仕様面で確認が必要な点
 *
 *   * 1 つの取引金額は 1000 以下であることを前提としている
 *     ※ 取引金額の累計最大が 1000 以下という制約があるところから逆に考えてこうしている。
 *     ☆ 仕様面で確認が必要な点
 * </pre>
 */
@Value
public class Amount {

  /**
   * 最大値.
   */
  private static final int MAXIMUM = 1000;

  int value;

  private Amount(int value) {
    this.value = value;
  }

  @NonNull
  public static Amount from(int value) throws InvalidArgumentException {

    if (value > MAXIMUM) {
      throw new InvalidArgumentException();
    }
    return new Amount(value);
  }
}

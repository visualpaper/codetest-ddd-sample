package org.visualpaper.example.codetest.umejima.impl.domain.user;

import javax.annotation.Nonnull;
import lombok.Value;

/**
 * ユーザ Id.
 */
@Value
public class UserId {

  int value;

  @Nonnull
  public static UserId of(int value) {
    return new UserId(value);
  }
}

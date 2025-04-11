package org.visualpaper.example.codetest.umejima.impl.domain.user;

import lombok.NonNull;
import lombok.Value;

/**
 * ユーザ.
 */
@Value
public class User {

  @NonNull
  UserId userId;

  @NonNull
  String name;

  @NonNull
  String apiKey;
}

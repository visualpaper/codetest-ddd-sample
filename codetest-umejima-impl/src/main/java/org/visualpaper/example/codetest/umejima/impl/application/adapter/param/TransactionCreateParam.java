package org.visualpaper.example.codetest.umejima.impl.application.adapter.param;

import lombok.NonNull;
import lombok.Value;
import org.visualpaper.example.codetest.umejima.impl.domain.transaction.Amount;
import org.visualpaper.example.codetest.umejima.impl.domain.user.UserId;

@Value
public class TransactionCreateParam {

  @NonNull
  UserId userId;

  @NonNull
  Amount amount;

  @NonNull
  String description;
}

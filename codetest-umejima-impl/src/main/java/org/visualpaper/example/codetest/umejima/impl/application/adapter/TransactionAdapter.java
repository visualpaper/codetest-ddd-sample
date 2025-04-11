package org.visualpaper.example.codetest.umejima.impl.application.adapter;

import javax.annotation.Nonnull;
import org.springframework.stereotype.Component;
import org.visualpaper.example.codetest.umejima.impl.application.adapter.param.TransactionCreateParam;
import org.visualpaper.example.codetest.umejima.impl.domain.transaction.Amount;
import org.visualpaper.example.codetest.umejima.impl.domain.user.UserId;
import org.visualpaper.example.codetest.umejima.impl.exceptions.application.BadRequestException;
import org.visualpaper.example.codetest.umejima.impl.exceptions.common.InvalidArgumentException;

@Component
public class TransactionAdapter {

  @Nonnull
  public TransactionCreateParam from(
      int userIdValue,
      int amountValue,
      @Nonnull String descriptionValue
  ) throws BadRequestException {
    UserId userId = UserId.of(userIdValue);
    Amount amount;

    try {
      amount = Amount.from(amountValue);
    } catch (InvalidArgumentException e) {
      throw new BadRequestException(e);
    }
    return new TransactionCreateParam(
        userId,
        amount,
        descriptionValue
    );
  }
}

package org.visualpaper.example.codetest.umejima.impl.application;

import javax.annotation.Nonnull;
import org.visualpaper.example.codetest.umejima.impl.application.adapter.param.TransactionCreateParam;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestException;

public interface TransactionCreateService {

  void create(
      @Nonnull TransactionCreateParam param
  ) throws CodetestException;
}

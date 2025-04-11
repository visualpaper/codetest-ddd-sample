package org.visualpaper.example.codetest.umejima.impl.infrastructure.db;

import javax.annotation.Nonnull;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestException;

public interface StampedLock<T> {

  @Nonnull
  T tryLock() throws CodetestException;
}

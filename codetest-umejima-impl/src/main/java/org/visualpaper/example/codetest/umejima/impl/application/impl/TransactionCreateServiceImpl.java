package org.visualpaper.example.codetest.umejima.impl.application.impl;

import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.visualpaper.example.codetest.umejima.impl.application.TransactionCreateService;
import org.visualpaper.example.codetest.umejima.impl.application.adapter.param.TransactionCreateParam;
import org.visualpaper.example.codetest.umejima.impl.domain.transaction.Transaction;
import org.visualpaper.example.codetest.umejima.impl.domain.transaction.TransactionRepository;
import org.visualpaper.example.codetest.umejima.impl.domain.user.User;
import org.visualpaper.example.codetest.umejima.impl.domain.user.UserRepository;
import org.visualpaper.example.codetest.umejima.impl.domain.user.UserTransactions;
import org.visualpaper.example.codetest.umejima.impl.domain.user.UserTransactionsQueryService;
import org.visualpaper.example.codetest.umejima.impl.exceptions.CodetestException;
import org.visualpaper.example.codetest.umejima.impl.exceptions.application.PaymentRequiredException;
import org.visualpaper.example.codetest.umejima.impl.infrastructure.db.StampedLock;
import org.visualpaper.example.codetest.umejima.impl.infrastructure.repository.user.UserStampedLock;

@Service
public class TransactionCreateServiceImpl implements TransactionCreateService {

  private final UserRepository userRepository;
  private final TransactionRepository transactionRepository;
  private final UserTransactionsQueryService queryService;

  @Autowired
  public TransactionCreateServiceImpl(
      @Nonnull UserRepository userRepository,
      @Nonnull TransactionRepository transactionRepository,
      @Nonnull UserTransactionsQueryService queryService
  ) {
    this.userRepository = userRepository;
    this.transactionRepository = transactionRepository;
    this.queryService = queryService;
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @Override
  public void create(@Nonnull TransactionCreateParam param) throws CodetestException {
    StampedLock<User> stampedLock = UserStampedLock.create(userRepository, param.getUserId());

    // 対象ユーザをロックする。
    User user = stampedLock.tryLock();

    // 対象ユーザの取引情報を取得する。
    UserTransactions userTransactions = queryService.query(user);

    // 対象ユーザの登録可能な取引の金額累計上限　(1000) を超過しているか確認する。
    // もし、超過していた場合は特定のエラーを返す。
    Transaction transaction = Transaction.create(
        user.getUserId(),
        param.getAmount(),
        param.getDescription()
    );
    if (!userTransactions.canAdd(transaction)) {
      throw new PaymentRequiredException();
    }

    // 対象ユーザの取引情報を追加する
    transactionRepository.add(transaction);
  }
}

package org.visualpaper.example.codetest.umejima.impl.resources;

import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.visualpaper.example.codetest.umejima.impl.application.TransactionCreateService;
import org.visualpaper.example.codetest.umejima.impl.application.adapter.TransactionAdapter;
import org.visualpaper.example.codetest.umejima.impl.openapi.api.TransactionsApi;
import org.visualpaper.example.codetest.umejima.impl.openapi.model.TransactionRequest;

@RestController
public class TransactionResource implements TransactionsApi {

  @Autowired
  private TransactionCreateService createService;

  @Autowired
  private TransactionAdapter adapter;

  @Nonnull
  @Override
  public ResponseEntity<Void> transactions(
      @Nonnull String apikey,
      @Nonnull TransactionRequest request
  ) throws Exception {
    createService.create(
        adapter.from(request.getUserId(), request.getAmount(), request.getDescription())
    );

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}

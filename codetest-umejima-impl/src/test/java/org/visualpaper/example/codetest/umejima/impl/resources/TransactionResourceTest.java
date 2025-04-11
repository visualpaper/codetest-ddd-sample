package org.visualpaper.example.codetest.umejima.impl.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.visualpaper.example.codetest.umejima.impl.openapi.model.TransactionRequest;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class TransactionResourceTest {

  @Autowired
  private MockMvc mockMvc;

  /**
   * [case 1]
   * * あるユーザの取引情報を登録する
   * * 取引累計金額が 1000 超過しない登録を行う
   */
  @Test
  void transactions_case1() throws Exception {
    TransactionRequest requestSchema = new TransactionRequest();
    requestSchema.setUserId(1);
    requestSchema.setAmount(100);
    requestSchema.setDescription("sample");

    MockHttpServletResponse response = mockMvc.perform(
        MockMvcRequestBuilders.post("/transactions")
            .content(new ObjectMapper().writeValueAsString(requestSchema))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("apikey", "secure-api-key-1"))
        .andReturn()
        .getResponse();

    Assertions.assertThat(response.getStatus()).isEqualTo(201);
  }

  /**
   * [case 2]
   * * あるユーザの取引情報を、複数回登録する
   * * 取引累計金額が 1000 超過しない登録を行う
   */
  @Test
  void transactions_case2() throws Exception {
    TransactionRequest requestSchema = new TransactionRequest();
    requestSchema.setUserId(2);
    requestSchema.setAmount(100);
    requestSchema.setDescription("sample");

    // 1 回目の登録
    MockHttpServletResponse firstResponse = mockMvc.perform(
        MockMvcRequestBuilders.post("/transactions")
            .content(new ObjectMapper().writeValueAsString(requestSchema))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("apikey", "secure-api-key-2"))
        .andReturn()
        .getResponse();

    Assertions.assertThat(firstResponse.getStatus()).isEqualTo(201);

    // 2 回目の登録
    MockHttpServletResponse secondResponse = mockMvc.perform(
        MockMvcRequestBuilders.post("/transactions")
            .content(new ObjectMapper().writeValueAsString(requestSchema))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("apikey", "secure-api-key-2"))
        .andReturn()
        .getResponse();

    Assertions.assertThat(secondResponse.getStatus()).isEqualTo(201);
  }

  /**
   * [case 3]
   * * あるユーザの取引情報を、複数回登録する
   * * 取引累計金額が 1000 を超過する登録を行う
   */
  @Test
  void transactions_case3() throws Exception {
    TransactionRequest requestSchema = new TransactionRequest();
    requestSchema.setUserId(3);
    requestSchema.setAmount(600);
    requestSchema.setDescription("sample");

    // 1 回目の登録
    MockHttpServletResponse firstResponse = mockMvc.perform(
        MockMvcRequestBuilders.post("/transactions")
            .content(new ObjectMapper().writeValueAsString(requestSchema))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("apikey", "secure-api-key-3"))
        .andReturn()
        .getResponse();

    Assertions.assertThat(firstResponse.getStatus()).isEqualTo(201);

    // 2 回目の登録
    MockHttpServletResponse secondResponse = mockMvc.perform(
        MockMvcRequestBuilders.post("/transactions")
            .content(new ObjectMapper().writeValueAsString(requestSchema))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("apikey", "secure-api-key-3"))
        .andReturn()
        .getResponse();

    Assertions.assertThat(secondResponse.getStatus()).isEqualTo(402);
  }

  /**
   * [case 4]
   * * 存在しないユーザを指定し登録を行う
   */
  @Test
  void transactions_case4() throws Exception {
    TransactionRequest requestSchema = new TransactionRequest();
    requestSchema.setUserId(99999999);
    requestSchema.setAmount(100);
    requestSchema.setDescription("sample");

    MockHttpServletResponse response = mockMvc.perform(
        MockMvcRequestBuilders.post("/transactions")
            .content(new ObjectMapper().writeValueAsString(requestSchema))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("apikey", "secure-api-key-99999999"))
        .andReturn()
        .getResponse();

    Assertions.assertThat(response.getStatus()).isEqualTo(409);
  }
}

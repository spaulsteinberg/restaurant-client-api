package com.java.firebaseclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.java.firebaseclient.controllers.OrderController;
import com.java.firebaseclient.services.OrderService;
import com.models.*;
import com.models.BeverageItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class AppRunnerTests {

	@Autowired
	private OrderController orderController;
	@Autowired
	private WebApplicationContext context;

	@MockBean
	private OrderService mockOrderService;

	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void setUp() throws InterruptedException, ExecutionException {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		Mockito.when(mockOrderService.sendOrder(Mockito.any())).thenReturn(new PostOrderResponse(201, "Created", null));
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}

	@Test
	void contextLoads() {
		assertThat(orderController).isNotNull();
	}

	@Test
	void postOrderReturns201Created() throws Exception {
		String jsonRequest = mapper.writeValueAsString(getValidOrderRequest());
		MvcResult result = mockMvc
				.perform(post("/api/v1/client/send/order")
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		PostOrderResponse response = mapper.readValue(resultContent, PostOrderResponse.class);
		assertThat(response.status).isEqualTo(201);
	}

	@Test
	void postOrderReturns400BadRequest() throws Exception {
		String jsonRequest = mapper.writeValueAsString(getInvalidOrder());
		MvcResult result = mockMvc
				.perform(post("/api/v1/client/send/order")
						.content(jsonRequest)
						.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		OrderErrorResponse response = mapper.readValue(resultContent, OrderErrorResponse.class);
		assertThat(response.status).isEqualTo(400);
		assertThat(response.message).isEqualTo("Bad Request");
	}

	@Test
	void healthCheckReturns200ok() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/v1/client/health").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andReturn();
		var response = result.getResponse();
		assertThat(response.getContentAsString()).isEqualTo("Application is running!");
		assertThat(response.getStatus()).isEqualTo(200);
	}

	private OrderRequest getValidOrderRequest(){
		OrderRequest orReq = new OrderRequest();
		orReq.credit = "123456789";
		orReq.firstName = "John";
		orReq.lastName = "Doe";
		orReq.email = "johndoe@gmail.com";
		orReq.totalCost = 32.23;
		orReq.order = getValidOrder();
		return orReq;
	}

	private Order getValidOrder(){
		Order or = new Order();
		BeverageItem bItem = new BeverageItem("Soda", "Sprite", 1.98);
		FoodItem fItem = new FoodItem("Meat", "Steak", 20.34);
		fItem.additions = getAdditions();
		fItem.subtractions = new ArrayList<>(Arrays.asList("Ketchup"));
		or.drink = new ArrayList<>(Arrays.asList(bItem));
		or.food = new ArrayList<>(Arrays.asList(fItem));
		return or;
	}

	private List<FoodAddition> getAdditions(){
		return new ArrayList<>(Arrays.asList(new FoodAddition("Onions", 1)));
	}

	private OrderRequest getInvalidOrder(){
		OrderRequest or = new OrderRequest();
		or.firstName = "Sam";
		or.totalCost = 42.3;
		or.order = getValidOrder();
		or.credit = "123456789";
		// no last name
		return or;
	}
}

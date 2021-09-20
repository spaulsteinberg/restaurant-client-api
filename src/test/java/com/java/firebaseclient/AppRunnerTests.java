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
	}

	@Test
	void contextLoads() {
		assertThat(orderController).isNotNull();
	}

	@Test
	void postOrderTest() throws Exception {
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
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
}

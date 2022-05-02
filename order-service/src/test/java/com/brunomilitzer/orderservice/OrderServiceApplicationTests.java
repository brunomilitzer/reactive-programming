package com.brunomilitzer.orderservice;

import com.brunomilitzer.orderservice.client.ProductClient;
import com.brunomilitzer.orderservice.client.UserClient;
import com.brunomilitzer.orderservice.dto.ProductDTO;
import com.brunomilitzer.orderservice.dto.PurchaseOrderRequestDTO;
import com.brunomilitzer.orderservice.dto.PurchaseOrderResponseDTO;
import com.brunomilitzer.orderservice.dto.UserDTO;
import com.brunomilitzer.orderservice.service.OrderFulfillmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class OrderServiceApplicationTests {

	private final UserClient userClient;

	private final ProductClient productClient;

	private final OrderFulfillmentService orderFulfillmentService;

	@Autowired
	public OrderServiceApplicationTests(
			UserClient userClient, ProductClient productClient, OrderFulfillmentService orderFulfillmentService) {
		this.userClient = userClient;
		this.productClient = productClient;
		this.orderFulfillmentService = orderFulfillmentService;
	}

	@Test
	void contextLoads() {
		Flux<PurchaseOrderResponseDTO> dtoFlux = Flux.zip(userClient.getAllUsers(), productClient.getAllProducts())
				.map(t -> buildDto(t.getT1(), t.getT2()))
				.flatMap(dto -> this.orderFulfillmentService.processOrder(Mono.just(dto)))
				.doOnNext(System.out::println);

		StepVerifier.create(dtoFlux).expectNextCount(4).verifyComplete();
	}

	private PurchaseOrderRequestDTO buildDto(UserDTO userDTO, ProductDTO productDTO) {
		return PurchaseOrderRequestDTO.builder().userId(userDTO.getId()).productId(productDTO.getId()).build();
	}

}

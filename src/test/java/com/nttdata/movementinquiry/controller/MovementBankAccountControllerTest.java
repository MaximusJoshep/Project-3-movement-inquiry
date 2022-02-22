package com.nttdata.movementinquiry.controller;

import java.sql.Date;

import com.nttdata.movementinquiry.entity.MovementBankAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
@WebFluxTest(MovementBankAccountController.class)
public class MovementBankAccountControllerTest {
    @Autowired
	private  WebTestClient webClient;
	
	
	@MockBean
	MovementBankAccountController service;

    MovementBankAccount movement;

  
    @BeforeEach
	void setUp() throws Exception {
		movement=new  MovementBankAccount("1","retiro",(float)20000,new Date(2022-02-16),true,"1");
		
	}
    @Test
    void testDeleteMovements() {
        Mockito.when(service.deleteMovements(movement.getId()))
        .thenReturn(Mono.just(movement));
        webClient.put().uri("/movements/delete/1")
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(movement), MovementBankAccount.class)
				.exchange()
				.expectStatus().isEqualTo(200);


    }

    @Test
    void testFindMovementsByAccount() {
        Flux<MovementBankAccount> movementFlux = Flux.just(movement);
	    Mockito.when(service.findMovementsByAccount(movement.getIdAccount()))
        .thenReturn(movementFlux);
	    Flux<MovementBankAccount> flux = webClient.get().uri("/movements/1")
	    		.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.returnResult(MovementBankAccount.class)
				.getResponseBody();
	   
	    StepVerifier.create(flux)
			.expectSubscription()
			.expectNext(movement)
			.verifyComplete();

    }

    @Test
    void testGetMovementsBankAccount() {
        Flux<MovementBankAccount> movementFlux = Flux.just(movement);
	    Mockito.when(service.getMovementsBankAccount())
        .thenReturn(movementFlux);
	    webClient.get().uri("/movements/bank-account")
	    		.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.returnResult(MovementBankAccount.class)
				.getResponseBody();
	   
	    

    }

    @Test
    void testSaveMovementBankAccount() {
        Mockito.when(service.saveMovementBankAccount(movement))
        .thenReturn(Mono.just(movement));
        
      webClient.post().uri("/movements/bank-account")
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(movement), MovementBankAccount.class)
            .exchange()
            .expectStatus().isOk();

    }

    @Test
    void testUpdateMovements() {
        Mockito.when(service.updateMovements(movement))
        .thenReturn(Mono.just(movement));

      webClient.put().uri("/movements/update")
              .accept(MediaType.APPLICATION_JSON)
              .body(Mono.just(movement), MovementBankAccount.class)
              .exchange()
              .expectStatus().isEqualTo(200);
    

    }
}

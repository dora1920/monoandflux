package com.acoder.rest;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acoder.pojo.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class CustomerRest {

	@GetMapping(value="/get")
	//when a project giving only one response to user for every http request
	public ResponseEntity<Mono<Customer>> getCutsomer()
	{
		// creating object 
		 Customer customer = new Customer("anjaneyulu",new Date(System.currentTimeMillis()));
		 
		 //creating mono with specified user data object
		 Mono<Customer>  mono= Mono.just(customer);
		 
		 //sending response to back
		 return ResponseEntity.ok(mono);
	}
	
	@GetMapping(value="/gets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<Flux<Customer>> getflux()
    {
		//create customer object
		 Customer customer = new Customer("anjaneyulu",new Date(System.currentTimeMillis()));
		 
		 
		 //Create Stream object to send data 
		 Stream<Customer> customerStream= Stream.generate(() -> customer);
		 
		 //Giving stream object to flux object
		 Flux<Customer> ceflux= Flux.fromStream(customerStream);
		 
		 //Setting Response interval
		 Flux<Long> interval= Flux.interval(Duration.ofSeconds(1));
		 
		 //Combining interflux and customer
		 Flux<Tuple2<Long,Customer>> zip= Flux.zip(interval, ceflux);
		 
		 //getting Second tuple value as flux obj
		 Flux<Customer> fluxMap=zip.map(Tuple2::getT2);
	 
		return ResponseEntity.ok(fluxMap);
	}
}

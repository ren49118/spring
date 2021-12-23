package com.mmit.jpit.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckoutRestController {
	@PostMapping("/api/checkout")
	public ResponseEntity<Message> makeOrder(@RequestBody RequestOrderDto order){
		try {
			System.out.print("object"+order);
			return new ResponseEntity<Message>(new Message("success", null), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Message>(new Message("fail", null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

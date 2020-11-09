package com.vanna.ssodemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://locahost:4200", allowCredentials = "true")
public class SampleController {

	@GetMapping("get")
	public ResponseEntity<?> anonymousGet() {
		return new ResponseEntity<>("This is an open endpoint.", HttpStatus.OK);
	}
	
	@GetMapping("secure/get")
	public ResponseEntity<?> authorizedGet() {
		return new ResponseEntity<>("This is a secure endpoint.", HttpStatus.OK);
	}
}

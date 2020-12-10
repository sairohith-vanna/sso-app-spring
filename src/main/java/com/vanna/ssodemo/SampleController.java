package com.vanna.ssodemo;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	private final CSVService csvService;
	
	@Autowired
	public SampleController(CSVService csvService) {
		this.csvService = csvService;
	}

	@GetMapping("get")
	public ResponseEntity<?> anonymousGet() {
		return new ResponseEntity<>("This is an open endpoint.", HttpStatus.OK);
	}
	
	@GetMapping("secure/get")
	public ResponseEntity<?> authorizedGet() {
		return new ResponseEntity<>("This is a secure endpoint.", HttpStatus.OK);
	}
	
	@GetMapping("secure/download")
	public ResponseEntity<?> getCsvFile() throws IOException {
		return new ResponseEntity<>(csvService.writeSampleCSV(), HttpStatus.OK);
	}
}

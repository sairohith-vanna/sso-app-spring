package com.vanna.ssodemo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SampleController {
	
	private final CSVService csvService;
	private final KeycloakUtilityService keycloakService;
	
	@Autowired
	public SampleController(CSVService csvService, KeycloakUtilityService keycloakService) {
		this.csvService = csvService;
		this.keycloakService = keycloakService;
		
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
	
	@GetMapping(".well-known/openid-configuration")
	public ResponseEntity<?> getOpenIdConfiguration() {
		return new ResponseEntity<>(keycloakService.getWellKnownConfiguration().toString(), HttpStatus.OK);
	}
	
	@PostMapping("openid-keycloak/token")
	public ResponseEntity<?> getToken(@RequestParam MultiValueMap<String,String> paramMap) {
		return keycloakService.getTokenFromKeycloak(paramMap);
	}
}

package com.vanna.ssodemo;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KeycloakUtilityService {

	@Value("${keycloak.realm}")
	private String realm;

	@Value("${keycloak.auth-server-url}")
	private String providerURL;
	
	@Value("${keycloak.credentials.secret}")
	private String clientSecret;

	public JSONObject getWellKnownConfiguration() {
		RestTemplate restTemplate = new RestTemplate();
		String wellKnownEndpoint = this.providerURL + "/realms/" + this.realm + "/.well-known/openid-configuration";
		ResponseEntity<String> response = restTemplate.exchange(wellKnownEndpoint, HttpMethod.GET, null, String.class);
		JSONObject wellKnowConfigurationJSON = new JSONObject(response.getBody());
		wellKnowConfigurationJSON.put("token_endpoint", "http://localhost:8081/openid-keycloak/token");
		return wellKnowConfigurationJSON;
	}
	
	public ResponseEntity<String> getTokenFromKeycloak(MultiValueMap<String, String> authenticationRequestMap) {
		String tokenEndpoint = this.providerURL + "/realms/" + this.realm + "/protocol/openid-connect/token";
		RestTemplate restTemplate = new RestTemplate();
		authenticationRequestMap.add("client_secret", clientSecret);
		HttpEntity<MultiValueMap<String, String>> formDataEntity = new HttpEntity<>(authenticationRequestMap, new HttpHeaders());
		ResponseEntity<String> token = restTemplate.exchange(tokenEndpoint, HttpMethod.POST, formDataEntity, String.class);
		return token;
	}

}

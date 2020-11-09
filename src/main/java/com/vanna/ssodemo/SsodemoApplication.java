package com.vanna.ssodemo;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SsodemoApplication {
	
	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		SpringApplication.run(SsodemoApplication.class, args);
	}

}

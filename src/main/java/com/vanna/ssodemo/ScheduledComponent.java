package com.vanna.ssodemo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "scheduling", name = "enabled", havingValue = "true")
public class ScheduledComponent {
	
	@Bean
	@Scheduled(fixedDelay = 2000)
	public void printSomething() {
		System.out.println("Hello");
	}

}

package com.peerless.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AssessmentApplication {


//	@Bean
//	public AuditorAware<Integer> auditorAware() {
//		return new ApplicationAuditAware();
//	}
	public static void main(String[] args) {
		SpringApplication.run(AssessmentApplication.class, args);
	}

}

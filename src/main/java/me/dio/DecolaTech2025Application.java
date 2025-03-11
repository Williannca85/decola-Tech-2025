package me.dio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DecolaTech2025Application {
	private static final Logger logger = LoggerFactory.getLogger(DecolaTech2025Application.class);

	public static void main(String[] args) {
		System.setProperty("PGDATABASE", "railway");
		System.setProperty("PGHOST", "postgres.railway.internal");
		System.setProperty("PGPASSWORD", "UfjnImpAczxYdxnhDjlStxauWqcYFiSb");
		System.setProperty("PGPORT", "5432");
		System.setProperty("PGUSER", "postgres");
		System.setProperty("SPRING_PROFILES_ACTIVE", "prod");

		logger.info("PGDATABASE: {}", System.getProperty("PGDATABASE"));
		logger.info("PGHOST: {}", System.getProperty("PGHOST"));
		logger.info("PGPASSWORD: {}", System.getProperty("PGPASSWORD"));
		logger.info("PGPORT: {}", System.getProperty("PGPORT"));
		logger.info("PGUSER: {}", System.getProperty("PGUSER"));
		logger.info("SPRING_PROFILES_ACTIVE: {}", System.getProperty("SPRING_PROFILES_ACTIVE"));

		SpringApplication.run(DecolaTech2025Application.class, args);
	}
}
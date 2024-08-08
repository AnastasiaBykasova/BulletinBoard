package com.example.BulletinBoard;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BulletinBoardApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(BulletinBoardApplicationTests.class);

	@Test
	void contextLoads() {
		logger.info("Тест начался");
		// Ваш код теста здесь
		logger.info("Тест закончился");
	}

}

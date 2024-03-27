package com.banasiak.weatherApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WeatherAppApplicationTests {

	@Test
	void contextLoads() {


		long unixTime = System.currentTimeMillis() / 1000L;


		Assertions.assertEquals(unixTime, 100);




	}

}

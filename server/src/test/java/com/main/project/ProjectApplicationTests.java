package com.main.project;

import com.main.project.restaurant.service.RestaurantServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class ProjectApplicationTests {

	@Autowired
	private RestaurantServiceImpl restaurantServiceImpl;

	@Test
	public void searchTest() {
		var result = restaurantServiceImpl.searchApi("갈비집");

		System.out.println(result);

		Assertions.assertNotNull(result);
	}

}

package com.main.project;

import com.main.project.naver.NaverClient;
import com.main.project.naver.SearchLocalReq;
import com.main.project.restaurant.service.RestaurantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class ProjectApplicationTests {

	@Autowired
	private RestaurantService restaurantService;

	@Test
	public void searchTest() {
		var result = restaurantService.search("갈비집");

		System.out.println(result);

		Assertions.assertNotNull(result);
	}

}

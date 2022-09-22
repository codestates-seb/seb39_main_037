package com.main.project;

import com.main.project.location.City;
import com.main.project.location.LocationRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

@SpringBootApplication
@EnableJpaAuditing
public class ProjectApplication {

	@Bean//@Bean 애너테이션으로 해당 메서드의 리턴값을 IoC로 등록해준다.
	public BCryptPasswordEncoder encodePwd(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}

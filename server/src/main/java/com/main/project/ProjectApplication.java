package com.main.project;

import com.main.project.location.City;
import com.main.project.location.LocationRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.IOException;

@SpringBootApplication
@EnableJpaAuditing
public class ProjectApplication {
	public static void main(String[] args) {SpringApplication.run(ProjectApplication.class, args);	}
}



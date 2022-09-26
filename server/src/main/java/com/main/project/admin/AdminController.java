package com.main.project.admin;

import com.main.project.user.entity.WebUser;
import com.main.project.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    UserService userService;


   @GetMapping("/userCount")
   public ResponseEntity getUserGrowth(@RequestBody LocalDate startDate, @RequestBody LocalDate endDate ){// "2022-01-01"

     List<WebUser> usersByDate =  userService.findUserBydate(startDate, endDate);

        return new ResponseEntity(usersByDate, HttpStatus.FOUND);
   }




}

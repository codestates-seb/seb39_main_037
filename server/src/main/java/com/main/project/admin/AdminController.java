package com.main.project.admin;

import com.main.project.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/admin")
public class AdminController {

    UserService userService;


//   @GetMapping("/userCount")
//   public String getUserGrowth(@RequestBody int page, @RequestBody LocalDate userCreatedDate){// "2022-01-01"
//
//       userService.findAllUser()
//
//
//   }




}

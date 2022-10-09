package com.main.project.mail;


import com.main.project.mail.dto.MailDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {

    MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send")
    public ResponseEntity postMail(@RequestBody MailDto.PostMailDto postMailDto){
        System.out.println("이메일 전송");
        return new ResponseEntity(mailService.mailSend(postMailDto), HttpStatus.ACCEPTED);


    }





}

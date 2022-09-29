package com.main.project.mail;


import com.main.project.mail.dto.MailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {

    MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping
    public String postMail(@RequestBody MailDto.PostMailDto postMailDto){

        mailService.mailSend(postMailDto);

        return "메일이 전송되었습니다.";
    }
}

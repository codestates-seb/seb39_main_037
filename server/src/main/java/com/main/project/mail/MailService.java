package com.main.project.mail;


import com.main.project.mail.dto.MailDto;
import org.springframework.stereotype.Service;


public interface MailService {


    public String mailSend(MailDto.PostMailDto postMailDto);
}

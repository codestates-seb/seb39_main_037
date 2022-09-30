package com.main.project.mail;


import com.main.project.mail.dto.MailDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MailServiceImpl implements MailService{

    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String FROM_ADDRESS;

    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void mailSend(MailDto.PostMailDto postMailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(postMailDto.getAddress());//메일 수신자 설정
        message.setFrom(FROM_ADDRESS);//메일 발신자 설정
        message.setSubject("foodreco 회원 가입을 위한 인증번호 안내입니다.");
        message.setText("foodreco를 가입해 주셔서 감사합니다. 인증 번호는 "+ randomNumberMaker()+ " 입니다");

        mailSender.send(message);
    }




    public String randomNumberMaker(){
        int[] authenticationNumber = new int[6];
        for(int i=0; i<6; i++) {
            authenticationNumber[i] = (int) (Math.random()*10);
        }

        return Arrays.toString(authenticationNumber);//int 배열을 String으로 변환
    }



}

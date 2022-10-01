package com.main.project.mail;


import com.main.project.mail.dto.MailDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService{

    private JavaMailSender mailSender;
    int randomNumber;
    @Value("${spring.mail.username}")
    private String FROM_ADDRESS;

    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public String mailSend(MailDto.PostMailDto postMailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(postMailDto.getAddress());//메일 수신자 설정
        message.setFrom(FROM_ADDRESS);//메일 발신자 설정
        message.setSubject("foodreco 회원 가입을 위한 인증번호 안내입니다.");
        message.setText("foodreco를 가입해 주셔서 감사합니다."+" \"<br><br>\" +  인증 번호는 [ "+ randomNumberMaker()+ " ] 입니다");

        mailSender.send(message);
        return Integer.toString(randomNumber);
    }




    public int randomNumberMaker(){
        Random r = new Random();
        randomNumber = r.nextInt(888888) + 111111;

//        int[] authenticationNumber = new int[6];
//        for(int i=0; i<6; i++) {
//            authenticationNumber[i] = (int) (Math.random()*10);
//        }
//        Arrays.toString(authenticationNumber);
        return randomNumber;
    }



}

package com.main.project.qna;

import com.main.project.user.WebUser;

import javax.persistence.*;

@Entity
public class QnA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long qnAId;
    String questionTitle;
    String questionBody;

    @ManyToOne
    @JoinColumn(name="user_Id")
    WebUser qnaUser;
}

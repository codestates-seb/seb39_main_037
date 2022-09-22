package com.main.project.qna;

import com.main.project.user.entity.WebUser;

import javax.persistence.*;

@Entity
@Table(name = "qna")
public class QnA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long qnaId;

    @Column
    String questionTitle;

    @Column
    String questionBody;

    @ManyToOne
    @JoinColumn(name="user_Id")
    WebUser qnaUser;
}

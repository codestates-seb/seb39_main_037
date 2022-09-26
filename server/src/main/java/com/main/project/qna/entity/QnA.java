package com.main.project.qna.entity;

import com.main.project.entity.BaseTimeEntity;
import com.main.project.user.entity.WebUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "qna")
public class QnA extends BaseTimeEntity {

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

    public void addWebUser(WebUser qnaUser){
        this.qnaUser = qnaUser;
    }
}

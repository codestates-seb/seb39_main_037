package com.main.project.qna.service;

import com.main.project.qna.entity.QnA;
import org.springframework.data.domain.Page;

public interface QnaService {
    public QnA createQna(long userId, QnA qna);
    public QnA updateQna(long qnaId, QnA qna);
    public QnA findQna(long qnaId);
    public Page<QnA> findAllQna(int page, int size);
    public void deleteQna(long qnaId);
}

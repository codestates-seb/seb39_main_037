package com.main.project.qna.service;

import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.qna.entity.QnA;
import com.main.project.qna.repository.QnaRepository;
import com.main.project.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QnaServiceImpl implements QnaService{
    private final QnaRepository qnaRepository;
    UserService userService;

    public QnaServiceImpl(QnaRepository qnaRepository, UserService userService) {
        this.qnaRepository = qnaRepository;
        this.userService = userService;
    }

    public QnA createQna(long userId, QnA qna) {
        qna.addWebUser(userService.findUser(userId));
        verifyUser(qna);
        qna.setQuestionTitle(qna.getQuestionTitle());
        qna.setQuestionBody(qna.getQuestionBody());
        qna.setQnaUser(qna.getQnaUser());

        return qnaRepository.save(qna);
    }
    public QnA updateQna(long qnaId, QnA qna) {
        QnA foundQna = findQna(qnaId);
        if (qnaId == foundQna.getQnaUser().getUserId()) {
            Optional.ofNullable(qna.getQuestionTitle())
                    .ifPresent(foundQna::setQuestionTitle);
            Optional.ofNullable(qna.getQuestionBody())
                    .ifPresent(foundQna::setQuestionBody);

            return qnaRepository.save(foundQna);
        } else {
            new BusinessLogicException(ExceptionCode.WRITER_IS_NOT_MATCH);
        }
        return null;
    }
    public QnA findQna(long qnaId) {
        Optional<QnA> qna = qnaRepository.findById(qnaId);
        QnA findQnA = qna.orElseThrow(() -> new BusinessLogicException(ExceptionCode.REVIEW_NOT_FOUND));
        return findQnA;
    }
    public Page<QnA> findAllQna(int page, int size) {

        return qnaRepository.findAll(PageRequest.of(page, size, Sort.by("qnaId").descending()));
    }
    public void deleteQna(long qnaId) {

                QnA qna = findQna(qnaId);
                qnaRepository.delete(qna);
    }
    private void verifyUser(QnA qna) {
        userService.findUser(qna.getQnaUser().getUserId());
    }
}

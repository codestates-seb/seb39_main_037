package com.main.project.qna.controller;

import com.main.project.qna.dto.QnaDto;
import com.main.project.qna.entity.QnA;
import com.main.project.qna.mapper.QnaMapper;
import com.main.project.qna.service.QnaServiceImpl;
import com.main.project.entity.Multi_ResponseDTOwithPageInfo;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/qna")
@Validated
@CrossOrigin("*")
public class QnaController {

    private final QnaServiceImpl qnaService;
    private final QnaMapper qnaMapper;

    public QnaController(QnaServiceImpl qnaService, QnaMapper qnaMapper) {
        this.qnaService = qnaService;
        this.qnaMapper = qnaMapper;
    }

    @PostMapping("/post")
    public ResponseEntity postQna (@Valid @RequestBody QnaDto.PostDto postDto) {

        long userId = postDto.getUserId();
        QnA qna = qnaService.createQna(userId, qnaMapper.qnaPostDtoToQna(postDto));

        return new ResponseEntity<>(qnaMapper.qnaToQnaResponseDto(qna),
                HttpStatus.CREATED);
    }

    @PatchMapping("/edit")
    public ResponseEntity patchQna (@Valid @RequestBody QnaDto.PatchDto patchDto) {

        QnA editQna = qnaMapper.qnaPatchDtoToQna(patchDto);
        QnA qna = qnaService.updateQna(patchDto.getQnaId(), patchDto.getUserId(), editQna);

        return new ResponseEntity<>(qnaMapper.qnaToQnaResponseDto(qna),
                HttpStatus.OK);
    }

    @GetMapping("/{qna-id}")
    public ResponseEntity getQna (@PathVariable("qna-id") long qnaId) {

        QnA qna = qnaService.findQna(qnaId);

        return new ResponseEntity<>(qnaMapper.qnaToQnaResponseDto(qna),
                HttpStatus.OK);
    }

    @GetMapping("/all/{page}")
    public ResponseEntity getAllQna (@PathVariable("page") int page) {

        int size =10;
        Page<QnA> pageQna = qnaService.findAllQna(page-1, size);
        List<QnA> qnastions = pageQna.getContent();

        return new ResponseEntity<>(new Multi_ResponseDTOwithPageInfo<>(qnaMapper.qnasToQnaResponseDtos(qnastions),pageQna),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{qna-id}")
    public ResponseEntity deleteQan (@PathVariable("qna-id") long qnaId) {

        qnaService.deleteQna(qnaId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

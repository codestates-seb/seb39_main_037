package com.main.project.qna.mapper;

import com.main.project.qna.dto.QnaDto;
import com.main.project.qna.entity.QnA;
import com.main.project.review.dto.ReviewPatchDto;
import com.main.project.review.dto.ReviewPostDto;
import com.main.project.review.dto.ReviewResponseDto;
import com.main.project.review.entity.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QnaMapper {
    QnA qnaPostDtoToQna(QnaDto.PostDto postDto);
    QnA qnaPatchDtoToQna(QnaDto.PatchDto patchDto);
    List<QnaDto.ResponseDto> qnasToQnaResponseDtos(List<QnA> qnas);

    default public QnaDto.ResponseDto qnaToQnaResponseDto(QnA qna) {

        QnaDto.ResponseDto responseDto = new QnaDto.ResponseDto();

        responseDto.setQnaId(qna.getQnaId());
        responseDto.setUserId(qna.getQnaUser().getUserId());
        responseDto.setQuestionTitle(qna.getQuestionTitle());
        responseDto.setQuestionBody(qna.getQuestionBody());
        responseDto.setCreatedAt(qna.getCreatedAt());
        responseDto.setUpdatedAt(qna.getUpdatedAt());

        return responseDto;
    }
}

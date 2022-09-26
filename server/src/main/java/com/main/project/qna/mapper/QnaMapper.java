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
    QnaDto.ResponseDto qnaToQnaResponseDto(QnA qna);
    List<QnaDto.ResponseDto> qnasToQnaResponseDtos(List<QnA> qnas);
}

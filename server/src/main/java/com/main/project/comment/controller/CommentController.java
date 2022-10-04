package com.main.project.comment.controller;

import com.main.project.comment.dto.CommentPatchDto;
import com.main.project.comment.dto.CommentPostDto;
import com.main.project.comment.entity.Comment;
import com.main.project.comment.mapper.CommentMapper;
import com.main.project.comment.service.CommentService;
import com.main.project.review.entity.Review;
import com.main.project.review.service.ReviewServiceImpl;
import com.main.project.entity.Multi_ResponseDTOwithPageInfo;
import com.main.project.user.entity.WebUser;
import com.main.project.user.repository.service.UserServieImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comment")
@Validated
@CrossOrigin("*")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    ReviewServiceImpl reviewService;
    UserServieImpl userService;

    public CommentController(CommentService commentService, CommentMapper commentMapper, ReviewServiceImpl reviewService, UserServieImpl userService) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.reviewService =reviewService;
        this.userService = userService;
    }

    @PostMapping("/post/{user-id}")
    public ResponseEntity postComment (@Valid @RequestBody CommentPostDto commentPostDto) {
        long userId = commentPostDto.getUserId();
        long reviewId = commentPostDto.getReviewId();

        Comment comment = commentMapper.commentPostDtoToComment(commentPostDto);//코멘트 바디만 존재

        Comment createdComment = commentService.createComment(userId, reviewId, comment);

        return new ResponseEntity(commentMapper.commentToCommentResponseDto(createdComment), HttpStatus.CREATED);
    }

    @PatchMapping("/edit")
    public ResponseEntity patchComment (@Valid @RequestBody CommentPatchDto commentPatchDto) {

        Comment comment= commentMapper.commentPatchDtoToComment(commentPatchDto);

        Comment editComment = commentService.updateComment(commentPatchDto.getCommentId(), commentPatchDto.getUserId(), comment);

        return new ResponseEntity(commentMapper.commentToCommentResponseDto(editComment), HttpStatus.OK);
    }

    @GetMapping("/review/{review-id}/{page}")
    public ResponseEntity getReviewComment (@PathVariable("review-id") long reviewId,
                                         @PathVariable("page") int page) {


        Review review = reviewService.findReview(reviewId);
        int size =10;
        Page<Comment> pageComment = commentService.findReviewComment(review,page - 1, size);
        List<Comment> comments = pageComment.getContent();

        return new ResponseEntity(commentMapper.commentsToCommentResponseDtos(comments), HttpStatus.OK);
    }
    @GetMapping("/mypage/{user-id}/{page}")
    public ResponseEntity getAllUserComment (@PathVariable("user-id") long userId, @PathVariable("page") int page) {

        WebUser user = userService.checkUserByUserId(userId);

        Page<Comment> pageComment = commentService.findUserComment(user, page - 1);
        List<Comment> comments = pageComment.getContent();

        return new ResponseEntity<>(new Multi_ResponseDTOwithPageInfo<>(commentMapper.commentsToCommentResponseDtos(comments), pageComment),
                HttpStatus.OK);
    }


    @DeleteMapping("/delete/{comment-id}")
    public ResponseEntity deleteComment (@PathVariable("comment-id") long commentId) {

        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

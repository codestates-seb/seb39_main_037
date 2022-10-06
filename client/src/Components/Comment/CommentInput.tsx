import { SquareButtonForm } from "Components/Common/Button/SquareButtonForm";
import { useComment } from "Hooks/Api/Review/comment";
import useCurrentUser from "Hooks/useCurrentUser";
import React, { useState } from "react";
import { useParams } from "react-router";
import styled from "styled-components";

interface ICommentProps {
  commentId?: number;
  reviewId?: number;
  commentBody?: string;
}

const CommentInput = ({
  commentId = 0,
  reviewId = 0,
  commentBody = "",
}: ICommentProps) => {
  const isEdit = commentId > 0; // 댓글수정
  const isPost = reviewId > 0; // 댓들등록
  const { currentUser } = useCurrentUser();
  const { postComment, patchComment } = useComment();

  const [form, setForm] = useState({
    commentForm: commentBody,
  });
  const { commentForm } = form;

  const onTextAreaChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setForm({
      ...form,
      [name]: value,
    });
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    // commentForm 비어있을 경우 disabled 되도록.
    if (commentForm === "") return;
    if (
      window.confirm(`댓글을 ${isEdit ? "수정" : "등록"}하시겠습니까?`) === true
    ) {
      if (!isEdit) {
        postComment({
          userId: currentUser.userId,
          commentBody: commentForm,
          reviewId,
        }).then((r: any) => {
          window.location.reload();
        });
      } else {
        patchComment({
          commentId,
          userId: currentUser.userId,
          commentBody: commentForm,
        }).then((r: any) => {
          window.location.reload();
        });
      }
    }

    setForm({ commentForm: "" });
  };

  return (
    <>
      <Form onSubmit={handleSubmit}>
        <InputWrapper>
          <InputStyle
            name="commentForm"
            value={commentForm}
            placeholder="댓글을 입력하세요."
            onChange={onTextAreaChange}
          />
        </InputWrapper>
        <ButtonWrapper>
          <SquareButtonForm
            disabled={commentForm === ""}
            title={isEdit ? "수정" : "등록"}
            widthStyle="50%"
            paddingStyle="1vw"
          />
        </ButtonWrapper>
      </Form>
    </>
  );
};
export default CommentInput;
const Form = styled.form`
  width: 90%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: auto;
  padding: 1vw;
`;
const InputWrapper = styled.div`
  width: 75%;
`;

const InputStyle = styled.textarea`
  width: 100%;
  height: 4vw;
  padding: 1vw;
  margin-left: 1vw;
  border: 1px solid lightgray;
  border-radius: 5px;
  font-family: "Nanum Gothic", sans-serif;
  font-size: 11pt;
`;

const ButtonWrapper = styled.div`
  width: 20%;
`;

import { Edit } from "@mui/icons-material";
import CommentInput from "Components/Comment/CommentInput";
import { useComment } from "Hooks/Api/Review/comment";
import useCurrentUser from "Hooks/useCurrentUser";
import React, { useEffect, useState } from "react";
import styled from "styled-components";

const Comment = ({ data }: any) => {
  const { postComment, patchComment, delComment } = useComment();
  const { currentUser } = useCurrentUser();
  const [isEdit, setIsEdit] = useState<boolean>(false);
  const IconStyle = {
    fontSize: 15,
    margin: "5px",
    color: "black",
    opacity: "0.8",
  };
  return (
    <CommentWrapper key={data.commentId}>
      <CommentInfo>
        <NickName>
          {data.nickName}
          <Date>
            {data.createdAt === data.updatedAt ? (
              <div>{data.createdAt.replace("T", " ")}</div>
            ) : (
              <div>{data.updatedAt.replace("T", " ")}수정됨.</div>
            )}
          </Date>
        </NickName>
        {currentUser.nickName === data.nickName && (
          <EditWrapper onClick={() => setIsEdit(!isEdit)}>
            {!isEdit ? "수정" : "닫기"}
            <Edit style={IconStyle} />
          </EditWrapper>
        )}
      </CommentInfo>
      <Content>
        {isEdit ? (
          <CommentInput
            commentId={data.commentId}
            commentBody={data.commentBody}
          />
        ) : (
          <>{data.commentBody}</>
        )}
      </Content>
    </CommentWrapper>
  );
};

export default Comment;

const CommentWrapper = styled.div`
  width: 100%;
  min-height: 15vh;
  padding: 3vw;
  border-bottom: 1px solid #eaeaea;
  white-space: pre-line;
`;
const CommentInfo = styled.div`
  margin: 5px;
  font-size: 12px;
  display: flex;
`;
const NickName = styled.div`
  margin: 5px;
  font-size: 12pt;
  font-weight: bold;
`;
const Date = styled.div`
  margin-top: 2px;
  font-size: 10pt;
  font-weight: lighter;
  color: gray;
`;
const Content = styled.div`
  font-size: 12pt;
  padding-top: 1vw;
  padding-left: 1vw;
  font-weight: lighter;
`;
const EditWrapper = styled.span`
  margin-left: 30px;
  font-size: 9pt;
  opacity: 0.8;
  cursor: pointer;
`;

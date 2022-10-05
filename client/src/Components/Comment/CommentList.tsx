import Comment from "Components/Comment/Comment";
import Loading from "Components/Common/Loading";
import { useComment } from "Hooks/Api/Review/comment";
import useCurrentUser from "Hooks/useCurrentUser";
import React, { useEffect, useState } from "react";
import styled from "styled-components";

interface IComments {
  reviewId: number;
}
interface ICommentObj {
  commentId: number;
  commentBody: string;
  createdAt: string;
  updatedAt: string;
  nickName: string;
  userId: number;
  restaurantId: number;
  restaurantName: string;
  reviewNickName: string;
  reviewPhotoUrl: null | string;
  reviewTitle: string;
}
const commentList = [
  {
    commentId: 2,
    commentBody: "comment2",
    createdAt: "2022-10-01T10:00:47",
    updatedAt: "2022-10-01T10:00:47",
    nickName: "지영",
    userId: 2,
    restaurantId: 1,
    restaurantName: "코코이찌방야 압구정로데오점",
    reviewNickName: "jin2",
    reviewPhotoUrl: null,
    reviewTitle: "review",
  },
  {
    commentId: 1,
    commentBody: "comment",
    createdAt: "2022-10-01T09:20:03",
    updatedAt: "2022-10-01T09:20:03",
    nickName: "minmin2",
    userId: 2,
    restaurantId: 1,
    restaurantName: "코코이찌방야 압구정로데오점",
    reviewNickName: "jin2",
    reviewPhotoUrl: null,
    reviewTitle: "review",
  },
];
const CommentList = ({ reviewId }: IComments) => {
  const { currentUser } = useCurrentUser();
  const [page, setPage] = useState<number>(1);

  const { getComment } = useComment();
  // const [commentList, setCommentList] = useState<ICommentObj[] | null>(null);
  // useEffect(() => {
  //   const fetchData = async () => {
  //     const res = await getComment({ reviewId: { reviewId }, page: { page } });
  //     setCommentList(res);
  //   };
  //   fetchData();
  // }, [page]);

  if (commentList === null) return <Loading />;
  return (
    <CommentListWrapper>
      <div className="top">댓글 (2)</div>
      {commentList.length !== 0 ? (
        commentList.map((c: any) => <Comment data={c} key={c.commentId} />)
      ) : (
        <>첫 댓글을 남겨주세요</>
      )}
    </CommentListWrapper>
  );
};
export default CommentList;

const CommentListWrapper = styled.div`
  width: 100%;
  > .top {
    margin-top: 20px;
    padding-left: 20px;
    font-family: 18px;
    font-weight: 700;
  }
`;

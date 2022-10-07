import Comment from "Components/Comment/Comment";
import Loading from "Components/Common/Loading";
import PaginationForm from "Components/Common/Pagination/PaginationForm";
import { useComment } from "Hooks/Api/Review/comment";
import useCurrentUser from "Hooks/useCurrentUser";
import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { IPageObj } from "Types";

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

const CommentList = ({ reviewId }: IComments) => {
  const { currentUser } = useCurrentUser();
  const [page, setPage] = useState<number>(1);

  const [currentPage, setCurrentPage] = useState<number>(1);
  const { getComment } = useComment();
  const [commentList, setCommentList] = useState<ICommentObj[] | null>(null);
  const [pageInfo, setPageInfo] = useState<IPageObj | null>(null);
  useEffect(() => {
    getComment({ reviewId, page: currentPage }).then((r: any) => {
      console.log(r);
      setCommentList(r);
      // setPageInfo(r.pageInfo);
    });
  }, [page]);

  console.log("comment", commentList);
  if (commentList === null) return <Loading />;
  return (
    <CommentListWrapper>
      <div className="top">댓글{commentList?.length || 0}</div>
      {commentList !== undefined ? (
        commentList.map((c: any) => <Comment data={c} key={c.commentId} />)
      ) : (
        <div className="firstComment">첫 댓글을 남겨주세요</div>
      )}
      {pageInfo && pageInfo.totalPages > 1 && (
        <PaginationForm
          activePage={currentPage}
          totalItemsCount={pageInfo.totalElements}
          onChange={(e: any) => {
            console.log(e);
            setCurrentPage(e);
          }}
        />
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
  > .firstComment {
    margin-top: 20px;
    padding-left: 20px;
    font-family: 18px;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
  }
`;

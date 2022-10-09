import { del, get, patch, post } from "Utils/api";

interface IPostComment {
  userId: number;
  reviewId: number;
  commentBody: string;
}
interface IPatchComment {
  commentId: number;
  userId: number;
  commentBody: string;
}
export const useComment = () => {
  const getComment = async ({ reviewId, page }: any) => {
    const res = await get(`/comment/review/${reviewId}/${page}`).then(
      (r: any) => {
        return r;
      },
    );

    return res;
  };

  const postComment = async ({
    userId,
    reviewId,
    commentBody,
  }: IPostComment) => {
    const res = await post(`/comment/post/${userId}`, {
      userId,
      reviewId,
      commentBody,
    }).then((r: any) => {
      return r;
    });
    return res;
  };
  const patchComment = async ({
    commentId,
    userId,
    commentBody,
  }: IPatchComment) => {
    const res = await patch(`/comment/edit/${commentId}`, {
      commentId,
      userId,
      commentBody,
    }).then((r: any) => {
      return r;
    });
    return res;
  };
  const delComment = async ({ commentId }: any) => {
    const res = await del(`comment/delete/${commentId}`).then((r: any) => {
      return r;
    });
    return res;
  };
  return { getComment, postComment, patchComment, delComment };
};

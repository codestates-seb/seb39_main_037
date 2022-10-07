// import { useNavigate } from "react-router-dom";
import useCurrentUser from "Hooks/useCurrentUser";
import { get, patch } from "Utils/api";

interface IPatchReview {
  userId: number;
}

export const useUsers = () => {
  const { currentUser, setCurrentUser } = useCurrentUser();

  // 회원정보
  const getUsers = async () => {
    const res: any = await get(`user/search/${currentUser.userId}`);

    return res;
  };
  // 회원 리뷰
  const getUsersReview = async ({ page }: any) => {
    const res: any = await get(`review/mypage/${currentUser.userId}/${page}`);
    return res;
  };
  // 회원 좋아요
  const getUsersLike = async ({ page }: any) => {
    const res: any = await get(`thumbUp/mypage/${currentUser.userId}/${page}`);

    return res;
  };
  // 회원 댓글
  const getUsersComments = async ({ page }: any) => {
    const res: any = await get(`comment/mypage/${currentUser.userId}/${page}`);
    return res;
  };
  // 회원 정보수정
  const patchUsers = async ({ userName, nickName, email }: any) => {
    const res: any = await patch(`/user/edit`, {
      userName,
      nickName,
      email,
    }).then((r: any) => {
      console.log(r);
      return r;
    });
    return res;
  };

  return {
    getUsers,
    getUsersReview,
    getUsersLike,
    getUsersComments,
    patchUsers,
  };
};

// const patchReview = async ({
//   reviewId,
//   userId,
//   reviewTitle,
//   reviewBody,
//   tasteStar,
//   facilityStar,
//   priceStar,
// }: IPatchReview) => {
//   const res = await patch(`/review/edit`, {
//     reviewId,
//     userId,
//     reviewTitle,
//     reviewBody,
//     tasteStar,
//     facilityStar,
//     priceStar,
//   }).then((r: any) => {
//     console.log(r);
//     return r;
//   });
//   return res;
// };

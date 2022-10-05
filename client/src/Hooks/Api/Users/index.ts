// import { useNavigate } from "react-router-dom";
import useCurrentUser from "Hooks/useCurrentUser";
import { useState } from "react";
import { get } from "Utils/api";

interface IPatchReview {
  userId: number;
}

export const useUsers = () => {
  const { currentUser, setCurrentUser } = useCurrentUser();
  console.log(currentUser.userId);
  // 회원정보
  const getUsers = async () => {
    const res: any = await get(`user/search/${currentUser.userId}`);

    return res;
  };
  // 회원 리뷰
  const getUsersReview = async ({ reviewId }: any) => {
    const res: any = await get("foodtype/all").then((r: any) => {
      console.log(r);
      return { r };
    });
    return { res };
  };
  // 회원 좋아요
  const getUsersLike = async () => {
    const res: any = await get("foodtype/all").then((r: any) => {
      console.log(r);
      return { r };
    });
    return { res };
  };
  // 회원 댓글
  const getUsersComments = async () => {
    const res: any = await get("foodtype/all").then((r: any) => {
      console.log(r);
      return { r };
    });
    return { res };
  };

  return { getUsers, getUsersReview, getUsersLike, getUsersComments };
};

import { atom } from "recoil"; // recoil 상태관리
import { recoilPersist } from "recoil-persist"; // 로컬스토리지에 저장됨

const { persistAtom } = recoilPersist();

export const userState = atom({
  key: "userState",
  default: {
    userId: 0,
    email: "",
    nickName: "",
    profileImgUrl: "",
    userName: "",
  },
  effects_UNSTABLE: [persistAtom],
});
// 일단 유저 정보를 default로 해서 recoil 에 저장

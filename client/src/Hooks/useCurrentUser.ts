import { useRecoilState } from "recoil";
import { userState } from "Store/userState";

const useCurrentUser = () => {
  const [currentUser, setCurrentUser] = useRecoilState(userState);

  return { currentUser, setCurrentUser };
};

export default useCurrentUser;

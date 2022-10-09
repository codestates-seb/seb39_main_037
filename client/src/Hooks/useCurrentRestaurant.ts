import { useRecoilState } from "recoil";
import { restaurantState } from "Store/restaurantState";

const useCurrentRestaurant = () => {
  const [currentRestaurant, setCurrentRestaurant] =
    useRecoilState(restaurantState);

  return { currentRestaurant, setCurrentRestaurant };
};

export default useCurrentRestaurant;

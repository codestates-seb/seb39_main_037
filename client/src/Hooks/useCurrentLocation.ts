import { useRecoilState } from "recoil";
import { locationState } from "Store/locationState";

const useCurrentLocation = () => {
  const [currentLocation, setCurrentLocation] = useRecoilState(locationState);

  return { currentLocation, setCurrentLocation };
};

export default useCurrentLocation;

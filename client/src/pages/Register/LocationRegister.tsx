import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Select, { SelectChangeEvent } from "@mui/material/Select";
import Loading from "Components/Common/Loading";
import { SquareButtonForm } from "Components/Common/SquareButtonForm";
import { useLocation } from "Hooks/Api/Location";
import useCurrentLocation from "Hooks/useCurrentLocation";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

interface IStateObj {
  stateId: number;
  stateName: string;
}
interface ILocationObj {
  locationId: number;
  stateName: string;
  cityName: string;
}

const LocationRegister = () => {
  const navigate = useNavigate();
  const { getState, getCity } = useLocation();
  const [stateOption, setStateOption] = useState<IStateObj[]>();

  const [locationOption, setLocationOption] = useState<ILocationObj[]>();

  const { setCurrentLocation } = useCurrentLocation();
  // const cityOption = {}
  console.log(stateOption);

  useEffect(() => {
    const fetchData = async () => {
      const res = await getState();
      console.log(res);
      setStateOption(res);
    };
    fetchData();
  }, []);

  const [state, setState] = useState<IStateObj>({
    stateId: 0,
    stateName: "",
  });

  const [location, setLocation] = useState<ILocationObj>({
    locationId: 0,
    stateName: "",
    cityName: "",
  });
  const handleChangeState = (e: SelectChangeEvent) => {
    if (stateOption) {
      console.log(stateOption[Number(e.target.value) - 1]);
      setState(stateOption[Number(e.target.value) - 1]);
      const res = getCity(Number(e.target.value)).then((r: any) => {
        console.log(r);
        setLocationOption(r);
      });
      // console.log(r);
    }
  };

  const handleChangeLocation = (e: SelectChangeEvent) => {
    if (locationOption && e.target.value) {
      console.log(locationOption[Number(e.target.value) - 1]);
      setLocation(locationOption[Number(e.target.value) - 1]);
    }
  };
  const handleClick = () => {
    setCurrentLocation(location);
    alert(`${location.stateName}/${location.cityName}으로 저장되었습니다.`);
    navigate(-1);
  };
  if (!stateOption) {
    return <Loading />;
  }

  return (
    <LocationWrapper>
      <LocationContainer>
        <div className="description">맛집이 궁금한 위치를 입력해주세요!</div>
        <div className="formContainer">
          <FormControl sx={{ width: "50%", marginTop: "60px" }}>
            <InputLabel id="stateLocation">광역자치단체</InputLabel>
            <Select
              labelId="stateLocation"
              id="stateLocation"
              value={String(state.stateId)}
              label="광역자치단체"
              onChange={handleChangeState}
              MenuProps={{
                PaperProps: { sx: { maxHeight: `400px !important` } },
              }}
            >
              {stateOption.map((s) => (
                <MenuItem key={s.stateId} value={s.stateId}>
                  {s.stateName}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
          {locationOption && (
            <FormControl sx={{ width: "50%", marginTop: "60px" }}>
              <InputLabel id="stateLocation">지방자치단체</InputLabel>
              <Select
                className="locationSelect"
                labelId="stateLocation"
                id="stateLocation"
                value={String(location.locationId)}
                label="지방자치단체"
                onChange={handleChangeLocation}
                MenuProps={{
                  PaperProps: { sx: { maxHeight: `400px !important` } },
                }}
              >
                {locationOption.map((l) => (
                  <MenuItem key={l.locationId} value={l.locationId}>
                    {l.cityName}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          )}
        </div>
        {location.locationId !== 0 && (
          <SquareButtonForm title="위치 저장하기" onClick={handleClick} />
        )}
      </LocationContainer>
    </LocationWrapper>
  );
};

export default LocationRegister;

const LocationWrapper = styled.div`
  display: flex;
  min-height: 100vh;
  width: 100%;
  flex-direction: column;
  /* justify-content: center; */
  align-items: center;
`;

const LocationContainer = styled.div`
  display: flex;
  width: 75%;
  height: 100%;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  .description {
    font-size: 2rem;
  }
  .formContainer {
    display: flex;
    gap: 20px;
    margin: 10px;
    width: 100%;
  }
`;

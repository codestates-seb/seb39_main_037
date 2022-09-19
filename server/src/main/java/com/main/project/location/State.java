package com.main.project.location;



public enum State{
    seoul(2,"서울특별시"),
    busan(51, "부산광역시"),
    daegu(53, "대구광역시"),
    inchen(32,"인천광역시"),
    gwangju(62,"광주광역시"),
    daejeon(42,"대전광역시"),
    ulsan(52,"울산광역시"),
    sejong(044,"세종특별자치시"),
    gyunggi(31,"경기도"),
    gangwon(33,"강원도"),
    chungcheongbukdo(43,"충청북도"),
    chungcheongnamdo(41,"충청남도"),
    jeollabukdo(63,"전라북도"),
    jeollanamdo(61,"전라남도"),
    gyeongsangbukdo(54,"경상북도"),
    gyeongsangnamdo(55,"경상남도"),
    jeju(64,"제주특별자치도");

    private int stateIndex;
    private String stateName;


    State(int stateIndex, String stateName) {
        this.stateIndex = stateIndex;
        this.stateName = stateName;
        }

}

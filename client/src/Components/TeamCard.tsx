import React from "react";
import styled from "styled-components";

interface ITeamCardProps {
  img: any;
  name: string;
  featureTag: string[];
  intro: string;
  github: string;
  blog: string;
  email: string;
}
const TeamCard = ({
  img,
  name,
  featureTag,
  intro,
  github,
  blog,
  email,
}: ITeamCardProps) => {
  return (
    <MemberCard>
      <MemberImg data-lazy={img} />
      <MemberText>
        <NameTag>
          <Name>{name}</Name>
          {featureTag.map((el, idx) => (
            // eslint-disable-next-line react/no-array-index-key
            <FeatureTag key={idx}>{el}</FeatureTag>
          ))}
        </NameTag>
        <div>{intro}</div>
        <div>
          <CircleTag>github:</CircleTag>
          <a href={github} target="_blank" rel="noreferrer">
            {github}
          </a>
        </div>
        <div>
          <CircleTag>blog:</CircleTag>
          <a href={blog} target="_blank" rel="noreferrer">
            {blog}
          </a>
        </div>
        <div>
          <CircleTag>email:</CircleTag> {email}
        </div>
      </MemberText>
    </MemberCard>
  );
};

export default TeamCard;

const MemberCard = styled.div`
  width: 56.25rem;
  height: 17.5rem;
  box-shadow: 0px 3px 3px var(--main-yellow);
  border-radius: 10px;
  margin: 1.875rem 0;
  display: flex;
  align-items: center;
  @media (max-width: 768px) {
    width: 90%;
    height: fit-content;
    margin: 1.875rem auto;
    flex-direction: column;
    font-size: 12px;
  }
`;

const MemberImg = styled.img`
  width: 130px;
  height: 130px;
  margin: 30px;
`;

const MemberText = styled.div`
  width: 37.5rem;
  & div {
    margin: 0.5rem 0;
    @media (max-width: 768px) {
      display: flex;
      justify-content: center;
    }
    > a {
      text-decoration: none;
      color: var(--font-color);
    }
  }
  @media (max-width: 768px) {
    width: 100%;
    font-size: 12px;
    box-sizing: border-box;
    padding: 0 20px;
  }
`;

const NameTag = styled.div`
  display: flex;
  align-items: center;
  @media (max-width: 768px) {
    justify-content: center;
  }
`;

const Name = styled.span`
  font-size: 20px;
  color: #5a5a5a;
  @media (max-width: 768px) {
    font-size: 16px;
  }
`;

const FeatureTag = styled.span`
  font-size: 0.75rem;
  padding: 0.25rem 0.75rem;
  margin: 0 0.25rem;
  border: 1px solid var(--main-yellow);
  border-radius: 10px;
  background-color: var(--main-yellow);
  color: #fff;
  font-weight: 600;
  @media (max-width: 768px) {
    padding: 0.1rem 0.5rem;
    font-size: 0.55rem;
  }
`;

const CircleTag = styled.span`
  color: var(--main-yellow);
  font-weight: 600;
`;

//  <TeamCard
//             img={Park}
//             name="박지영"
//             featureTag={["auth", "main", "review"]}
//             intro="무엇을 먹을지 모르겠을 땐? 같이 푸드레코해요!"
//             github="https://github.com/JIYEONGSTAR"
//             blog="https://jiyeongstar.tistory.com/"
//             email="jiyeongstar@gmail.com"
//           />
//           <TeamCard
//             img={Park}
//             name="박지영"
//             featureTag={["auth", "main", "review"]}
//             intro="무엇을 먹을지 모르겠을 땐? 같이 푸드레코해요!"
//             github="https://github.com/JIYEONGSTAR"
//             blog="https://jiyeongstar.tistory.com/"
//             email="jiyeongstar@gmail.com"
//           />
// <TeamCard
//   img={Park}
//   name="박지영"
//   featureTag={["auth", "main", "review"]}
//   intro="무엇을 먹을지 모르겠을 땐? 같이 푸드레코해요!"
//   github="https://github.com/JIYEONGSTAR"
//   blog="https://jiyeongstar.tistory.com/"
//   email="jiyeongstar@gmail.com"
// />
//           <TeamCard
//             img={Park}
//             name="박지영"
//             featureTag={["auth", "main", "review"]}
//             intro="무엇을 먹을지 모르겠을 땐? 같이 푸드레코해요!"
//             github="https://github.com/JIYEONGSTAR"
//             blog="https://jiyeongstar.tistory.com/"
//             email="jiyeongstar@gmail.com"
//           />

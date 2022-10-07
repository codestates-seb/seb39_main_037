import styled from "styled-components";

const Loading = () => {
  return (
    <LoadingWrapper>
      <div id="fountainTextG">
        <div id="fountainTextG_1" className="fountainTextG">
          L
        </div>
        <div id="fountainTextG_2" className="fountainTextG">
          o
        </div>
        <div id="fountainTextG_3" className="fountainTextG">
          a
        </div>
        <div id="fountainTextG_4" className="fountainTextG">
          d
        </div>
        <div id="fountainTextG_5" className="fountainTextG">
          i
        </div>
        <div id="fountainTextG_6" className="fountainTextG">
          n
        </div>
        <div id="fountainTextG_7" className="fountainTextG">
          g
        </div>
      </div>
    </LoadingWrapper>
  );
};

export default Loading;

const LoadingWrapper = styled.div`
  width: 100%;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  #fountainTextG {
    margin: auto;
  }

  .fountainTextG {
    color: rgb(0, 0, 0);
    font-family: Arial;
    font-size: 35px;
    text-decoration: none;
    font-weight: normal;
    font-style: normal;
    float: left;
    animation-name: bounce_fountainTextG;
    -o-animation-name: bounce_fountainTextG;
    -ms-animation-name: bounce_fountainTextG;
    -webkit-animation-name: bounce_fountainTextG;
    -moz-animation-name: bounce_fountainTextG;
    animation-duration: 2.09s;
    -o-animation-duration: 2.09s;
    -ms-animation-duration: 2.09s;
    -webkit-animation-duration: 2.09s;
    -moz-animation-duration: 2.09s;
    animation-iteration-count: infinite;
    -o-animation-iteration-count: infinite;
    -ms-animation-iteration-count: infinite;
    -webkit-animation-iteration-count: infinite;
    -moz-animation-iteration-count: infinite;
    animation-direction: normal;
    -o-animation-direction: normal;
    -ms-animation-direction: normal;
    -webkit-animation-direction: normal;
    -moz-animation-direction: normal;
    transform: scale(0.5);
    -o-transform: scale(0.5);
    -ms-transform: scale(0.5);
    -webkit-transform: scale(0.5);
    -moz-transform: scale(0.5);
  }
  #fountainTextG_1 {
    animation-delay: 0.75s;
    -o-animation-delay: 0.75s;
    -ms-animation-delay: 0.75s;
    -webkit-animation-delay: 0.75s;
    -moz-animation-delay: 0.75s;
  }
  #fountainTextG_2 {
    animation-delay: 0.9s;
    -o-animation-delay: 0.9s;
    -ms-animation-delay: 0.9s;
    -webkit-animation-delay: 0.9s;
    -moz-animation-delay: 0.9s;
  }
  #fountainTextG_3 {
    animation-delay: 1.05s;
    -o-animation-delay: 1.05s;
    -ms-animation-delay: 1.05s;
    -webkit-animation-delay: 1.05s;
    -moz-animation-delay: 1.05s;
  }
  #fountainTextG_4 {
    animation-delay: 1.2s;
    -o-animation-delay: 1.2s;
    -ms-animation-delay: 1.2s;
    -webkit-animation-delay: 1.2s;
    -moz-animation-delay: 1.2s;
  }
  #fountainTextG_5 {
    animation-delay: 1.35s;
    -o-animation-delay: 1.35s;
    -ms-animation-delay: 1.35s;
    -webkit-animation-delay: 1.35s;
    -moz-animation-delay: 1.35s;
  }
  #fountainTextG_6 {
    animation-delay: 1.5s;
    -o-animation-delay: 1.5s;
    -ms-animation-delay: 1.5s;
    -webkit-animation-delay: 1.5s;
    -moz-animation-delay: 1.5s;
  }
  #fountainTextG_7 {
    animation-delay: 1.64s;
    -o-animation-delay: 1.64s;
    -ms-animation-delay: 1.64s;
    -webkit-animation-delay: 1.64s;
    -moz-animation-delay: 1.64s;
  }

  @keyframes bounce_fountainTextG {
    0% {
      transform: scale(1);
      color: rgb(0, 0, 0);
    }

    100% {
      transform: scale(0.5);
      color: rgb(255, 255, 255);
    }
  }

  @-o-keyframes bounce_fountainTextG {
    0% {
      -o-transform: scale(1);
      color: rgb(0, 0, 0);
    }

    100% {
      -o-transform: scale(0.5);
      color: rgb(255, 255, 255);
    }
  }

  @-ms-keyframes bounce_fountainTextG {
    0% {
      -ms-transform: scale(1);
      color: rgb(0, 0, 0);
    }

    100% {
      -ms-transform: scale(0.5);
      color: rgb(255, 255, 255);
    }
  }

  @-webkit-keyframes bounce_fountainTextG {
    0% {
      -webkit-transform: scale(1);
      color: rgb(0, 0, 0);
    }

    100% {
      -webkit-transform: scale(0.5);
      color: rgb(255, 255, 255);
    }
  }

  @-moz-keyframes bounce_fountainTextG {
    0% {
      -moz-transform: scale(1);
      color: rgb(0, 0, 0);
    }

    100% {
      -moz-transform: scale(0.5);
      color: rgb(255, 255, 255);
    }
  }
`;

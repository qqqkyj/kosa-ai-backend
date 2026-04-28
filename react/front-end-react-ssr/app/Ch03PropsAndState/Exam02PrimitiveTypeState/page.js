"use client";

import { useState } from "react";

function Exam02PrimitiveTypeState() {
  // 상태 정의
  const [number, setNumber] = useState(0);
  const [color, setColor] = useState("#7e1b1b");

  //이벤트 처리 함수
  const addNumber = function () {
    setNumber(number + 1);
  };

  const changeColor = () => {
    setColor(getRandomColor);
  };

  // 랜덤 색상(hex 코드)을 생성하는 함수
  const getRandomColor = function () {
    return (
      // 0 ~ ffffff(16진수) 범위의 랜덤 숫자를 생성
      "#" + Math.floor(Math.random() * parseInt("ffffff", 16)).toString(16) // 숫자를 16진수 문자열로 변환
    );
  };

  console.log("계속 실행돼?");

  return (
    <div className="card mt-2">
      <div className="card-header">Exam02PrimitiveTypeState</div>
      <div className="card-body">
        <button onClick={addNumber} className="btn btn-info btn-sm me-2">
          숫자 증가
        </button>
        <button onClick={changeColor} className="btn btn-info btn-sm me-2">
          색상 변경
        </button>
        <hr />
        <h3 style={{ color: color }}>{number}</h3>
      </div>
    </div>
  );
}

export default Exam02PrimitiveTypeState;

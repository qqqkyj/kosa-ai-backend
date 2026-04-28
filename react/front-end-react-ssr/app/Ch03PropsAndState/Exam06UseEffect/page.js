"use client";

import { useEffect, useState } from "react";

function Exam06UseEffect() {
  // 상태 정의
  const [number, setNumber] = useState(0);
  const [color, setColor] = useState("#7e1b1b");

  //이벤트 처리 함수
  const addNumber = function () {
    setNumber(number + 1);
    // setNumber(number => number + 1);
  };

  const changeColor = () => {
    setColor(getRandomColor);
    // setColor((color) => getRandomColor());
  };

  // 랜덤 색상(hex 코드)을 생성하는 함수
  const getRandomColor = function () {
    return (
      // 0 ~ ffffff(16진수) 범위의 랜덤 숫자를 생성
      "#" + Math.floor(Math.random() * parseInt("ffffff", 16)).toString(16) // 숫자를 16진수 문자열로 변환
    );
  };

  //상태가 변경이 되면 자동 콜백되는 함수를 등록
  useEffect(() => {
    console.log("상태 변경 완료1: ", number, color);
  });

  //상태가 변경이 되면 자동 콜백되는 함수를 등록
  useEffect(() => {
    console.log("상태 변경 완료2: ", number, color);
  });

  return (
    <div className="card mt-2">
      <div className="card-header">Exam06UseEffect</div>
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

export default Exam06UseEffect;

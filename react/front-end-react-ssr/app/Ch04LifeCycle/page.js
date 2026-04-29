"use client";

import { useEffect, useState } from "react";

function Ch04LifeCycle() {
  const [count1, setCount1] = useState(1);
  const [count2, setCount2] = useState(101);

  const addCount1 = () => {
    setCount1(count1 + 1);
  };
  const addCount2 = () => {
    setCount2(count2 + 1);
  };

  useEffect(() => {
    console.log("마운트/업데이트 실행");
    return () => {
      console.log("언마운트 실행");
    };
  });

  return (
    <div className="card mt-2">
      <div className="card-header">Ch04LifeCycle</div>
      <div className="card-body">
        <button onClick={addCount1} className="btn btn-info btn-sm me-2">
          count1 증가
        </button>
        <button onClick={addCount2} className="btn btn-info btn-sm me-2">
          count2 증가
        </button>
        <hr />
        {count1} {count2}
      </div>
    </div>
  );
}

export default Ch04LifeCycle;

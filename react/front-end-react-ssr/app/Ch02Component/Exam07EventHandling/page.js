"use client";

import ChildComponentA from "./ChildComponentA";

function Exam07EventHandling() {
  const fun1 = function (event) {
    console.log("버튼1 클릭");
    console.log("버튼1 이벤트가 발생한 객체:", event.target);
    console.log("버튼1 이벤트 이름:", event.type);
  };

  const fun2 = function (arg, event) {
    console.log(`${arg} 클릭`);
    console.log(`${arg} 이벤트가 발생한 객체:`, event.target);
    console.log(`${arg} 이벤트 이름:`, event.type);
  };

  return (
    <div className="card mt-2">
      <div className="card-header">Exam07EventHandling</div>
      <div className="card-body">
        <button className="btn btn-info btn-sm me-2" onClick={fun1}>
          버튼1
        </button>
        <button
          className="btn btn-info btn-sm me-2"
          onClick={(e) => fun2("버튼2", e)}
        >
          버튼2
        </button>

        <hr />

        {/* 컴포넌트(DOM X)는 이벤트 속성을 작성할 수 없음 */}
        {/* <ChildComponentA /> */}
      </div>
    </div>
  );
}

export default Exam07EventHandling;

"use client";
import { useRef } from "react";

function Exam09DomRef() {
  //Dom 객체 찾기
  // const email = document.querySelector("#email");
  // console.log(email);

  // 훅을 이용해서 DOM 참조 얻기
  const inputRef = useRef();

  const handleClick = function (event) {
    // id가 없으면 사용 불가
    // React에서 같은 컴포넌트를 여러 번 사용하면 id 중복 발생 가능성 높음
    // id 사용을 권장하지 않음
    // const email = document.querySelector("#email");
    const email = inputRef.current;
    console.log(email);
    email.focus();
    email.style.backgroundColor = "orange";
    console.log(email.value);

    //form 태그의 제출 기능을 제거
    //서버에 요청(X)
    event.preventDefault();
  };

  return (
    <div className="card mt-2">
      <div className="card-header">Exam09DomRef</div>
      <div className="card-body">
        <form className="row g-3" action="서버 요청 경로">
          <div className="col-auto">
            <label htmlFor="email" className="visually-hidden">
              Email
            </label>
            <input
              type="text"
              id="email"
              ref={inputRef}
              style={{ width: "200px" }}
              className="form-control"
              defaultValue={"email@example.com"}
            />
          </div>
          <div className="col-auto">
            <button
              type="submit"
              onClick={handleClick}
              className="btn btn-info btn-sm me-2"
            >
              입력양식 포커스 및 스타일 변경
            </button>

            {/* 제출 버튼 */}
            <input
              onClick={handleClick}
              type="submit"
              value="제출"
              className="btn btn-info btn-sm"
            />
            <input
              onClick={handleClick}
              type="image"
              src="/logo512.png"
              width="50"
              height="50"
            />
          </div>
        </form>
      </div>
    </div>
  );
}

export default Exam09DomRef;

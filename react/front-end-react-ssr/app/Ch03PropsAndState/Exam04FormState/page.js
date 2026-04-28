"use client";
import { useState } from "react";

function Exam04FormState() {
  //상태 정의
  const [user, setUser] = useState({
    userId: "",
    userName: "",
    userPassword: "",
  });

  //이벤트 처리 함수
  const handleChange = (event) => {
    console.log(`${event.target.name}: ${event.target.value}`);
    setUser({ ...user, [event.target.name]: event.target.value });

    //[참고]
    //setUser((user) => ({ ...user, [event.target.name]: event.target.value }));
  };

  const handleJoin = (event) => {
    // 브라우저 기본 동작 막기
    event.preventDefault();
    console.log(user);
  };

  return (
    <div className="card mt-2">
      <div className="card-header">Exam04FormState</div>
      <div className="card-body">
        <div>
          <div className="mb-2">
            <label htmlFor="btitle" className="form-label">
              User ID
            </label>
            <input
              type="text"
              className="form-control"
              name="userId"
              value={user.userId}
              onChange={handleChange}
            />
          </div>
          <div className="mb-2">
            <label htmlFor="bcontent" className="form-label">
              User Name
            </label>
            <input
              type="text"
              className="form-control"
              name="userName"
              value={user.userName}
              onChange={handleChange}
            />
          </div>
          <div className="mb-2">
            <label htmlFor="bcontent" className="form-label">
              User Password
            </label>
            <input
              type="password"
              className="form-control"
              name="userPassword"
              value={user.userPassword}
              onChange={handleChange}
            />
          </div>
          <div className="d-flex justify-content-center mb-2">
            <button
              className="btn btn-primary btn-sm mr-2"
              onClick={handleJoin}
            >
              제출
            </button>
          </div>
          <div className="alert alert-success">
            <div>userId: {user.userId}</div>
            <div>userName: {user.userName}</div>
            <div>userPassword: {user.userPassword}</div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Exam04FormState;

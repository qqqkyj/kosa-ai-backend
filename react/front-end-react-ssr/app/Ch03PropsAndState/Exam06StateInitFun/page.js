"use client";

import { useState } from "react";

function initBoardList() {
  console.log("initBoardList() 실행");
  const boardList = [];
  for (var i = 1; i <= 3; i++) {
    boardList.push({ bno: i, btitle: "제목" + i });
  }
  return boardList;
}

function Exam06StateInitFun() {
  //상태 정의
  //initBoardList은 초기값 설정이므로 딱 한 번만 실행되어야 함
  //함수 호출로 하지말것 initBoardList() (X) => initBoardList (O)
  //useState(initBoardList) : 컴포넌트 생성시 한 번만 호출
  //useState(initBoardList()) : 상태가 별견될 때 마다 호출
  const [boardList, setBoardList] = useState(initBoardList());

  //이벤트 처리 함수
  const addBoard = () => {
    setBoardList([...boardList, { bno: 4, btitle: "제목4" }]);
  };

  return (
    <div className="card mt-2">
      <div className="card-header">Exam06StateInitFun</div>
      <div className="card-body">
        <button className="btn btn-success btn-sm" onClick={addBoard}>
          추가
        </button>
        <table className="table table-hover">
          <thead>
            <tr className="text-center">
              <th>bno</th>
              <th>btitle</th>
            </tr>
          </thead>
          <tbody>
            {boardList.map((board) => (
              <tr className="text-center" key={board.bno}>
                <th>{board.bno}</th>
                <td>{board.btitle}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Exam06StateInitFun;

"use client";

import { useState } from "react";
import BoardListItem from "./BoardListItem";

function getBoard() {
  const boards = [];
  for (var i = 5; i >= 1; i--) {
    boards.push({ bno: i, btitle: "제목" + i, selected: false });
  }
  return boards;
}

function BoardList() {
  // 새 게시물을 위한 상태 정의
  const [bno, setBno] = useState(6);
  const [btitle, setBtitle] = useState("");

  //게시물 목록을 위한 상태 정의
  const [boards, setBoards] = useState(getBoard);

  return (
    <div className="card mt-2">
      <div className="card-header"></div>
      <div className="card-body">
        <div>
          <div className="mb-2">
            게시물 수(리렌더링될 때마다 getLength1() 함수를 재호출):{" "}
          </div>
          <div className="mb-2">
            게시물 수(boards 상태 변경으로 인한 리렌더링시에만 getLength2()
            함수를 재호출):
          </div>

          <div className="d-flex align-items-center me-2 mb-3">
            <span className="me-2">제목:</span>
            <input type="text" value={btitle} className="me-2" />
            <button className="btn btn-info btn-sm">추가</button>
          </div>
        </div>
        <div className="d-flex bg-info">
          <span className="border" style={{ width: "80px" }}>
            번호
          </span>
          <span className="border flex-fill">제목</span>
        </div>
        {boards.map((board) => (
          <BoardListItem
            key={board.bno}
            board={board}
            removeBoard={null}
            changeBoard={null}
          />
        ))}
      </div>
    </div>
  );
}

export default BoardList;

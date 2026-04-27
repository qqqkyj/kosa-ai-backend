function Exam04InlineStyle() {
  // 변수 선언
  const name = "리액트";

  // 변수 선언
  const style = {
    backgroundColor: "black",
    color: "aqua",
    fontSize: "24px",
    fontWeight: "bold",
    padding: 8,
  };

  style.backgroundColor = "green";

  return (
    <div className="card">
      <div className="card-header">Exam04InlineStyle</div>
      <div className="card-body">
        <div style={style}>{name}</div>

        <div
          style={{
            backgroundColor: name !== "리액트" ? "orange" : "red",
            color: "aqua",
            fontSize: "24px",
            fontWeight: "bold",
            padding: 8,
          }}
        >
          {name}
        </div>
      </div>
    </div>
  );
}

export default Exam04InlineStyle;

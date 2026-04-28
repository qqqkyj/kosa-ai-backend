function ChildComponent({ name, version = 18, children }) {
  console.log(name);
  console.log(version);

  return (
    <div className="card mt-2">
      <div className="card-header">ChildComponent</div>
      <div className="card-body">
        <div>이름: {name}</div>
        <div>버전: {version}</div>
        {children}
      </div>
    </div>
  );
}

export default ChildComponent;

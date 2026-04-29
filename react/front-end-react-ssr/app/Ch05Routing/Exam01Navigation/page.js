import Link from "next/link";

function Exam01Navigation() {
  return (
    <div className="card mt-2">
      <div className="card-header">Exam01Navigation</div>
      <div className="card-body">
        <Link
          href="Exam01Navigation/ChildComponentA"
          className="btn btn-info btn-sm me-2"
        >
          ChildComponentA
        </Link>
        <Link
          href="Exam01Navigation/ServerComponentA"
          className="btn btn-info btn-sm me-2"
        >
          ServerComponentA
        </Link>
      </div>
    </div>
  );
}

export default Exam01Navigation;

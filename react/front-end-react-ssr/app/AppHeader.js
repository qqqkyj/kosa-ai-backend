"use client";

import Image from "next/image";
import Link from "next/link";

function AppHeader() {
  return (
    <nav className="navbar justify-content-between bg-dark">
      <Link href="/" className=" navbar-brand text-white ms-2">
        <Image
          src={"/logo512.png"}
          width={30}
          height={30}
          alt="logo"
          className="align-top"
        />{" "}
        React
      </Link>
      <div className="text-white me-2">
        <button className="btn btn-success btn-sm">로그인</button>
      </div>
    </nav>
  );
}

export default AppHeader;

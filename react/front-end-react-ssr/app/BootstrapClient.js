"use client"; //브라우저에서 실행되도록 강제

import "bootstrap/dist/css/bootstrap.min.css";
//  브라우저 로딩 후에만 JS 실행
import { useEffect } from "react";

function BootstrapClient() {
  useEffect(() => {
    // 자바스크립트 코드 내부에서 import하는 경우 ()(괄호) 필요
    import("bootstrap/dist/js/bootstrap.bundle.min.js");
  }, []);
  return null;
}

export default BootstrapClient;

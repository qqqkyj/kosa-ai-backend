//export default로 내보내기한 것을 가져오는 방법
import moduleA from "./moduleA.js";

//변수값 읽기
console.log(moduleA.moduleA_var1);
console.log(moduleA.moduleA_var2);
console.log(moduleA.moduleA_var3);

//함수 호출 코드
moduleA.moduleA_fun1();
moduleA.moduleA_fun2();
moduleA.moduleA_fun3();

//export로 내보내기한 것을 가져오는 방법
import moduleB, { moduleB_fun1, moduleB_var1 } from "./moduleB.js";
console.log(moduleB_var1);
moduleB_fun1();
console.log(moduleB.moduleB_var2);
moduleB.moduleB_fun2;

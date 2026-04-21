-- ============================================
-- Oracle 사용자 생성 및 권한 설정 (SYS 계정 실행)
-- ============================================

-- 1. 사용자 생성
-- user_spring 계정 생성 (비밀번호: oracle)
CREATE USER user_spring IDENTIFIED BY oracle;

-- 2. 기본 권한 + 테이블 생성 권한 + 테이블스페이스 권한 부여
-- CONNECT       : DB 접속 권한
-- RESOURCE      : 기본 객체 생성 권한
-- CREATE TABLE  : 테이블 생성 권한
-- UNLIMITED TABLESPACE : 저장 공간 무제한 사용
GRANT CONNECT, RESOURCE, CREATE TABLE, UNLIMITED TABLESPACE TO user_spring;

-- 3. 비밀번호 만료 정책 해제
-- DEFAULT 프로파일의 비밀번호 만료 기간을 무제한으로 설정
-- (모든 사용자에 적용되므로 실무에서는 신중하게 사용)
ALTER PROFILE DEFAULT LIMIT PASSWORD_LIFE_TIME UNLIMITED;
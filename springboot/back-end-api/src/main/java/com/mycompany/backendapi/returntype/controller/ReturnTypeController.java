package com.mycompany.backendapi.returntype.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backendapi.returntype.dto.Board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/return-type")
@Slf4j
public class ReturnTypeController {
	@GetMapping("/string")
	public String returnString() {
		return "Hello, World!";
	}
	
//	@GetMapping("/dto")
	@GetMapping(value = "/dto", 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Board dto() {
		Board board = new Board();
		board.setBno(1);
		board.setBtitle("제목입니다.");
		board.setBcontent("내용입니다.");
		board.setBwriter("작성자 입니다.");
		board.setBhitcount(0);
		board.setBdate(new Date());
		return board;
	}
	
	@GetMapping("/list")
	public List<Board> list(){
		List<Board> list = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			Board board = new Board();
			board.setBno(i);
			board.setBtitle(i + "번째 제목입니다.");
			board.setBcontent(i + "번째 내용입니다.");
			board.setBwriter(i + "번째 사용자");
			board.setBhitcount(0);
			board.setBdate(new Date());
			list.add(board);
		}
		return list;
	}
	
	@GetMapping("/map")
	public Map<String, Object> map(){
		Map<String, Object> map = new HashMap<>();
		map.put("mid", "user");
		List<Board> list = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			Board board = new Board();
			board.setBno(i);
			board.setBtitle(i + "번째 제목입니다.");
			board.setBcontent(i + "번째 내용입니다.");
			board.setBwriter(i + "번째 사용자");
			board.setBhitcount(0);
			board.setBdate(new Date());
			list.add(board);
		}
		map.put("boards", list);
		return map;
	}
	
	@GetMapping("/response-entity")
	public ResponseEntity<Board> returnResponseEntity(){
		try {
			Board board = new Board();
			board.setBno(1);
			board.setBtitle("제목입니다.");
			board.setBcontent("내용입니다.");
			board.setBwriter("작성자 입니다.");
			board.setBhitcount(0);
			board.setBdate(new Date());
			
			// 에러 테스트
			// throw new RuntimeException();
			
			//상태코드 200 + JSON 본문 반환
			//return ResponseEntity.ok(board);
			
			//custom header 추가
			HttpHeaders headers = new HttpHeaders();
			headers.add("my-head", "my-value");
			
			//return new ResponseEntity<>(board, headers, HttpStatus.OK);
			//builder 방식(static 메서드라 가능)
			return ResponseEntity
			        .status(HttpStatus.OK)
			        .headers(headers)
			        .body(board);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
					HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value())//404
					);
		}
	}
	
	@GetMapping("/http-entity")
	public HttpEntity<Board> returnHttpEntity(){
		Board board = new Board();
		board.setBno(1);
		board.setBtitle("제목입니다.");
		board.setBcontent("내용입니다.");
		board.setBwriter("작성자 입니다.");
		board.setBhitcount(0);
		board.setBdate(new Date());
		
		//custom header 추가
		HttpHeaders headers = new HttpHeaders();
		headers.add("my-head", "my-value");
		return new HttpEntity<>(board, headers);//상태코드 200이 자동 세팅
	}
	
	
	//브라우저가 HTTP 요청을 보내면 Spring Boot 내장 Tomcat(WAS)이 이를 받아 처리
	//Tomcat은 요청 정보를 담은 HttpServletRequest와 응답을 위한 HttpServletResponse 객체를 자동으로 생성
	//그 후 Spring Controller가 이 객체들을 이용해 요청을 처리하고 응답을 만들어 반환
	@GetMapping("/void/{id}")
	public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id) throws IOException{
		log.info("실행");
		//응답을 직접 생성
		//1) Content-Type 설정: 응답 본문(response body)의 데이터 형식
		//Content-Type: image/jpeg
		//response.addHeader("Content-Type", "image/jpeg");
		response.setContentType("image/jpeg");
		
		//2) Body에 있는 데이터를 브라우저가 바로 보여주지 않고 파일 다운로드를 하도록 설정
		String fileName = "사진" + id + ".jpg";
		//java17 이전 버전은 한글 한글자를 2byte로 읽음(이전 버전 호환을 위해 추가)
		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		
		
		//3) 응답 본문에 데이터를 넣기(출력)
//		방식 1: 큰 파일(이미지 등)을 메모리 적게 쓰면서 다운로드/전송하는 방식
//		InputStream is = new FileInputStream("C:\\kosa-course\\projects\\backend-spring\\back-end-api\\src\\main\\resources\\static\\images\\photo" + id +".jpg");
//		OutputStream os = response.getOutputStream(); //응답 본문(response body)에 데이터를 출력
//		
//		byte[] buffer = new byte[1024]; //1k
//		while(true) {
//			int num = is.read(buffer); // 한 번에 buffer의 크기(1k)만큼 읽을 수 있음
//			if(num == -1) break; // 다 읽으면 -1
//			os.write(buffer, 0, num); //0인텍스에서 읽은 만큼만 출력
//		}
//		os.flush(); // 버퍼에 쌓여있는 데이터를 강제로 즉시 전송하는 기능
//		is.close();
//		os.close();
		
		//방식 2 : 파일 크기가 작은 경우
		Files.copy(Path.of("C:\\kosa-course\\projects\\backend-spring\\back-end-api\\src\\main\\resources\\static\\images\\photo" + id +".jpg") ,
				response.getOutputStream());
	}
	
}

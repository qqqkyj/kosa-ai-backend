package com.mycompany.backendapi.database.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backendapi.database.dto.BoardCreateRequest;
import com.mycompany.backendapi.database.dto.BoardCreateResponse;
import com.mycompany.backendapi.database.dto.BoardDeleteResponse;
import com.mycompany.backendapi.database.dto.BoardListItemResponse;
import com.mycompany.backendapi.database.dto.BoardReadResponse;
import com.mycompany.backendapi.database.dto.BoardUpdateRequest;
import com.mycompany.backendapi.database.dto.BoardUpdateResponse;
import com.mycompany.backendapi.database.dto.Pager;
import com.mycompany.backendapi.database.entity.Board;
import com.mycompany.backendapi.database.interceptor.AccessTokenCheck;
import com.mycompany.backendapi.database.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/database/board")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	private static final int ROWS_PER_PAGE = 10;
	private static final int PAGES_PER_GROUP = 5;
	
	
	@GetMapping("/list")
	public Map<String, Object> list(@RequestParam(value="pageNo", defaultValue = "1") int pageNo) {
		// 페이징 대상이 되는 전체 행의 수 얻기
		int totalRows = boardService.getTotalRows();
		// 페이징 정보를 담고있는 pager 생성
		Pager pager = new Pager(ROWS_PER_PAGE, PAGES_PER_GROUP, totalRows, pageNo);
		// 현재 페이지 내용 가져오기 (Board를 BoardListItemResponse로 변환)
		List<BoardListItemResponse> listItems = boardService.getListResponse(pager);
		//JSON 응답을 위한 Map 리턴
		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("boards", listItems);
		return map;
	}
	
	@PostMapping("/create")
	@AccessTokenCheck
	public BoardCreateResponse create(@ModelAttribute BoardCreateRequest request
									, HttpServletRequest hsr) throws Exception{
		// Entity 생성
		Board board = new Board();
		
		//문자 파트 확인
		log.info(request.getBtitle());
		log.info(request.getBcontent());
		log.info(hsr.getAttribute("mid").toString());
		board.setBtitle(request.getBtitle());
		board.setBcontent(request.getBcontent());
		board.setBwriter(hsr.getAttribute("mid").toString());
		
		// 파일 파트 확인 및 서버 파일 시스템에 파일을 저장
		// null과 isEmpty() 모두 검사해줘야됨
		if(request.getBattach() != null && !request.getBattach().isEmpty()) {
			String battachoname = request.getBattach().getOriginalFilename();
			String contentType = request.getBattach().getContentType();
			log.info(battachoname);
			log.info(contentType);
			board.setBattachtype(contentType);
			board.setBattachoname(battachoname);
			
			// 방법1 : DB에 파일을 직접 저장
			// 파일 사이즈가 작은 경우 (만약 크기가 크다면 InputStream 사용을 권장)
			byte[] fileData = request.getBattach().getBytes();
			board.setBattachdata(fileData);
			
//			방법2 : 서버 파일 시스템에 파일로 저장하고 관리
//			String battachsname = new Date().getTime() + "-" + battachoname;
//			OutputStream os = new FileOutputStream("C:\\Temp\\" + battachsname);
//			os.write(fileData);
//			os.flush();
//			os.close();
//			board.setBattachsname(battachsname);
//			log.info(battachsname);
		}
		
		// 서비스의 비즈니스 로직 호출
		return boardService.create(board);
	}
	
	@GetMapping("/read/{bno}")
	public BoardReadResponse read(@PathVariable("bno") int bno) {
		return boardService.getReadBoard(bno);
	}
	
	@GetMapping("/battach/{bno}")
	@AccessTokenCheck
	public void battach(@PathVariable("bno") int bno, HttpServletResponse response) throws Exception {
		Board board = boardService.getBoard(bno);
		
		//직접 응답을 코드로 생성
		response.setContentType(board.getBattachtype());
		String fileName = board.getBattachoname();
		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1"); //한글
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		OutputStream os = response.getOutputStream();
		
		//방법1 : battachdata 이용
		os.write(board.getBattachdata());
		os.flush();
		os.close();
		
		//방법2 : battachsname 이용, 자동으로 os.close()가 실행됨
//		Path path = Path.of("C:\\Temp\\" + board.getBattachsname());
//		Files.copy(path, os);
	}
	
	@PutMapping("/update")
	@AccessTokenCheck
	public BoardUpdateResponse update(@ModelAttribute BoardUpdateRequest request) throws Exception {
		// Entity 생성
		Board board = new Board();
		
		//문자 파트 확인
		log.info(request.getBtitle());
		log.info(request.getBcontent());
		board.setBtitle(request.getBtitle());
		board.setBcontent(request.getBcontent());
		board.setBno(request.getBno());
		
		// 파일 파트 확인 및 서버 파일 시스템에 파일을 저장
		// null과 isEmpty() 모두 검사해줘야됨
		if(request.getBattach() != null && !request.getBattach().isEmpty()) {
			String battachoname = request.getBattach().getOriginalFilename();
			String contentType = request.getBattach().getContentType();
			log.info(battachoname);
			log.info(contentType);
			board.setBattachtype(contentType);
			board.setBattachoname(battachoname);
			
			// 방법1 : DB에 파일을 직접 저장
			// 파일 사이즈가 작은 경우 (만약 크기가 크다면 InputStream 사용을 권장)
			byte[] fileData = request.getBattach().getBytes();
			board.setBattachdata(fileData);
			
//					방법2 : 서버 파일 시스템에 파일로 저장하고 관리
//					String battachsname = new Date().getTime() + "-" + battachoname;
//					OutputStream os = new FileOutputStream("C:\\Temp\\" + battachsname);
//					os.write(fileData);
//					os.flush();
//					os.close();
//					board.setBattachsname(battachsname);
//					log.info(battachsname);
		}
		
		// 서비스의 비즈니스 로직 호출
		return boardService.update(board);
	}
	
	@DeleteMapping("/delete/{bno}")
	@AccessTokenCheck
	public BoardDeleteResponse delete(@PathVariable("bno") int bno) {
		return boardService.delete(bno);
	}

}

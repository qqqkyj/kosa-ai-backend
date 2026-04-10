package com.mycompany.backendapi.receivedata.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.backendapi.receivedata.dto.LoginRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/receive-data")
public class ReceiveDataController {
	
	//@PathVariable
	@GetMapping("/path-variable/{mid}/{bno}")
	public Map<String, Object> pathVariable(
			@PathVariable("mid") String mid,
			@PathVariable("bno") int boardNo
			){
		Map<String, Object> map = new HashMap<>();
		log.info("mid:" + mid);
		log.info("boardNo:" + boardNo);
		return map;
	}
	
	//@RequestParam
	@GetMapping("/request-param")
	public Map<String, Object> requestParam(
			@RequestParam("mid") String mid,
			@RequestParam("bno") int boardNo
			){
		Map<String, Object> map = new HashMap<>();
		log.info("mid:" + mid);
		log.info("boardNo:" + boardNo);
		return map;
	}
	
	//@ModelAttribute
//	@PostMapping("/model-attribute")
//	public Map<String, Object> modelAttribute(
//			@RequestParam("mid") String mid, //각각 받음
//			@RequestParam("mpassword") String mpassword //각각 받음
//			){
//		Map<String, Object> map = new HashMap<>();
//		log.info("mid:" + mid);
//		log.info("mpassword:" + mpassword);
//		return map;
//	}
	
	//@ModelAttribute
	@PostMapping("/model-attribute")
	public Map<String, Object> modelAttribute(
			@ModelAttribute LoginRequest loginRequest
			){
		Map<String, Object> map = new HashMap<>();
		log.info("mid:" + loginRequest.getMid());
		log.info("mpassword:" + loginRequest.getMpassword());
		log.info(loginRequest.toString());
		return map;
	}
	
	//@RequestBody
	@PostMapping("/request-body")
	public Map<String, Object> requestBody(
			@RequestBody LoginRequest loginRequest
			){
		Map<String, Object> map = new HashMap<>();
		log.info("mid:" + loginRequest.getMid());
		log.info("mpassword:" + loginRequest.getMpassword());
		log.info(loginRequest.toString());
		return map;
	}
	
	//multipart
	@PostMapping(value = "/multipart", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Map<String, Object> multipart(
			@RequestParam("title") String title,
			@RequestParam("attach") MultipartFile attach
			) throws IOException{
		Map<String, Object> map = new HashMap<>();
		log.info("title: " + title);
		log.info("file name: " + attach.getOriginalFilename());
		log.info("file type: " + attach.getContentType());
		
		byte[] fileData = attach.getBytes();
		String strData = new String(fileData);
		log.info("file data: " + strData);
		
		return map;
	}
}

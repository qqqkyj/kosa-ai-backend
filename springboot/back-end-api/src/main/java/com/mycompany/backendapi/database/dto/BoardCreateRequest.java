package com.mycompany.backendapi.database.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardCreateRequest {
	private String btitle;
	private String bcontent;
	private String bwriter;
	private MultipartFile battach;
}

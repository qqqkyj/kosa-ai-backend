package com.mycompany.backendapi.database.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardUpdateRequest {
	private String btitle;
	private String bcontent;
	private int bno;
	private MultipartFile battach;
}

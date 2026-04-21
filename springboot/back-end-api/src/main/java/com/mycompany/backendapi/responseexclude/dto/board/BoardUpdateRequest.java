package com.mycompany.backendapi.responseexclude.dto.board;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardUpdateRequest {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private MultipartFile battach;
}

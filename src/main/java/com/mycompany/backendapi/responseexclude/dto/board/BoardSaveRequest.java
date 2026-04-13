package com.mycompany.backendapi.responseexclude.dto.board;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //@Getter + @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardSaveRequest {
	private String btitle;
	private String bcontent;
	private String bwriter;
	private MultipartFile battach;
}

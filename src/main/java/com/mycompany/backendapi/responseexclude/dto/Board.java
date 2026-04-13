package com.mycompany.backendapi.responseexclude.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //@Getter + @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private int bhitcount;
	private Date bdate;
	
	private MultipartFile battach; // 업로드된 파일을 받을 때
	private String battachoname; //MultipartFile에서 원래 파일 이름을 추출해서 저장 
	private String battachsname; //서버 파일 시스템에 저장할 떄 사용할 파일 이름
	private String battachtype; // 파일의 타입(image/jpeg, image/png 등)
	
	private byte[] battachdata;
}

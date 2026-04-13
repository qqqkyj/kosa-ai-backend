package com.mycompany.backendapi.responseexclude.dto.board;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //@Getter + @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private String btitle;
	private String bcontent;
	private String bwriter;

	
	//browser -> write -> server -> read -> browser
	//Request(x), Response(o)
	@JsonProperty(access = Access.READ_ONLY)
	private int bno;
	private int bhitcount;
	private Date bdate;
	private String battachoname; //MultipartFile에서 원래 파일 이름을 추출해서 저장 
	private String battachsname; //서버 파일 시스템에 저장할 떄 사용할 파일 이름
	private String battachtype; // 파일의 타입(image/jpeg, image/png 등)
	
	//Request(o), Response(x)
	@JsonProperty(access = Access.WRITE_ONLY)
	private MultipartFile battach; // 업로드된 파일을 받을 때
	
	//Request(o), Response(x)
	@JsonProperty(access = Access.WRITE_ONLY)
	private byte[] battachdata;
}

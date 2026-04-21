package com.mycompany.backendapi.database.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
	private int bno;
	private String btitle;
	private String bcontent;
	private Date bdate;
	private String bwriter;
	private int bhitcount;
	private String battachoname;//기존 파일 이름
	private String battachsname;//서버 저장 파일 이름
	private String battachtype;
	private byte[] battachdata;
}

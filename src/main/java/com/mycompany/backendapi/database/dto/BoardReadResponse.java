package com.mycompany.backendapi.database.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardReadResponse {
	private int bno;
	private String btitle;
	private String bwriter;
	private int bhitcount;
	private String bdate;
	private String battachoname;
	private String battachtype;
	private String battachsname;
}

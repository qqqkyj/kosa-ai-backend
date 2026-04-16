package com.mycompany.backendapi.database.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardCreateResponse {
	private int bno;
	private String btitle;
	private String bwriter;
	private int bhitcount;
	private String battachoname;
	private String battachtype;
}

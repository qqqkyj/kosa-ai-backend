package com.mycompany.backendapi.database.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferResponse {
	private String result; //success. failure
	private String message; // failure의 원인
}

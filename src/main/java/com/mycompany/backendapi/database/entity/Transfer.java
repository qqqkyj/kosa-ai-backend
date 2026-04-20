package com.mycompany.backendapi.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Transfer {
	private String from;
	private String to;
	private int amount;
}

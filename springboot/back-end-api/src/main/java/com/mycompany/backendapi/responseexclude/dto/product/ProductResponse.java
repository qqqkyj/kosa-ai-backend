package com.mycompany.backendapi.responseexclude.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
	private int pid;
	private String pname;
	private int pprice;
	private String pcompany;
	private String pimageoname;
	private String pimagesname;
	private String pimagetype;
}

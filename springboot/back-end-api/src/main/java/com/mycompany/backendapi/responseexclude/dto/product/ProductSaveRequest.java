package com.mycompany.backendapi.responseexclude.dto.product;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class ProductSaveRequest {
	private String pname;
	private int pprice;
	private String pcompany;
	private MultipartFile pimage;
}

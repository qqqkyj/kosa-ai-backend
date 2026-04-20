package com.mycompany.backendapi.database.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backendapi.database.dto.TransferRequest;
import com.mycompany.backendapi.database.dto.TransferResponse;
import com.mycompany.backendapi.database.entity.Transfer;
import com.mycompany.backendapi.database.service.AccountService;
import com.mycompany.backendapi.exception.InsufficientFundsException;
import com.mycompany.backendapi.exception.NoAccountExistException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/database/account")
@Slf4j
@RequiredArgsConstructor
public class AccountController {
	private final AccountService accountService;
	
	@PostMapping("/transfer")
	public TransferResponse transfer(@RequestBody TransferRequest request) {
		Transfer transfer = Transfer.builder()
									.from(request.getFrom())
									.to(request.getTo())
									.amount(request.getAmount())
									.build();
		
		TransferResponse tranferResponse;
		try {
			accountService.transfer(transfer);
			tranferResponse = TransferResponse.builder()
					.result("success")
					.build();
		} catch (InsufficientFundsException e) {
			tranferResponse = TransferResponse.builder()
									.result("failure")
									.message("잔고 부족")
									.build();
		} catch (NoAccountExistException e) {
			tranferResponse = TransferResponse.builder()
									.result("failure")
									.message(e.getMessage())
									.build();
		}
		return tranferResponse;
	}
}

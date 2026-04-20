package com.mycompany.backendapi.database.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.backendapi.database.dao.AccountDao;
import com.mycompany.backendapi.database.entity.Account;
import com.mycompany.backendapi.database.entity.Transfer;
import com.mycompany.backendapi.exception.InsufficientFundsException;
import com.mycompany.backendapi.exception.NoAccountExistException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {
	private final AccountDao accountDao;
	
	@Transactional // 중간 실패 시 전체 작업을 롤백
	public void transfer(Transfer transfer) throws InsufficientFundsException,NoAccountExistException{	
		//***********출급하기***********
		Account fromAccount = accountDao.selectByAno(transfer.getFrom());
		if(fromAccount == null) {
			throw new NoAccountExistException("출금 계좌 없음");
		}
		if(fromAccount.getBalance() < transfer.getAmount()) {
			throw new InsufficientFundsException();
		}
		fromAccount.setBalance(fromAccount.getBalance() - transfer.getAmount());
		accountDao.update(fromAccount);
		
		//***********입급하기***********
		Account toAccount = accountDao.selectByAno(transfer.getTo());
		if(toAccount == null) {
			throw new NoAccountExistException("입금 계좌 없음");
		}
		toAccount.setBalance(toAccount.getBalance() + transfer.getAmount());
		accountDao.update(toAccount);
	}
	
}

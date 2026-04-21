package com.mycompany.backendapi.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mycompany.backendapi.di.bean.DIAComponent;
import com.mycompany.backendapi.di.bean.DIBComponent;
import com.mycompany.backendapi.di.bean.DiInterface;
import com.mycompany.backendapi.di.bean.DiInterfaceImplA;
import com.mycompany.backendapi.di.bean.DiInterfaceImplB;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DiMemberService {
	//필드 주입
	@Autowired
	private DIAComponent aComponent;
	
	@Autowired @Qualifier("diInterfaceImplA")
	private DiInterface impleA;
	
	private final DIBComponent bComponent;
	private final DiInterface impleB;
	
	//생성자 주입
	public DiMemberService(DIBComponent bComponent,  @Qualifier("diInterfaceImplB")DiInterface impleB) {
		this.bComponent = bComponent;
		this.impleB = impleB;
	}
	
	public void join() {
		log.info("실행");
		aComponent.method();
		bComponent.method();
		impleA.method();
		impleB.method();
	}
	
	public void login() {
		log.info("실행");
	}
}

package com.dogroup.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.dogroup.DogroupApplication;
import com.dogroup.config.MyApplicationContext;
import com.dogroup.dto.WalletDTO;

@SpringBootTest
@ContextConfiguration(classes = {DogroupApplication.class, MyApplicationContext.class})
public class WalletRepositoryTest {

	@Autowired
	WalletRepository repository;
	
	@Test
	void 프로시저테스트() throws Exception {
		WalletDTO wallet = new WalletDTO(0, "user9@gmail.com", 0, null, 0, "기업은행", 3, 1000);
		int flag = 1;
		
		repository.updateUserBalance(wallet, flag);
	}

}

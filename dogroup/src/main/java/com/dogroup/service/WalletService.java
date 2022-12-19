package com.dogroup.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogroup.dto.UserDTO;
import com.dogroup.dto.WalletDTO;
import com.dogroup.exception.AddException;
import com.dogroup.exception.FindException;
import com.dogroup.repository.WalletRepository;

@Service("walletService")
public class WalletService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private WalletRepository repository;
	
	@Autowired
	private UserService userService;

	/**
	 * 사용자의 지갑 목록을 반환한다.
	 * @param email				사용자 ID
	 * @return 					지갑목록
	 * @throws FindException
	 */
	public List<WalletDTO> searchTransactionListAll(String email) throws FindException {
		return repository.selectWallet(email);
	}

	/**
	 * 사용자의 지갑에 돈을 충전한다
	 * @param wallet
	 * @throws Exception
	 */
	public void deposit(WalletDTO wallet) throws Exception {
		wallet.setTransactionCategory(3);
		repository.updateUserBalance(wallet, 1);
	}

	/**
	 * 사용자의 지갑에 있는 돈을 출금한다
	 * @param wallet
	 * @throws Exception
	 */
	public void withdraw(WalletDTO wallet) throws Exception {
		UserDTO userDTO = userService.searchUserInfo(wallet.getEmail());
		if (userDTO.getUserBalance() > wallet.getTransactionMoney()) {
			wallet.setTransactionCategory(4);
			repository.updateUserBalance(wallet, 0);
		} else {
			throw new AddException();
		}
	}
}

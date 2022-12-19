package com.dogroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogroup.dto.UserDTO;
import com.dogroup.exception.AddException;
import com.dogroup.exception.FindException;
import com.dogroup.repository.UserRepository;

@Service("userService")
public class UserService {
	
	@Autowired
	private UserRepository repository;

	/**
	 * email로 아이디 중복체크한다
	 * 아이디가 없으면 만들수 있다는 의미로 true를 반환하고, 아이디를 발견하면 새로 만들수 없다는 의미로 false를 반환한다.
	 * @param email
	 * @throws FindException 
	 */
	public boolean idDuplicateCheck(String email) {
		try {
			repository.selectUserByEmail(email);
		} catch (FindException e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}

	/**
	 * 회원을 저장소에 추가한다.
	 * 
	 * @param inputUser 회원의 가입 정보
	 * @return 회원 정보
	 */
	public UserDTO signUp(UserDTO inputUser) throws AddException{
		repository.insertUser(inputUser);
		return inputUser;
	}
	
	/**
	 * 회원의 개인정보를 확인한다 .
	 * @param email 회원의 정보
	 * @return 회원 정보
	 * @throws FindException 회원을 찾지못하면 FindException 발생한다.
	 */
	public UserDTO searchUserInfo(String email) throws FindException {
		try {
			return repository.selectUserByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
	}
}

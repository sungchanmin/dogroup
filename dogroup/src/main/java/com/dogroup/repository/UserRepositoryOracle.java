package com.dogroup.repository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dogroup.dto.UserDTO;
import com.dogroup.exception.AddException;
import com.dogroup.exception.FindException;

@Repository("userRepository")
public class UserRepositoryOracle implements UserRepository {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public void insertUser(UserDTO inputUser) throws AddException {
		log.info("insertUser 시작: " + inputUser.getEmail() + "/" + inputUser.getName() + "/" + inputUser.getPassword());
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.insert("com.dogroup.mybatis.UserMapper.insertUser", inputUser);
		} catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
			log.info("insertUser 끝");
		}
	}
	@Override
	public UserDTO selectUserByEmail(String email) throws FindException {
		log.info("selectUserByEmail 시작 - email: " + email);
		SqlSession session = null;
		UserDTO result = null;
		try {
			session = sqlSessionFactory.openSession();
			result = session.selectOne("com.dogroup.mybatis.UserMapper.selectUserByEmail", email);
			if(result == null) {
				throw new FindException("결과를 찾지 못했습니다.");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
			log.info("selectUserByEmail 종료");
		}
	}
}

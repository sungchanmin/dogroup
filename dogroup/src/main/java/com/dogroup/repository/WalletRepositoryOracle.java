package com.dogroup.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dogroup.dto.WalletDTO;
import com.dogroup.exception.AddException;
import com.dogroup.exception.FindException;

@Repository("walletRepository")
public class WalletRepositoryOracle implements WalletRepository {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	/**
	 * 사용자의 지갑 목록을 반환한다.
	 * @param		사용자 이메일
	 * @return		지갑 내역이 없으면 빈 list 객체, 오류가 나면 FindException
	 * @throws FindException
	 */
	@Override
	public List<WalletDTO> selectWallet(String email) throws FindException {
		log.info("selectWallet 시작 email: " + email);
		List<WalletDTO> list = null;
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			list = session.selectList("com.dogroup.mybatis.WalletMapper.selectWallet", email);
			if(list == null) {
				return new ArrayList<>();
			}
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
			log.info("selectWallet 끝");
		}
	}

	/**
	 * 
	 * @param email에  해당하는 사용자의 지갑에 돈을 충전하는 프로시저호출함
	 * @param balance 잔액을 업데이트함
	 * @throws Exception
	 */
//	@Override
//	public void updateUserBalance(WalletDTO wallet, int flag) throws Exception {
//		Connection conn = null;
//		CallableStatement calStmt = null;
//		// 지갑 충전 프로시저
//		try {
//			conn = MyConnection.getConnection();
//			conn.setAutoCommit(false);
//			String procUserWalletSQL = "{call proc_userwallet(?, ?, ?, ?, ?)}";
//			calStmt = conn.prepareCall(procUserWalletSQL);
//			calStmt.setInt(1, flag);
//			calStmt.setString(2, wallet.getEmail());
//			calStmt.setString(3, wallet.getTransactionUser());
//			calStmt.setInt(4, 3);
//			calStmt.setInt(5, wallet.getTransactionMoney());
//			calStmt.executeUpdate();
//
//			conn.commit();
//
//		} catch (SQLException e) {
//			conn.rollback();
//			e.printStackTrace();
//		} finally {
//			MyConnection.close(null, calStmt, conn);
//		}
//	}
	@Override
	public void updateUserBalance(WalletDTO wallet, int flag) throws Exception {
		
		log.info("updateUserBalance 시작: email" + wallet.toString() + "/ flag: " + flag);
		
		SqlSession session = null;
		Map<String, Object> map = new HashMap<>();
		map.put("flag", flag);
		map.put("email", wallet.getEmail());
		map.put("transactionUser", wallet.getTransactionUser());
		map.put("transactionCategory", 3);
		map.put("transactionMoney", wallet.getTransactionMoney());
		
		try {
			session = sqlSessionFactory.openSession();
			session.update("com.dogroup.mybatis.WalletMapper.updateUserBalance", map);
		} catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
			log.info("updateUserBalance 끝");
		}
	}
}

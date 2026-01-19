package com.hyungsuu.apigate.samaple.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyungsuu.apigate.samaple.dao.UserDAO;
import com.hyungsuu.apigate.samaple.service.UserService;
import com.hyungsuu.apigate.samaple.vo.UserReqVo;
import com.hyungsuu.apigate.samaple.vo.UserResVo;
import com.hyungsuu.common.exception.GlobalException;
import com.hyungsuu.common.util.JwtTokenUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {


	@Autowired
    private UserDAO userDAO;
	
	@Override
	public UserResVo selectUser(UserReqVo userReqVo) throws Exception {
	
		UserResVo userResVo = new UserResVo();

		HashMap<String, Object> rtnMap = userDAO.selectUser(userReqVo);

		if (rtnMap != null) {
		log.info("ProcessBusiness. Start() " + rtnMap.toString()+"AAAAAA");
		}
		userResVo.setData(rtnMap);
			log.info("ProcessBusiness. Start() " + userResVo.toString());
		return userResVo;

	}

	
	@Override
	@Transactional
	public int insertUser(UserReqVo userReqVo) throws GlobalException, Exception  {
	
		int intVal = 0;
		try {
			intVal = userDAO.insertUser( userReqVo);
		} catch (DataIntegrityViolationException e) { // MicroService 처리 중 기타 예외 발생 시
			
			log.info("GlobalException ==>" +"||"+e.getMessage() +"||"+e.toString() );
			throw new GlobalException("600", "aaaa", e);
		} catch (DataAccessException e) { // MicroService 처리 중 기타 예외 발생 시
			
			log.info("GlobalException ==>" +"||"+e.getMessage() +"||"+e.toString() );
			throw new GlobalException("600", "aaaa", e);
		} catch (Exception e) { // MicroService 처리 중 기타 예외 발생 시
			log.info("GlobalException ==>" +"||"+e.getMessage() +"||"+e.toString() );
			throw e;
		}
	
		return intVal;

	}


	@Override
	public int updateUser(UserReqVo userReqVo) throws GlobalException, Exception {
		int intVal = 0;
		try {
			intVal = userDAO.updateUser(userReqVo);
		} catch (DataIntegrityViolationException e) { // MicroService 처리 중 기타 예외 발생 시
			
			log.info("GlobalException ==>" +"||"+e.getMessage() +"||"+e.toString() );
			throw new GlobalException("600", "aaaa", e);
		} catch (DataAccessException e) { // MicroService 처리 중 기타 예외 발생 시
			
			log.info("GlobalException ==>" +"||"+e.getMessage() +"||"+e.toString() );
			throw new GlobalException("600", "aaaa", e);
		} catch (Exception e) { // MicroService 처리 중 기타 예외 발생 시
			log.info("GlobalException ==>" +"||"+e.getMessage() +"||"+e.toString() );
			throw e;
		}

		return intVal;
	}


	@Override
	public int deleteUser(UserReqVo userReqVo) throws GlobalException, Exception {
		int intVal = 0;
		try {
			intVal = userDAO.deleteUser(userReqVo);
		} catch (DataIntegrityViolationException e) { // MicroService 처리 중 기타 예외 발생 시
			
			log.info("GlobalException ==>" +"||"+e.getMessage() +"||"+e.toString() );
			throw new GlobalException("600", "aaaa", e);
		} catch (DataAccessException e) { // MicroService 처리 중 기타 예외 발생 시
			
			log.info("GlobalException ==>" +"||"+e.getMessage() +"||"+e.toString() );
			throw new GlobalException("600", "aaaa", e);
		} catch (Exception e) { // MicroService 처리 중 기타 예외 발생 시
			log.info("GlobalException ==>" +"||"+e.getMessage() +"||"+e.toString() );
			throw e;
		}

		return intVal;
	}


	@Override
	public List<Object> selectUserPage(HashMap<String, Object> userMap,RowBounds rowBounds) throws GlobalException, Exception {


		List<Object> rtnList =  (List<Object>) userDAO.selectUserPage( userMap, rowBounds);
		log.info("ProcessBusiness. Start() " + rtnList.toString()+"AAAAAA");	

		return rtnList;
	}

}

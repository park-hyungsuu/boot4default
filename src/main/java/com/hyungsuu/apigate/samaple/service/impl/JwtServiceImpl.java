package com.hyungsuu.apigate.samaple.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hyungsuu.apigate.samaple.dao.UserDAO;
import com.hyungsuu.apigate.samaple.service.JwtService;
import com.hyungsuu.apigate.samaple.vo.JwtTokenReqVo;
import com.hyungsuu.apigate.samaple.vo.JwtTokenResVo;
import com.hyungsuu.apigate.samaple.vo.RefreshTokenReqVo;
import com.hyungsuu.apigate.samaple.vo.UserReqVo;
import com.hyungsuu.common.exception.GlobalException;
import com.hyungsuu.common.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("jwtService")
public class JwtServiceImpl implements JwtService {

//	@Autowired
//    private CommonDAO commonDAO;

	@Autowired
	private UserDAO userDAO;

	@Value("${jwt.expTime}")
	private int jwtExpTime;

	@Transactional
	public JwtTokenResVo generateJwtToken(JwtTokenReqVo jwtTokenReqVo) throws GlobalException, Exception {

		Map<String, Object> rtnMap = new HashMap<String, Object>();
		try {
			rtnMap = userDAO.selectUserAndPass(jwtTokenReqVo);

		} catch (Exception e) { // MicroService 처리 중 기타 예외 발생 시
			log.info("GlobalException ==>" + "||" + e.getMessage() + "||" + e.toString());
			throw e;
		}

		// 데이타가 없는 경우
		if (rtnMap.size() < 1) {
			throw new GlobalException("600", "aaaa");
		}
		JwtTokenResVo jwtTokenResVo = new JwtTokenResVo();
		String jwtToken = null;
//		Date date = new Date(System.currentTimeMillis());
		long date = System.currentTimeMillis();

		jwtToken = JwtTokenUtil.generateToken(jwtTokenReqVo.getUserId(), (String) rtnMap.get("userAuth"), jwtExpTime,
				date);

		jwtTokenResVo.setJwtToken(jwtToken);

		return jwtTokenResVo;

	}

	public JwtTokenResVo getRefreshJwtToken(RefreshTokenReqVo refreshTokenReqVo) throws GlobalException, Exception {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		UserReqVo userReqVo = new UserReqVo();
		userReqVo.setUserId(refreshTokenReqVo.getUserId());
		
		try {
			rtnMap = userDAO.selectUser(userReqVo);

		} catch (Exception e) { // MicroService 처리 중 기타 예외 발생 시
			log.info("GlobalException ==>" + "||" + e.getMessage() + "||" + e.toString());
			throw e;
		}

		// 데이타가 없는 경우
		if (rtnMap.size() < 1) {
			throw new GlobalException("600", "aaaa");
		}
		JwtTokenResVo jwtTokenResVo = new JwtTokenResVo();
		String jwtToken = null;

//		Date date = new Date(System.currentTimeMillis());
		long date = System.currentTimeMillis();
		jwtToken = JwtTokenUtil.generateToken(refreshTokenReqVo.getUserId(), (String) rtnMap.get("userAuth"),
				jwtExpTime, date);
		jwtTokenResVo.setJwtToken(jwtToken);

		return jwtTokenResVo;
	}

	@Override
	public JwtTokenResVo getRefreshJwtToken(UserReqVo userReqVo) throws GlobalException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

package com.hyungsuu.apigate.samaple.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyungsuu.apigate.samaple.vo.JwtTokenReqVo;
import com.hyungsuu.apigate.samaple.vo.RefreshTokenReqVo;
import com.hyungsuu.apigate.samaple.vo.UserReqVo;

@Repository
public class UserDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;


	public HashMap<String, Object> selectUserAndPass(JwtTokenReqVo jwtTokenReqVo) {
		return sqlSession.selectOne("User.selectUserAndPass",jwtTokenReqVo);
	}
	
	public HashMap<String, Object> selectUser(UserReqVo userReqVo) {
		return sqlSession.selectOne("User.selectUser",userReqVo);
	}
	
	public int insertUser(UserReqVo userReqVo) {
		return sqlSession.insert("User.insertUser",userReqVo);
	}
	
	public int updateUser(UserReqVo userReqVo) {
		return sqlSession.update("User.updateUser",userReqVo);
	}
	
	public int deleteUser(UserReqVo userReqVo) {
		return sqlSession.delete("User.deleteUser",userReqVo);
	}
	
	public List<Object> selectUserPage(HashMap<String, Object> userMap, RowBounds rowBounds) {

		return sqlSession.selectList("User.listWithPaging",userMap,rowBounds);
	}
	
	
	

}
package com.hyungsuu.apigate.samaple.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hyungsuu.apigate.samaple.vo.UserReqVo;
import com.hyungsuu.apigate.samaple.vo.UserResVo;
import com.hyungsuu.common.exception.GlobalException;





public interface UserService {


	UserResVo selectUser(UserReqVo userReqVo) throws Exception;
	int insertUser(UserReqVo userReqVo) throws GlobalException, Exception;
	int updateUser(UserReqVo userReqVo) throws GlobalException, Exception;
	int deleteUser(UserReqVo userMap) throws GlobalException, Exception;
	List<Object> selectUserPage(HashMap<String, Object> userMap,RowBounds rowBounds) throws GlobalException, Exception;
	
}

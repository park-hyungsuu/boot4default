package com.hyungsuu.common.exception;


import org.slf4j.MDC;
import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyungsuu.common.vo.BaseResponseVo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CustomErrorController implements ErrorController {

	private String mdcName = "SID";
	
    @RequestMapping("/error")
    public ResponseEntity<BaseResponseVo> handleError(HttpServletRequest request) {
    	Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    	
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf("application/json;charset=UTF-8"));
		BaseResponseVo baseResVo = new BaseResponseVo();
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
        		baseResVo.setCode("3333");
        		baseResVo.setMessage( status.toString() +" is happened. ");
            } else  {
        		baseResVo.setCode("3333");
        		baseResVo.setMessage( status.toString() +" is happened. ");
            }
        }



       	MDC.remove(mdcName);
		return new ResponseEntity<BaseResponseVo>(baseResVo, headers, HttpStatus.OK);

    }
}
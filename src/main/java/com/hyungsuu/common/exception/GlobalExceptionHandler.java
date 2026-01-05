package com.hyungsuu.common.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.hyungsuu.common.vo.BaseResponseVo;



@ControllerAdvice(annotations = RestController.class)
@RestController
public class GlobalExceptionHandler  {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<BaseResponseVo> handleBaseException(GlobalException e) {
    	log.info("Exception is happened!");
    	
    	   HttpHeaders headers = new HttpHeaders();
   	    headers.setContentType(MediaType.valueOf("application/json;charset=UTF-8"));
   	    
   	    
    	
    	BaseResponseVo baseResVo = new BaseResponseVo();
    	baseResVo.setCode(e.getCode());
    	baseResVo.setMessage(e.getMessage());
    	
    	return new ResponseEntity<BaseResponseVo>(baseResVo, headers, HttpStatus.OK);
    }
    
    // Request Body가 없는경우 Json 이나 XML 포맷에 안 맞을때
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponseVo> handleBaseException(HttpMessageNotReadableException e) {
    	e.printStackTrace();
    	log.info("HttpMessageNotReadableException is happened! {}" +e);
    	   HttpHeaders headers = new HttpHeaders();
    	    headers.setContentType(MediaType.valueOf("application/json;charset=UTF-8"));
    	    
    	    
    	BaseResponseVo baseResVo = new BaseResponseVo();
    	baseResVo.setCode("2222");
    	baseResVo.setMessage("HttpMessageNotReadableException is happened!");
    	
    	return new ResponseEntity<BaseResponseVo>(baseResVo,headers, HttpStatus.OK);
    }
    
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<BaseResponseVo> handleValidationExceptions(MethodArgumentNotValidException ex){
    	log.info("MethodArgumentNotValidException is happened!"+ex.getBindingResult().getErrorCount());
    	
    	   HttpHeaders headers = new HttpHeaders();
   	    headers.setContentType(MediaType.valueOf("application/json;charset=UTF-8"));
   	    
   	    
    	BaseResponseVo baseResVo = new BaseResponseVo();
    	baseResVo.setCode("3333");
    	baseResVo.setMessage("MethodArgumentNotValidException is happened.");
    	
    	return new ResponseEntity<BaseResponseVo>(baseResVo, headers, HttpStatus.OK);
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors()
//                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
//        return ResponseEntity.badRequest().body(errors);
    }
    
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponseVo> handleValidationExceptions(Exception ex){
    	log.info("Exception is happened!");
    	
    	   HttpHeaders headers = new HttpHeaders();
   	    headers.setContentType(MediaType.valueOf("application/json;charset=UTF-8"));
   	    
   	    
    	BaseResponseVo baseResVo = new BaseResponseVo();
    	baseResVo.setCode("44444");
    	baseResVo.setMessage("Exception is happened!");
    	
    	return new ResponseEntity<BaseResponseVo>(baseResVo, headers, HttpStatus.OK);
    }
}
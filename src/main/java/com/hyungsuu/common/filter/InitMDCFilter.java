package com.hyungsuu.common.filter;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hyungsuu.common.util.TimeUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class InitMDCFilter extends OncePerRequestFilter {

  private FilterConfig config;

  private String mdcName ="SID";
  
   
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//      // 시간및 난수로 SID 값을 세팅하여 MDC 값 세팅
    	log.info("Start InitMDCFilter");
    	String strSID = TimeUtil.getDateAndRandom();
    	MDC.put(mdcName, strSID);
        filterChain.doFilter(request, response);
    	MDC.remove(mdcName);
    }


}
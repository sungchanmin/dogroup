package com.dogroup.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 컨트롤러의 예외를 처리하는 Advice
 * @author NYK
 *
 */
@ControllerAdvice
public class CommonControllerAdvice {
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<?> except(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseBody
	public ResponseEntity<?> exceptMaxUploadSize(MaxUploadSizeExceededException e){
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		resHeaders.add("Access-Control-Allow-Origin", "*"); //정확한 응답허용할 URL
		resHeaders.add("Access-Control-Allow-Credentials", "true");
		return new ResponseEntity<>("파일 IO에 실패했습니다.", resHeaders, HttpStatus.BAD_REQUEST);
	}
}

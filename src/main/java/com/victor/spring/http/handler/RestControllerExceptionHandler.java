package com.victor.spring.http.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.victor.spring.http.rest")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
}

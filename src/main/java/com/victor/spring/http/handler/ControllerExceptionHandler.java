package com.victor.spring.http.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice(basePackages = "com.victor.spring.http.controller")
public class ControllerExceptionHandler {
}

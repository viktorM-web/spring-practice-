package com.victor.spring.http.controller;

import com.victor.spring.database.entity.Role;
import com.victor.spring.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {
    @ModelAttribute("roles")
    public List<Role> roles() {
        return Arrays.asList(Role.values());
    }


    @GetMapping("/hello")
    public String hello(Model model, HttpServletRequest request,
                        @ModelAttribute("userReadDto") UserReadDto userReadDto) {
//        model.addAttribute("user", new UserReadDto(1L, "Ivan"));

        return "greeting/hello";
    }
    @GetMapping("/bye")
    public String bye(@SessionAttribute("user") UserReadDto user, Model model) {

        return "greeting/bye";
    }

    @GetMapping("/hello/{id}")
    public ModelAndView hello2(ModelAndView modelAndView, HttpServletRequest request,
                              @RequestParam Integer age,
                              @RequestHeader String accept,
                              @CookieValue("JSESSIONID") String jsessionId,
                              @PathVariable("id") Integer id
    ) {
        String ageParamValue = request.getParameter("age");
        String acceptHeader = request.getHeader("accept");
        Cookie[] cookies = request.getCookies();

        modelAndView.setViewName("greeting/hello");

        return modelAndView;
    }
}

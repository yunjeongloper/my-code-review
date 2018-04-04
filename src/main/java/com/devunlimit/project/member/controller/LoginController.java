
package com.devunlimit.project.member.controller;

import com.devunlimit.project.admin.service.AdminService;
import com.devunlimit.project.member.service.AccountService;
import com.devunlimit.project.util.session.LoginManager;
import java.util.HashMap;
import java.util.Map;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

  private LoginManager loginManager = LoginManager.getInstance();

  @Autowired
  AccountService accountService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home() {

    return "redirect:/loginform.do";
  }

  @RequestMapping(value = "/loginform.do", method = {RequestMethod.GET, RequestMethod.POST})
  public ModelAndView handleLogin() {

    ModelAndView mav = new ModelAndView();

    mav.setViewName("member/login");

    return mav;
  }

  @RequestMapping(value = "/login.do", method = RequestMethod.POST)
  @ResponseBody
  public Map handleLoginSess(@RequestParam String id, @RequestParam String pass,
      HttpSession session) throws AuthenticationException {

    Map<String, String> status = new HashMap<>();

    status.put("status", "200");

    try {

      Map<String, String> serviceStatus = accountService.login(id, pass);

      String memberNo = serviceStatus.get("memberNo");

      session.setAttribute("memberNo", memberNo);

      if (loginManager.isUsing(memberNo)) {

        status.put("message", "duplicated");

        status.put("sessId", "memberNo");

      } else {

        loginManager.setSession(session, memberNo);

        status.put("message", "로그인 성공함");

      }

    } catch (AuthenticationException failLength) {

      status.put("status", "400");

      status.put("message", failLength.getMessage());

    }

    return status;

  }

  @RequestMapping(value = "/logout.do", method = {RequestMethod.GET, RequestMethod.POST})
  public ModelAndView logout(HttpSession session) {

    ModelAndView mav = new ModelAndView();

    String memberNo = (String) session.getAttribute("memberNo");

    if (memberNo != null) {

      loginManager.removeSession(memberNo);
    }

    mav.setViewName("redirect:/loginform.do");

    return mav;
  }

}
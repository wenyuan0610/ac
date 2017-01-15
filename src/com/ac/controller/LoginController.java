package com.ac.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ac.base.BaseController;
import com.ac.entity.UserEntity;
import com.ac.service.LoginService;

@Controller
@RequestMapping()
public class LoginController extends BaseController {

	@Resource
	public LoginService loginService;

	@RequestMapping("/")
	public String goTask(HttpServletRequest request) {
		return "/login";
	}

	@RequestMapping("/goLogin")
	public String doLogin(UserEntity userEntity, HttpServletRequest request) {

		List<UserEntity> list = loginService.findListByProperty(
				UserEntity.class, "name", userEntity.getName());
		if (list.size() == 0) {
			request.setAttribute("msg", "该用户不存在");
		} else if (StringUtils.isEmpty(userEntity.getName())) {
			request.setAttribute("msg", "用户名不能为空");
		} else {
			request.setAttribute("msg", "登陆成功");
		}
		return "/login";
	}
}

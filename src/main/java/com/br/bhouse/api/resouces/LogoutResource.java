package com.br.bhouse.api.resouces;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.bhouse.api.config.property.BhouseApiProperty;

@RestController
@RequestMapping("/tokens")
public class LogoutResource {
	
	@Autowired
	private BhouseApiProperty apiProperty;
	
	@DeleteMapping("/revoke")
	public void loggout(HttpServletRequest req, HttpServletResponse resp) {
		Cookie cookie = new Cookie("refresh_token", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(apiProperty.getSecurity().isEnableHttps());
		cookie.setPath(req.getContextPath() + "/oauth/token");
		cookie.setMaxAge(0);

		resp.addCookie(cookie);
		resp.setStatus(HttpStatus.NO_CONTENT.value());
	}

}

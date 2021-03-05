package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.controller.security.AuthenticationRequestDto;
import com.example.demo.dao.UserDao;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.model.User;


@Controller
public class MainController
{
	private final AuthenticationManager authenticationManager;
	private final UserDao userDao;
	private final JwtTokenProvider tokenProvider;

	@Autowired
	public MainController(final AuthenticationManager authenticationManager, final UserDao userDao,
			final JwtTokenProvider tokenProvider)
	{
		this.authenticationManager = authenticationManager;
		this.userDao = userDao;
		this.tokenProvider = tokenProvider;
	}

	@GetMapping("/")
	public String homePage() {
		return "home";
	}

	@GetMapping("/test")
	public String testPage() {
		return "test";
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDto request) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			User user = userDao.findByUsername(request.getUsername());
			String token = tokenProvider.createToken(request.getUsername(), user.getRole().name());
			Map<Object, Object> response = new HashMap<>();
			response.put("email", request.getUsername());
			response.put("token", token);
			return ResponseEntity.ok(response);
		} catch (AuthenticationException e) {
			return new ResponseEntity<>("Invalid email/password", HttpStatus.FORBIDDEN);
		}
	}

	@PostMapping("/logout")
	public void logout(@RequestBody HttpServletRequest request, HttpServletResponse response) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, response, null);
	}
}

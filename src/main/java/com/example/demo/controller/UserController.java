package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Login;
import com.example.demo.dto.Register;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.mail.MessagingException;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@PostMapping("/register")
	
	public ResponseEntity<String> UserRegister(@RequestBody Register register)
	{
		return new ResponseEntity<>(userService.register(register), HttpStatus.OK);
	}
	
	  @PutMapping("/verify-account")
	  public ResponseEntity<String> verifyAccount(@RequestParam String email,
	      @RequestParam String otp) {
	    return new ResponseEntity<>(userService.verifyAccount(email, otp), HttpStatus.OK);
	  }
	  @PutMapping("/regenerate-otp")
	  public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
	    return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
	  }
	  @PutMapping("/login")
	  public ResponseEntity<String> login(@RequestBody Login login) {
	    return new ResponseEntity<>(userService.login(login), HttpStatus.OK);
	  }
	  
	  @PutMapping("/forgot-password")
	  public ResponseEntity<String> forgotPaasword(@RequestParam String email) throws MessagingException
	  {
		  return new ResponseEntity<>(userService.forgotPssword(email), HttpStatus.OK);
	  }
	  
	  @PutMapping("/set-password")
	  public ResponseEntity<String> setPassword(@RequestParam String email, @RequestParam String newpass)
	  {
		  
		  
		return new ResponseEntity<>(userService.setPassword(email, newpass),HttpStatus.OK);
		  
	  }
 }

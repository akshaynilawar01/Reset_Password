package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private boolean active;
	
	private String otp;
	
	private LocalDateTime otpGenertedTime;

	public User(int id, String name, String email, String password, boolean active, String otp,
			LocalDateTime otpGenertedTime) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.active = active;
		this.otp = otp;
		this.otpGenertedTime = otpGenertedTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getOtpGenertedTime() {
		return otpGenertedTime;
	}

	public void setOtpGenertedTime(LocalDateTime otpGenertedTime) {
		this.otpGenertedTime = otpGenertedTime;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}

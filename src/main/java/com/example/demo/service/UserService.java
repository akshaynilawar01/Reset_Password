package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Login;
import com.example.demo.dto.Register;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.utils.EmailUtils;
import com.example.demo.utils.OtpUtils;


import jakarta.mail.MessagingException;

@Service
public class UserService {

	private static final long OTP_EXPIRATION_SECONDS = 60;

	@Autowired
	
	private UserRepo userRepo;
	
    @Autowired
	
	private OtpUtils otpUtils;
    

    @Autowired
	
	private EmailUtils emailUtils;
	
    public String register(Register register) {
        String otp = otpUtils.generatedOtp();
        try {
          emailUtils.sendOtpEmail(register.getEmail(), otp);
        } catch (MessagingException e) {
          throw new RuntimeException("Unable to send otp please try again");
        }
        User user = new User();
        user.setName(register.getName());
        user.setEmail(register.getEmail());
        user.setPassword(register.getPassword());
        user.setOtp(otp);
        user.setOtpGenertedTime(LocalDateTime.now());
        userRepo.save(user);
        return "User registration successful";
      }

      public String verifyAccount(String email, String otp) {
        User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        
        boolean a = user.getOtpGenertedTime().plusSeconds(OTP_EXPIRATION_SECONDS).isAfter(LocalDateTime.now());
        if (user.getOtp().equals(otp) && a == true
                )
       {
          user.setActive(true);
          userRepo.save(user);
          return "OTP verified you can login";
        }
        return "Please regenerate otp and try again";
      }

      public String regenerateOtp(String email) {
        User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        String otp = otpUtils.generatedOtp();
        try {
          emailUtils.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
          throw new RuntimeException("Unable to send otp please try again");
        }
        user.setOtp(otp);
        user.setOtpGenertedTime(LocalDateTime.now());
        userRepo.save(user);
        return "Email sent... please verify account within 1 minute";
      }

      public String login(Login login) {
        User user = userRepo.findByEmail(login.getEmail())
            .orElseThrow(
                () -> new RuntimeException("User not found with this email: " + login.getEmail()));
        if (!login.getPassword().equals(user.getPassword())) {
          return "Password is incorrect";
        } else if (!user.isActive()) {
          return "your account is not verified";
        }
        return "Login successful";
      }

	public String forgotPssword(String email) throws MessagingException {
		User user = userRepo.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		emailUtils.setPasswordOnEmail(email);
		return "please check email";
	}                                                                  

	public String setPassword(String email, String newpass) {         
		User user = userRepo.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		user.setPassword(newpass);
		userRepo.save(user);
		return "new password set";
	}
}                                                                                                                                                                                                           

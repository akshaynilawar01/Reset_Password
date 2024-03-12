package com.example.demo.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class OtpUtils {

	public String generatedOtp() {
		Random random = new Random();
		int randomnumber = random.nextInt(99999);
		String op = Integer.toString(randomnumber);
		while(op.length()<6)
		{
			op = "0" + op;
		}
		
		return op;
	}
}

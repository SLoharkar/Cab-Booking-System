package com.example.cbs.services.impl;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cbs.exceptions.DataNotFoundException;
import com.example.cbs.exceptions.pojo.SucessResponse;
import com.example.cbs.models.UserRegistration;
import com.example.cbs.repository.UserRepo;
import com.example.cbs.services.UserServices;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	UserRepo userRepo;

	@Autowired
	AuthenticationManagerBuilder amb;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	CustomUserDetailService customUserDetailService;

	private static int otp;

	private static final Logger log = LoggerFactory.getLogger(UserServicesImpl.class);

	public UserRegistration userRegistration(UserRegistration userRegistration) {
		// Encrypt password store in database
		String encodePassword = bCryptPasswordEncoder.encode(userRegistration.getPassword());
		userRegistration.setPassword(encodePassword);
		userRegistration.setRole("ROLE_NORMAL");
		userRepo.save(userRegistration);

		return userRegistration;
	}

	public List<UserRegistration> getUser() {

		return userRepo.getAllUser();
	}

	public UserRegistration login(String username, String password) {
		UserRegistration user = userRepo.findByUsername(username);

		if (user != null) {
			if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
				customUserDetailService.loadUserByUsername(username);
				return user;
			}
		}
		throw new DataNotFoundException("please enter valid username or password");

	}

	public SucessResponse forgotPassword(String username, String password) {
		UserRegistration user = userRepo.findByUsername(username);
		if (user != null) {
			user.setPassword(bCryptPasswordEncoder.encode(password));
			userRepo.save(user);
			return new SucessResponse(200, "password change sucessfully");
		}
		throw new DataNotFoundException("please enter valid username");
	}

	public SucessResponse sendEmail()// throws AddressException, MessagingException, IOException
	{
		String host = "smtp.gmail.com";

		String to = "to@gmail.com";

		String from = "from@gmail.com";

		double randomNumber = Math.random() * 900000 + 100000;

		otp = (int) randomNumber;

		String message = "" +

				"Dear Sir/Mam,\n\n" +

				"An email OTP has been sent to your registered email address.\n" +

				"Please enter the OTP in the field below to verify your identity:" + otp + ".\n" +

				"If you do not receive the OTP within a few minutes, please check your spam folder or resend the OTP.\n\n\n"
				+

				"Thank you,\n\n" +
				"Cab Booking System";

		// get the system properties
		Properties properties = System.getProperties();

		// System.out.println("Properties "+properties);
		log.info("Properties " + properties);

		// setting important information to properties object

		// host set

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", "587");

		// Step 1 get the session object

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("user@gmail.com", "Password");
			}

		});

		session.setDebug(true);

		// Step 2 : compose the message
		try {

			MimeMessage m = new MimeMessage(session);

			// from email
			m.setFrom(from);

			// adding receipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject("Verify Email OTP");

			// adding text to message
			m.setText(message);

			// send

			// Step 3 : Send the message using transport class
			Transport.send(m);

			return new SucessResponse(200, "Email OTP Sent Sucessfully");

		} catch (Exception e) {
			// e.printStackTrace();
			log.error(e.toString());

		}

		throw new DataNotFoundException("Something Went Wrong!");

	}

	public SucessResponse verifyEmail(int user_otp) {
		if (user_otp == otp) {
			return new SucessResponse(200, "Email Verified Sucessfully");
		}
		throw new DataNotFoundException("Enter OTP is Invalid");
	}

}

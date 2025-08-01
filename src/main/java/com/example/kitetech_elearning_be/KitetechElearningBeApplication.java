package com.example.kitetech_elearning_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;

@SpringBootApplication
public class KitetechElearningBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KitetechElearningBeApplication.class, args);

		try {
			// Get the local host IP address
			String ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println("Current Machine IP Address: " + ip);
		} catch (Exception e) {
			System.err.println("Unable to get IP address: " + e.getMessage());
		}
	}
}

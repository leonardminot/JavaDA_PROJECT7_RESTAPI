package com.nnk.springboot;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private final UserRepository userRepository;

	@Autowired
    public Application(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {

		userRepository.save(new User(
				"LeoM",
				"$2y$10$VJyFLNAl.cvMSN/hgbnFEuBIRXbdprgvgnfFy6vrn6BcnoyOy5Xh2",
				"Léonard MINOT",
				"ADMIN")
		);
		userRepository.save(new User(
				"VictorM",
				"$2y$10$VJyFLNAl.cvMSN/hgbnFEuBIRXbdprgvgnfFy6vrn6BcnoyOy5Xh2",
				"Victor MINOT",
				"USER")
		);
	}
}

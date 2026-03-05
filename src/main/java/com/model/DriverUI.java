package com.model;

import java.util.Scanner;
import java.util.UUID;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DriverUI {
	private SystemFacade driver;

	DriverUI() {
		driver = SystemFacade.getInstance();
	}

	public void run() {
		scenario1();
		scenario2();
	}

	public void scenario1() {
		System.out.println("Scenario 1: Login");

		User loggedInUser = driver.login("grant.smith@example.com", "123grant");
		if (loggedInUser == null) {
			System.out.println("Sorry we couldn't login.");
			return;
		}
		System.out.println("Grant Smith is now logged in");

	}

	public void scenario2() {
		System.out.println("Scenario 2: User Creation");

		UUID user1Id = UUID.randomUUID();
		User newUser = new User(
			user1Id,
            "john.doe@example.com",
            "hashed-password-123",
            "John",
            "Doe",
            LocalDateTime.parse("2024-01-15T10:30:00"),
            LocalDateTime.parse("2026-02-20T14:45:00"),
            true,
            true,
            new Profile("University of South Carolina", "Computer Science", 2026, 5, "https://example.com/resume.pdf"),
            new ArrayList<>()
		);

		if (driver.addUser(newUser)) {
			System.out.println("User John Doe has been successfully created");
			//driver.saveAllData();
			//driver.deleteUser(newUser.getUserId());
		} else {
			System.out.println("Sorry, we couldn't create the user.");
		}
	}


	public static void main(String[] args) {
		DriverUI appInterface = new DriverUI();
		appInterface.run();

	}
}
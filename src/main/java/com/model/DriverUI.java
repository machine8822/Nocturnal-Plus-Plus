package com.model;

import java.util.Scanner;

public class DriverUI {
	private SystemFacade driver;

	DriverUI() {
		driver = SystemFacade.getInstance();
	}

	public void run() {
		scenario1();
	}

	public void scenario1() {
		System.out.println();

		User loggedInUser = driver.login("grant.smith@example.com", "$2a$12$dOHvtvzLflQ8cC4hR50Z3.lMcJXDsAwMZD9sfy4gWthw2zWI6vDKe");
		if (loggedInUser == null) {
			System.out.println("Sorry we couldn't login.");
			return;
		}
		System.out.println("Amy Smith is now logged in");

	}
}
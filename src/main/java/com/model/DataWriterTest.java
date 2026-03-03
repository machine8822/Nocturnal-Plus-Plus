package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Simple test for DataWriter to check if it can save users and questions to JSON files.
 * 
 * Run this to test if saveUsers() and saveQuestions() work.
 * Check the json/ folder to see if the files were created.
 */
public class DataWriterTest {

	public static void main(String[] args) {
		System.out.println("=== Testing DataWriter ===\n");

		// TEST 1: Save Users
		System.out.println("TEST 1: Saving Users...");
		testSaveUsers();

		System.out.println("\n" + "=".repeat(50) + "\n");

		// TEST 2: Save Questions
		System.out.println("TEST 2: Saving Questions...");
		testSaveQuestions();

		System.out.println("\n=== Tests Complete ===");
		System.out.println("Check json/users.json and json/questions.json to see the results!");
	}

	/**
	 * Create some dummy users and try to save them
	 */
	private static void testSaveUsers() {
		// Create a list of test users
		ArrayList<User> users = new ArrayList<>();
		
		//dummy user uuids for testing
		UUID user1Id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UUID user2Id = UUID.fromString("22222222-2222-2222-2222-222222222222");

		// Create User 1 
		User user1 = new User(
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
		// Create User 2
		User user2 = new User(
            user2Id,
            "jane.smith@example.com",
            "hashed-password-456",
            "Jane",
            "Smith",
            LocalDateTime.parse("2024-02-01T10:30:00"),
            LocalDateTime.parse("2026-02-18T14:45:00"),
            false,
            true,
            new Profile("University of North Carolina", "Computer Engineering", 2027, 10, "https://example.com/resume2.pdf"),
            new ArrayList<>()
        );

		// Add users to the list
		users.add(user1);
		users.add(user2);

		// Call saveUsers() and check if it worked
		boolean success = DataWriter.saveUsers(users);

		if (success) {
			System.out.println(" SUCCESS: Users saved to json/users.json");
			System.out.println("  Total users saved: " + users.size());
		} else {
			System.out.println(" FAILED: Could not save users");
		}
	}

	/**
	 * Create some dummy questions and try to save them
	 */
	private static void testSaveQuestions() {
		// Create a list of test questions
		ArrayList<InterviewQuestion> questions = new ArrayList<>();

		UUID user1Id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UUID user2Id = UUID.fromString("22222222-2222-2222-2222-222222222222");

		// Create Question 1 
		InterviewQuestion question1 = new InterviewQuestion(
            UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"),
            "Binary search time complexity",
            "What is the time complexity of binary search on a sorted array and why?",
            Difficulty.EASY,
            Category.ARRAY,
            QuestionType.SHORT_ANSWER,
            user2Id,
            LocalDateTime.parse("2026-02-21T13:30:00"),
            LocalDateTime.parse("2026-02-21T13:30:00"),
            0,
            0,
            ""
		);
		
		// Add a section to the question
		Section section1 = new Section(
			"Description",
			"What is the time complexity of binary search on a sorted array and why?",
			"DESCRIPTION"
		);
		// Add the section to the question (you may need to adjust this based on your Question class)
		question1.addSection(section1);

		// Create Question 2

        InterviewQuestion question2 = new InterviewQuestion(
            UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb"),
            "Detecting duplicates in an array",
            "Given an integer array, explain an efficient approach to determine whether the array contains any duplicate values.",
            Difficulty.MEDIUM,
            Category.ARRAY,
            QuestionType.SHORT_ANSWER,
            user1Id,
            LocalDateTime.parse("2026-02-21T14:00:00"),
            LocalDateTime.parse("2026-02-21T14:00:00"),
            0,
            0,
            ""
        );
	
		// Add a section to the question
		Section section2 = new Section(
			"Description",
			"Given an integer array, explain an efficient approach to determine whether the array contains any duplicate values.",
			"DESCRIPTION"
		);
		question2.addSection(section2);

		// Add questions to the list
		questions.add(question1);
		questions.add(question2);

		// Call saveQuestions() and check if it worked
		boolean success = DataWriter.saveQuestions(questions);

		if (success) {
			System.out.println(" SUCCESS: Questions saved to json/questions.json");
			System.out.println("  Total questions saved: " + questions.size());
		} else {
			System.out.println(" FAILED: Could not save questions");
		}
	}
}

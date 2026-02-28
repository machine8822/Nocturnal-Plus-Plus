package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * DataWriter saves User and InterviewQuestion objects to JSON files.
 * 
 * This class is simple and straightforward:
 * - Call saveUsers() to write users to json/users.json
 * - Call saveQuestions() to write questions to json/questions.json
 */
public class DataWriter {

	private static final String USER_FILE = "json/users.json";
	private static final String QUESTION_FILE = "json/questions.json";

	/**
	 * Save all users to json/users.json
	 * 
	 * @param users: ArrayList of User objects we want to save
	 * @return true if save worked, false if there was an error
	 */
	public static boolean saveUsers(ArrayList<User> users) {
		// Create an empty JSON array (like an empty box to hold all users as JSON)
		JSONArray jsonUsers = new JSONArray();

		// For each user in the list, convert it to JSON and add it to the box
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			JSONObject userJSON = getUserJSON(user);
			jsonUsers.add(userJSON);
		}

		// Now write the entire box of JSON users to the file
		return writeJSONToFile(jsonUsers, USER_FILE);
	}

	/**
	 * Convert a single User object into a JSON object
	 * 
	 * @param user: the User object to convert
	 * @return JSONObject with all the user's data
	 */
	public static JSONObject getUserJSON(User user) {
		// Create an empty JSONObject (like an empty form to fill in)
		JSONObject userDetails = new JSONObject();

		// Fill in each field from the user object
		// Each put() is like: name → "John", email → "john@example.com", etc.
		userDetails.put("userId", user.getUserId());
		userDetails.put("email", user.getEmail());
		userDetails.put("passwordHash", user.getPasswordHash());
		userDetails.put("firstName", user.getFirstName());
		userDetails.put("lastName", user.getLastName());
		userDetails.put("createdAt", user.getCreatedAt());
		userDetails.put("lastLogin", user.getLastLogin());
		userDetails.put("isAdmin", user.isAdmin());
		userDetails.put("isContributor", user.isContributor());

		// Handle the profile (a nested object inside the user)
		JSONObject profile = new JSONObject();
		profile.put("school", user.getProfile().getSchool());
		profile.put("major", user.getProfile().getMajor());
		profile.put("totalUpvotes", user.getProfile().getTotalUpvotes());
		profile.put("resumeURL", user.getProfile().getResumeURL());
		userDetails.put("profile", profile);

		return userDetails;
	}

	/**
	 * Save all interview questions to json/questions.json
	 * 
	 * @param questions: ArrayList of InterviewQuestion objects we want to save
	 * @return true if save worked, false if there was an error
	 */
	public static boolean saveQuestions(ArrayList<InterviewQuestion> questions) {
		// Create an empty JSON array to hold all questions
		JSONArray jsonQuestions = new JSONArray();

		// For each question in the list, convert it to JSON and add it to the array
		for (int i = 0; i < questions.size(); i++) {
			InterviewQuestion question = questions.get(i);
			JSONObject questionJSON = getQuestionJSON(question);
			jsonQuestions.add(questionJSON);
		}

		// Write the entire array of JSON questions to the file
		return writeJSONToFile(jsonQuestions, QUESTION_FILE);
	}

	/**
	 * Convert a single InterviewQuestion object into a JSON object
	 * 
	 * @param question: the InterviewQuestion to convert
	 * @return JSONObject with all the question's data
	 */
	public static JSONObject getQuestionJSON(InterviewQuestion question) {
		// Create an empty JSONObject to fill in with question data
		JSONObject questionDetails = new JSONObject();

		// Fill in the basic question fields
		questionDetails.put("questionId", question.getQuestionId());
		questionDetails.put("title", question.getTitle());
		questionDetails.put("difficulty", question.getDifficulty());
		questionDetails.put("type", question.getType());
		questionDetails.put("category", question.getCategory());
		questionDetails.put("imageURL", question.getImageURL());
		questionDetails.put("authorId", question.getAuthorId());
		questionDetails.put("totalAttempts", question.getTotalAttempts());
		questionDetails.put("totalSuccesses", question.getTotalSuccesses());
		questionDetails.put("createdAt", question.getCreatedAt());
		questionDetails.put("lastUpdated", question.getLastUpdated());

		// Handle sections (a list of section objects)
		ArrayList<Section> sections = question.getSections();
		JSONArray sectionsJSON = new JSONArray();
		for (int i = 0; i < sections.size(); i++) {
			Section section = sections.get(i);
			JSONObject sectionJSON = getSectionJSON(section);
			sectionsJSON.add(sectionJSON);
		}
		questionDetails.put("sections", sectionsJSON);

		return questionDetails;
	}

	/**
	 * Helper method to convert a Section object to JSON
	 * 
	 * @param section: the Section object to convert
	 * @return JSONObject with all the section's data
	 */
	private static JSONObject getSectionJSON(Section section) {
		JSONObject sectionDetails = new JSONObject();

		sectionDetails.put("title", section.getTitle());
		sectionDetails.put("content", section.getContent());
		sectionDetails.put("type", section.getType());

		// Handle answers (a list of Answer objects)
		// TODO: add answers conversion when Answer class is ready

		// Handle comments (a list of Comment objects)
		// TODO: add comments conversion when Comment class is ready

		return sectionDetails;
	}

	/**
	 * Generic helper method to write ANY JSON (array or object) to a file
	 * 
	 * @param json: the JSONArray or JSONObject to write
	 * @param filePath: the path where to save it (e.g., "json/users.json")
	 * @return true if successful, false if something went wrong
	 */
	private static boolean writeJSONToFile(Object json, String filePath) {
		try (FileWriter file = new FileWriter(filePath)) {
			
            // Convert the JSON to a string and write it to the file
			file.write(json.toString());
			
			// Make sure the data is actually written to disk (not stuck in memory)
			file.flush();
			
			// Success!
			return true;
		} catch (IOException e) {
			// If something went wrong, print the error for debugging
			System.err.println("Error writing to file: " + filePath);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Main method for testing
	 * 
	 * Uncomment and modify to test saving users or questions
	 */
	public static void main(String[] args) {
		// Example: Create some test users and save them
		// ArrayList<User> testUsers = new ArrayList<>();
		// testUsers.add(new User(...));
		// boolean success = DataWriter.saveUsers(testUsers);
		// System.out.println("Users saved: " + success);
	}
}
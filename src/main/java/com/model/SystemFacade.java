package com.model;

/**
 * @author King
 */

public class SystemFacade {
    private static SystemFacade instance;
    private UserList users;
    private QuestionList questions;
    private User currentUser;
    private InterviewQuestion currentQuestion;

    private SystemFacade() {
        users = UserList.getInstance();
        questions = QuestionList.getInstance();
        currentUser = null;
        currentQuestion = null;
    }

    public static SystemFacade getInstance() {
        if (instance == null) {
            instance = new SystemFacade();
        }
        return instance;
    }

    public User login(String identifier, String pass) {
        User user = users.authenticate(identifier, pass);
        if (user != null) {
            currentUser = user;
        }
        return user;
    }

    public boolean logout() {
        currentUser = null;
        currentQuestion = null;
        return true;
    }

    public void selectQuestion(UUID id) {
        currentQuestion = questions.getQuestion(id);
    }

    public List<InterviewQuestion> getQuestionsByCategory(Category cat) {
        return questions.getByCategory(cat);
    }

    public List<InterviewQuestion> getQuestionsByDifficulty(Difficulty diff) {
        return questions.getByDifficulty(diff);
    }



}

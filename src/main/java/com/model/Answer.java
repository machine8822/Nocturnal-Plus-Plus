package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Answer {

    // Fields / Attributes
    

    // Optional code included with this answer.
    private String codeSnippet;
    // Main written explanation.
    private String explanation;
    // Community votes.
    private int upvoteCount;
    private int downvoteCount;
    // User who submitted the answer.
    private UUID authorId;
    // Creation timestamp.
    private LocalDateTime createdAt;
    // Discussion under this answer.
    private List<Comment> comments;


    // Constructor
   
    /**
     * Create a new answer with default vote counts and current timestamp.
     */
    public Answer(String codeSnippet, String explanation, UUID authorId) {
        this.codeSnippet = codeSnippet == null ? "" : codeSnippet;
        this.explanation = explanation == null ? "" : explanation;
        this.authorId = authorId;
        this.upvoteCount = 0;
        this.downvoteCount = 0;
        this.createdAt = LocalDateTime.now();
        this.comments = new ArrayList<>();
    }

    // Getters

    /**
     * Return the code snippet text for this answer.
     */
    public String getCodeSnippet() {
        return codeSnippet;
    }

    /**
     * Return the written explanation for this answer.
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * Return the number of upvotes on this answer.
     */
    public int getUpvoteCount() {
        return upvoteCount;
    }

    /**
     * Return the number of downvotes on this answer.
     */
    public int getDownvoteCount() {
        return downvoteCount;
    }

    /**
     * Return the ID of the user who authored this answer.
     */
    public UUID getAuthorId() {
        return authorId;
    }

    /**
     * Return when this answer was created.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Return the list of comments on this answer.
     */
    public List<Comment> getComments() {
        return comments;
    }


    // Setters
    

    /**
     * Update the code snippet text (null becomes empty string).
     */
    public void setCodeSnippet(String codeSnippet) {
        this.codeSnippet = codeSnippet == null ? "" : codeSnippet;
    }

    /**
     * Update the explanation text (null becomes empty string).
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation == null ? "" : explanation;
    }

    /**
     * Update the author ID.
     */
    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    /**
     * Set the creation timestamp.
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Replace comments list (null becomes empty list).
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments == null ? new ArrayList<>() : comments;
    }

    // Used by DataLoader when restoring from JSON
    public void setUpvoteCount(int upvoteCount) {
        this.upvoteCount = Math.max(0, upvoteCount);
    }

    // Used by DataLoader when restoring from JSON
    public void setDownvoteCount(int downvoteCount) {
        this.downvoteCount = Math.max(0, downvoteCount);
    }

    // Behavior / Actions

    /**
     * Return true when a non-blank code snippet exists.
     */
    public boolean hasCodeSnippet() {
        return !codeSnippet.isBlank();
    }

    /**
     * Return true when a non-blank explanation exists.
     */
    public boolean hasExplanation() {
        return !explanation.isBlank();
    }

    /**
     * Add one upvote.
     */
    public void upvote() {
        upvoteCount++;
    }

    /**
     * Add one downvote.
     */
    public void downvote() {
        downvoteCount++;
    }

    /**
     * Return net score (upvotes minus downvotes).
     */
    public int getVoteScore() {
        return upvoteCount - downvoteCount;
    }

    /**
     * Add a comment if it is not null.
     */
    public void addComment(Comment comment) {
        if (comment != null) {
            comments.add(comment);
        }
    }

    /**
     * Remove a comment and return whether it was removed.
     */
    public boolean removeComment(Comment comment) {
        return comments.remove(comment);
    }

    /**
     * Return total number of comments.
     */
    public int getCommentCount() {
        return comments.size();
    }
}

package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class InterviewQuestion {

    private UUID questionId;
    private String title;
    private String description;
    private Difficulty difficulty;
    private QuestionType questionType;
    private Category category;
    private String imageURL;
    private UUID authorId;
    private int totalAttempts;
    private int totalSuccesses;
    private LocalDateTime lastUpdated;
    
}

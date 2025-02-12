package com.lisandro.microservicioQuestions.enums;

public enum QuestionStatus {
    PENDING("Pending"),
    VALID("Valid"),
    INVALID("Invalid");

    private final String displayName;

    QuestionStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

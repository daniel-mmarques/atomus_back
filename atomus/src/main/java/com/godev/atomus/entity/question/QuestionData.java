package com.godev.atomus.entity.question;

public record QuestionData (Long id, String questionText) {
    public QuestionData(Question question) {
        this(question.getId(), question.getQuestionText());
    }
}
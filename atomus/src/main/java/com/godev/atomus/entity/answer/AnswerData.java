package com.godev.atomus.entity.answer;

public record AnswerData(Long id, int answerValue, Long questionId, Long userId) {
    public AnswerData(Answer answer) {
        this(answer.getId(), answer.getAnswerValue(), answer.getQuestionId(), answer.getUserId());
    }
}

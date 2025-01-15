package com.godev.atomus.entity.answer;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "answer")
@Entity(name = "Answer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int answerValue;
    private Long questionId;
    private Long userId;

    public Answer(AnswerData answerData) {
        this.answerValue = answerData.answerValue();
        this.questionId = answerData.questionId();
        this.userId = answerData.userId();
    }

    public void editAnswer(AnswerData answerData) {
        this.answerValue = answerData.answerValue();
    }
}

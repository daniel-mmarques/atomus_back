package com.godev.atomus.entity.question;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "question")
@Entity(name = "Question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionText;

    public Question(QuestionData questionData) {
        this.questionText = questionData.questionText();
    }

    public void editQuestion(QuestionData questionData) {
        this.questionText = questionData.questionText();
    }

}
package com.godev.atomus.service;

import com.godev.atomus.entity.question.Question;
import com.godev.atomus.entity.question.QuestionData;
import com.godev.atomus.entity.question.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class QuestionServiceTest {

    @Mock
    private QuestionRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {

        List<Question> questionList = new ArrayList<>();

        when(repository.findAll()).thenReturn(questionList);
        List<Question> retrievedQuestions = repository.findAll();

        assertEquals(questionList, retrievedQuestions);
    }

    @Test
    void testGetReferenceById() {

        Question question = new Question(new QuestionData(1L, ""));

        when(repository.getReferenceById(1L)).thenReturn(question);
        Question retrievedQuestion = repository.getReferenceById(1L);

        assertEquals(question, retrievedQuestion);
    }
}

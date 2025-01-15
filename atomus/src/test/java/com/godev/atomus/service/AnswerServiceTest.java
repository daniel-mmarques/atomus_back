package com.godev.atomus.service;

import com.godev.atomus.entity.answer.Answer;
import com.godev.atomus.entity.answer.AnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AnswerServiceTest {

    @Mock
    private AnswerRepository repository;

    @InjectMocks
    private AnswerService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAllByUserId() {

        List<Answer> answerList = new ArrayList<>();
        when(repository.findAllByUserId(1L)).thenReturn(answerList);
        List<Answer> retrievedAnswers = service.findAllByUserId(1L);

        assertEquals(answerList, retrievedAnswers);
    }

    @Test
    void testGetReferenceById() {

        Answer answer = new Answer();
        answer.setId(1L);
        when(repository.getReferenceById(1L)).thenReturn(answer);
        Answer retrievedAnswer = service.getReferenceById(1L);

        assertEquals(answer, retrievedAnswer);
    }
}

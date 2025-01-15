package com.godev.atomus.service;

import com.godev.atomus.entity.question.Question;
import com.godev.atomus.entity.question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;

    public void save(Question question) {
        repository.save(question);
    }

    public List<Question> findAll() {
        return repository.findAll();
    }

    public Question getReferenceById(Long questionId) { return repository.getReferenceById(questionId); }

    public void delete(long questionId) { delete(questionId); }
}

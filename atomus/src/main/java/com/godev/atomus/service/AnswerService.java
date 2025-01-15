package com.godev.atomus.service;

import com.godev.atomus.entity.answer.Answer;
import com.godev.atomus.entity.answer.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository repository;

    @Autowired
    UserService userService;

    public void save(Answer answer) {
        userService.saveProfile(answer.getUserId(), findAllByUserId(answer.getUserId()));
        repository.save(answer);
    }

    public List<Answer> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    public Answer getReferenceById(Long answerId) {
        return repository.getReferenceById(answerId);
    }

    public void delete(Long answerId) {
        delete(answerId);
    }
}

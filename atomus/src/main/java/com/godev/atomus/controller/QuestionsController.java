package com.godev.atomus.controller;

import com.godev.atomus.entity.question.Question;
import com.godev.atomus.entity.question.QuestionData;
import com.godev.atomus.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuario/perfil/questionario")
public class QuestionsController {

    @Autowired
    private QuestionService questionService;

    @PostMapping()
    public ResponseEntity cadastrar (@Valid @RequestBody QuestionData questionData, UriComponentsBuilder uriComponentsBuilder) {
        Question question = new Question(questionData);
        questionService.save(question);
        var uri = uriComponentsBuilder.path("/usuario/perfil/questionario/{id}").buildAndExpand(question.getId()).toUri();
        return ResponseEntity.created(uri).body(new QuestionData(question));
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> detalhar (Long questionId) {
        return ResponseEntity.ok(questionService.getReferenceById(questionId));
    }

    @GetMapping
    public ResponseEntity<List<Question>> listar () {
        return ResponseEntity.ok(questionService.findAll());
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity deletar (Long questionId) {
        questionService.delete(questionId);
        return ResponseEntity.noContent().build();
    }

}

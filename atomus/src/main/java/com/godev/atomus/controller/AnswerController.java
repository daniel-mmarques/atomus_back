package com.godev.atomus.controller;

import com.godev.atomus.entity.answer.Answer;
import com.godev.atomus.entity.answer.AnswerData;
import com.godev.atomus.service.AnswerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuario/perfil/respostas")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping()
    public ResponseEntity cadastrar (@Valid @RequestBody AnswerData answerData, UriComponentsBuilder uriComponentsBuilder) {
        Answer answer = new Answer(answerData);
        answerService.save(answer);
        var uri = uriComponentsBuilder.path("/usuario/perfil/respostas/{userId}").buildAndExpand(answer.getUserId()).toUri();
        return ResponseEntity.created(uri).body(new AnswerData(answer));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Answer>> listar (@PathVariable Long userId) {
        return ResponseEntity.ok(answerService.findAllByUserId(userId));
    }

    @GetMapping("/answer/{answerId}")
    public ResponseEntity<Answer> detalhar (@PathVariable Long answerId) {
        return ResponseEntity.ok(answerService.getReferenceById(answerId));
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity deletar (@PathVariable Long answerId) {
        answerService.delete(answerId);
        return ResponseEntity.noContent().build();
    }
}
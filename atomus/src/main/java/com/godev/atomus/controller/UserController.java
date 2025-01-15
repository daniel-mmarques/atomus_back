package com.godev.atomus.controller;

import com.godev.atomus.entity.ticker.TickerListData;
import com.godev.atomus.entity.user.User;
import com.godev.atomus.entity.user.UserData;
import com.godev.atomus.entity.user.UserEditData;
import com.godev.atomus.entity.user.UserRegisterData;
import com.godev.atomus.infra.security.SecurityConfigurations;
import com.godev.atomus.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityConfigurations securityConfigurations;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@Valid @RequestBody UserRegisterData userRegisterData, UriComponentsBuilder uriComponentsBuilder) {
        User user = new User(userRegisterData);
        userService.save(user);
        var uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserData(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar (@PathVariable Long id) {
        return ResponseEntity.ok(new UserData(userService.getReferenceById(id)));
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> listar () {
        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping
    @Transactional
    public ResponseEntity editar (@Valid @RequestBody UserEditData userEditData) {
        userService.getReferenceById(userEditData.id()).editUser(userEditData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar (@PathVariable Long id) {
        userService.getReferenceById(id).deleteUser();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/recomendacoes")
    public ResponseEntity<TickerListData> listarRecomendacoes (@PathVariable Long id) {
        return ResponseEntity.ok(userService.getRecommendations(id));
    }
}

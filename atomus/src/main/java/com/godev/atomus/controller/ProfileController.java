package com.godev.atomus.controller;

import com.godev.atomus.entity.profile.Profile;
import com.godev.atomus.entity.profile.ProfileData;
import com.godev.atomus.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/perfil")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping()
    public ResponseEntity cadastrar (@Valid @RequestBody ProfileData profileData, UriComponentsBuilder uriComponentsBuilder) {
        Profile profile = new Profile(profileData);
        profileService.save(profile);
        var uri = uriComponentsBuilder.path("/perfil/{profile}").buildAndExpand(profile.getProfile()).toUri();
        return ResponseEntity.created(uri).body(new ProfileData(profile));
    }

    @GetMapping("/{profile}")
    public ResponseEntity detalhar (@PathVariable String profile) {
        return ResponseEntity.ok(profileService.findByProfile(profile));
    }

    @GetMapping()
    public ResponseEntity<List<Profile>> listar () {
        return ResponseEntity.ok(profileService.findAll());
    }
}

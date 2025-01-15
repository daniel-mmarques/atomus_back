package com.godev.atomus.service;

import com.godev.atomus.entity.profile.Profile;
import com.godev.atomus.entity.profile.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repository;

    public void save(Profile profile) {
        repository.save(profile);
    }

    public Profile findByProfile(String profile) {
        return repository.findByProfile(profile);
    }

    public List<Profile> findAll(){
         return repository.findAll();
    }
}

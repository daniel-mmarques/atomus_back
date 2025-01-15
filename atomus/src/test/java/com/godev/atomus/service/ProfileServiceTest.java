package com.godev.atomus.service;

import com.godev.atomus.entity.profile.Profile;
import com.godev.atomus.entity.profile.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProfileServiceTest {

    @Mock
    private ProfileRepository repository;

    @InjectMocks
    private ProfileService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByProfile() {

        Profile profile = new Profile();
        profile.setId(1L);
        profile.setProfile("Test Profile");

        when(repository.findByProfile("Test Profile")).thenReturn(profile);
        Profile retrievedProfile = service.findByProfile("Test Profile");

        assertEquals(profile, retrievedProfile);
    }

    @Test
    void testFindAll() {

        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile(1L, "Profile 1"));
        profileList.add(new Profile(2L, "Profile 2"));

        when(repository.findAll()).thenReturn(profileList);
        List<Profile> retrievedProfiles = service.findAll();

        assertEquals(profileList, retrievedProfiles);
    }
}

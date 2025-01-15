package com.godev.atomus.entity.profile;

public record ProfileData (Long id, String profile) {
    public ProfileData(Profile profile) {
        this(profile.getId(), profile.getProfile());
    }
}

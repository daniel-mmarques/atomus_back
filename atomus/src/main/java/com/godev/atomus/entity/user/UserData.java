package com.godev.atomus.entity.user;

public record UserData(Long id, String name, String cpf, String email, Long profileId) {
    public UserData(User user) {
        this(user.getId(), user.getName(), user.getCpf(), user.getEmail(), user.getProfileId());
    }
}

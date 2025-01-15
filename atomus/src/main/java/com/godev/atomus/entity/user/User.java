package com.godev.atomus.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String password;
    private Long profileId;
    private boolean isActive;

    public User(UserRegisterData userRegisterData) {
        this.name = userRegisterData.name();
        this.cpf = userRegisterData.cpf();
        this.email = userRegisterData.email();
        this.password = userRegisterData.password();
        this.profileId = userRegisterData.profileId();
        this.isActive = true;
    }

    public void editUser(UserEditData userEditData) {
        this.name = userEditData.name();
        this.email = userEditData.email();
        this.profileId = userEditData.profileId();
    }

    public void editUser(UserPasswordEditData userPasswordEditData) {
        this.password = userPasswordEditData.password();
    }

    public void editUser(UserProfileEditData userProfileEditData) {
        this.profileId = userProfileEditData.profileId();
    }

    public void deleteUser() {
        this.isActive = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}

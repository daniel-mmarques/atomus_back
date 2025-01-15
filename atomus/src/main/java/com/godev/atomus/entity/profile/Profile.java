package com.godev.atomus.entity.profile;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "profile")
@Entity(name = "Profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String profile;

    public Profile(ProfileData profileData) {
        this.profile = profileData.profile();
    }
}

package com.packtpub.springboot2twitterclone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @NotNull
    private String userId;

    @JsonIgnore
    @NotNull
    private String password;

    @NotNull
    @Column(unique = true)
    private String screenName;

    @NotNull
    private Role role;

    private String bio;

    private String profileImage;

    @ElementCollection
    private Set<String> following;

}

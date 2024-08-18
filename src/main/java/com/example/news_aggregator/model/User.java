package com.example.news_aggregator.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;



@Entity
@Table(name = "app_user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @ElementCollection(targetClass = PreferencesEnum.class)
    @CollectionTable(name = "user_preferences", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "preferences")
    private Set<PreferencesEnum> preferences = new HashSet<>();

}

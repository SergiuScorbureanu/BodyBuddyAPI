package com.BodyBuddy.BodyBuddyAPI.models;

import com.BodyBuddy.BodyBuddyAPI.models.abstracts.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(name = "trainings")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Training extends AbstractEntity {

    @OneToMany(mappedBy = "training")
    @Column(name = "exercises", nullable = false)
    private Set<Exercise> exercises; // Set este o colectie neordonata de obiecte unice

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "backgroundImg")
    private String backgroundImg; // URL type

    @Column(name = "color")
    private String color;

}

package com.BodyBuddy.BodyBuddyAPI.models;

import com.BodyBuddy.BodyBuddyAPI.models.abstracts.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "exercises")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Exercise extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "training_id") // nullable = false
    private Training training;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "setNumber", nullable = false)
    private Integer setNumber;

    @Column(name = "repNumber", nullable = false)
    private Integer repNumber;

    @Column(name = "pauseTime", nullable = false)
    private Integer pauseTime;

    @Column(name = "image")
    private String image; //URL type
}

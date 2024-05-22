package com.BodyBuddy.BodyBuddyAPI.models.abstracts;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@MappedSuperclass // marcheaza o clasa ca fiind superioara pentru clasele mostenitoare
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public abstract class BaseEntity {

    @Id
    @Column(name = "id", columnDefinition = "UUID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDate creationDate = LocalDate.now();

    private LocalDate updatedDate = LocalDate.now();
}

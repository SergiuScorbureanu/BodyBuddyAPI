package com.BodyBuddy.BodyBuddyAPI.models;

import com.BodyBuddy.BodyBuddyAPI.models.abstracts.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "foods")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Food extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "carbohydrates ")
    private Double carbohydrates ;

    @Column(name = "fat")
    private Double fat;

    @Column(name = "protein")
    private Double protein;

    @Column(name = "quantity")
    private Double quantity;
}

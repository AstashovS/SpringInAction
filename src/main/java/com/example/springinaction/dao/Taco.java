package com.example.springinaction.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {
  @Id private Long id;
  private Date createdAt = new Date();

  @NotNull
  @Size(min = 5, message = "Name must be at least 5 characters long")
  private String name;

  @ManyToMany
  @Size(min = 1, message = " You need to choose at least 1 ingredient")
  private List<Ingredient> ingredients;

  public void addIngredient(Ingredient ingredient) {
    this.ingredients.add(ingredient);
  }
}

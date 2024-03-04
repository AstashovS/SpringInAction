package com.example.springinaction.repository;

import com.example.springinaction.dao.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {}

package com.example.animalsapi.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    List<Animal> findBySpecies(String species);

    @Query("SELECT a FROM Animal a WHERE a.name LIKE %?1%")
    List<Animal> findByNameContaining(String name);
}

package com.example.animalsapi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public String getAllAnimals(Model model) {
        List<Animal> animals = animalService.getAllAnimals();
        model.addAttribute("animalList", animals);
        return "animal-list";
    }

    @GetMapping("/{id}")
    public String getAnimalById(@PathVariable int id, Model model) {
        return animalService.getAnimalById(id)
                .map(animal -> {
                    model.addAttribute("animal", animal);
                    return "animal-details";
                })
                .orElse("error/404");
    }

    @PostMapping
    public String addAnimal(@ModelAttribute Animal animal) {
        animalService.addAnimal(animal);
        return "redirect:/api/animals";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        return animalService.getAnimalById(id)
                .map(animal -> {
                    model.addAttribute("animal", animal);
                    return "animal-update";
                })
                .orElse("error/404");
    }

    @PostMapping("/update")
    public String updateAnimal(@ModelAttribute Animal animal) {
        animalService.updateAnimal(animal.getAnimalId(), animal);
        return "redirect:/api/animals";
    }

    @GetMapping("/delete/{id}")
    public String deleteAnimal(@PathVariable int id) {
        animalService.deleteAnimal(id);
        return "redirect:/api/animals";
    }

    @GetMapping("/species/{species}")
    public String getAnimalsBySpecies(@PathVariable String species, Model model) {
        List<Animal> animals = animalService.getAnimalsBySpecies(species);
        model.addAttribute("animalList", animals);
        return "animal-list";
    }

    @GetMapping("/search")
    public String searchAnimals(@RequestParam String name, Model model) {
        List<Animal> animals = animalService.searchAnimalsByName(name);
        model.addAttribute("animalList", animals);
        return "animal-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("animal", new Animal());
        return "animal-create";
    }

}

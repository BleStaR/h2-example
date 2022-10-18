package ru.blestar.example.service;

import ru.blestar.example.model.Man;
import ru.blestar.example.repository.ManRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ManService {

    private final ManRepository manRepository;

    public ManService(ManRepository manRepository) {
        this.manRepository = manRepository;
    }

    public List<Man> findAll() {
        return manRepository.findAll();
    }

    public List<Man> findAllOlderThan(int ageThreshold) {
        return manRepository.findAll().stream()
                .filter(man -> man.getAge() != null && man.getAge() > ageThreshold)
                .collect(Collectors.toList());
    }
}

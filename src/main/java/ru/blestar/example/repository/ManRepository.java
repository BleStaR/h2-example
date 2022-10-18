package ru.blestar.example.repository;

import ru.blestar.example.model.Man;

import java.util.List;

public interface ManRepository {

    List<Man> findAll();
}

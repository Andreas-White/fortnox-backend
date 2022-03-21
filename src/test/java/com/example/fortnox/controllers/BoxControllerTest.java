package com.example.fortnox.controllers;

import com.example.fortnox.entities.Box;
import com.example.fortnox.repositories.BoxRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BoxControllerTest {

    private BoxRepository repository;
    private BoxController controller;

    @BeforeAll
    void init() {
        repository = new BoxRepository();
        Connection connection = repository.connect();
        controller = new BoxController();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @AfterAll
    void teardown() {
        Connection connection = repository.connect();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testControllerGetBoxes() {
        Box box = new Box("Username", 22.3, "#FFFFFF", "Sweden");
        controller.addBox(box);
        Box savedBox = repository.getBoxByName("Username");
        assertEquals(box, savedBox, "The boxes' color are equal");
        repository.deleteBoxByName("Username");
    }

    @Test
    void testControllerAddBox() {
        Integer count = repository.getNumberOfBoxes();
        List<Box> boxList = controller.getBoxes();
        assertEquals(boxList.size(), count, "They are equal");
    }
}
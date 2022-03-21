package com.example.fortnox.services;

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
class BoxServiceTest {

    private BoxRepository repository;
    private BoxService service;

    @BeforeAll
    void init() {
        repository = new BoxRepository();
        Connection connection = repository.connect();
        service = new BoxService();
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
    void testServiceSaveBox() {
        Box box = new Box("Username",22.3,"#FFFFFF","Sweden");
        service.saveBox(box);
        Box savedBox = repository.getBoxByName("Username");
        assertEquals(box,savedBox,"The boxes' color are equal");
        repository.deleteBoxByName("Username");
    }

    @Test
    void testServiceGetListOfBoxes() {
        Integer count = repository.getNumberOfBoxes();
        List<Box> boxList = service.getListOfBoxes();
        assertEquals(boxList.size(), count,  "They are equal");
    }
}
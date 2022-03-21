package com.example.fortnox.repositories;

import com.example.fortnox.entities.Box;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BoxRepositoryQueriesTest {

    private BoxRepository repository;

    @BeforeAll
    void init() {
        repository = new BoxRepository();
        Connection connection = repository.connect();
        try {
            connection.setAutoCommit(false); // so any operation in this test will be discarded.
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
    void testSaveSuccessfully() {
        Integer boxId = repository.getMaxId() + 1;
        Box box = new Box(boxId, "Username", 22.3, "#FFFFFF", "Sweden");
        repository.save(box);
        Box savedBox = repository.getBoxByName("Username");
        assertEquals("Sweden", savedBox.getCountry(), "Names are equal");
        repository.deleteBoxByName("Username");
    }

    @Test
    void testSaveThrowsNullPointerException() {
        Box box = new Box();
        NullPointerException throwables = assertThrows(NullPointerException.class, () -> repository.save(box));
        assertEquals(
                "Cannot invoke \"java.lang.Integer.intValue()\" because the return value of " +
                        "\"com.example.fortnox.entities.Box.getId()\" is null", throwables.getMessage());
    }

    @Test
    void testGetAllBoxesSuccessfully() {
        Integer count = repository.getNumberOfBoxes();
        List<Box> boxList = repository.getAllBoxes();
        assertEquals(boxList.size(), count, "They are equal");
    }

    @Test
    void testGetMaxIdSuccessfully() {
        Integer max = repository.getMaxId();
        List<Box> boxList = repository.getAllBoxes();
        Integer trackMax = 0;
        for (Box box : boxList) {
            if (box.getId() >= trackMax)
                trackMax = box.getId();
        }
        assertEquals(max, trackMax, "They are equal");
    }
}
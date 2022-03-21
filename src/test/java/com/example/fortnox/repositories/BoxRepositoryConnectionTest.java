package com.example.fortnox.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BoxRepositoryConnectionTest {

    @Test
    void getConnectionTest() {
        BoxRepository repository = new BoxRepository();

        Connection connection = repository.connect();
        assertNotNull(connection, "The connection is successful");
    }
}

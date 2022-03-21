package com.example.fortnox;

import com.example.fortnox.controllers.BoxController;
import com.example.fortnox.repositories.BoxRepository;
import com.example.fortnox.services.BoxService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FortnoxApplicationTests {

    @Test
    void contextLoads() {
        BoxRepository repository = new BoxRepository();
        BoxService service = new BoxService();
        BoxController controller = new BoxController();
        try {
            assertThat(repository).isNotNull();
            assertThat(service).isNotNull();
            assertThat(controller).isNotNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

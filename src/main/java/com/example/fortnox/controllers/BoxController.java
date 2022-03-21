package com.example.fortnox.controllers;

import com.example.fortnox.entities.Box;
import com.example.fortnox.services.BoxService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoxController {

    private final BoxService service = new BoxService();

    /**
     * Delivers a list of boxes when the api is called. The @CrossOrigin annotation was used to enable
     * cross-origin resource sharing
     *
     * @return list of boxes in a json format
     */
    @CrossOrigin
    @GetMapping("/listboxes")
    public List<Box> getBoxes() {
        return service.getListOfBoxes();
    }

    /**
     * When a post request is made to this endpoint it calls the saveBox() method from the service class
     * in order to be saved in the database.
     *
     * @param box
     */
    @CrossOrigin
    @PostMapping("/addbox")
    public void addBox(@RequestBody Box box) {
        service.saveBox(box);
    }
}

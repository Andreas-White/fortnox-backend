package com.example.fortnox.services;


import com.example.fortnox.entities.Box;
import com.example.fortnox.repositories.BoxRepository;

import java.util.List;

public class BoxService {

    private final BoxRepository repository = new BoxRepository();

    /**
     * Saves the specified box after assigning an incremented id. Also checks if there is an id equal or smaller
     * than the max id in the database and then assigns a new one.
     *
     * @param box the box object to be saved
     */
    public void saveBox(Box box) {
        if (box.getId() == null || box.getId() <= repository.getMaxId()) {
            Integer newId = repository.getMaxId() + 1;
            box.setId(newId);
            repository.save(box);
        } else
            repository.save(box);
    }

    /**
     * Retrieves all the boxes from the database by calling the method from the repository class
     *
     * @return a List of boxes
     */
    public List<Box> getListOfBoxes() {
        return repository.getAllBoxes();
    }
}

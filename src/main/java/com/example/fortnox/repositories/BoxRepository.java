package com.example.fortnox.repositories;


import com.example.fortnox.entities.Box;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoxRepository {

    /**
     * Initializes a connection to the database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/post", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Saves the specified box in database
     *
     * @param box a box object
     */
    public void save(Box box) {
        try {
            String sql = "INSERT INTO `boxes`(`id`, `name`, `weight`, `color`, `country`) VALUES (?,?,?,?,?)";

            PreparedStatement preparedStatement = connect().prepareStatement(sql);
            preparedStatement.setInt(1, box.getId());
            preparedStatement.setString(2, box.getName());
            preparedStatement.setDouble(3, box.getWeight());
            preparedStatement.setString(4, box.getColor());
            preparedStatement.setString(5, box.getCountry());

            preparedStatement.execute();

            preparedStatement.close();
            connect().close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Retrieves all the boxes from the table
     *
     * @return a List of boxes
     */
    public List<Box> getAllBoxes() {

        List<Box> boxes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM `boxes`";

            PreparedStatement preparedStatement = connect().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Double weight = resultSet.getDouble("weight");
                String color = resultSet.getString("color");
                String country = resultSet.getString("country");

                Box box = new Box(id, name, weight, color, country);
                boxes.add(box);
            }

            preparedStatement.close();
            connect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boxes;
    }

    /**
     * Gets the highest id value from the table (used to autogenerate ids)
     *
     * @return an Integer for the highest id
     */
    public Integer getMaxId() {
        int maxId = 0;
        try {
            String sql = "SELECT max(id) AS max_id FROM `boxes`";
            PreparedStatement preparedStatement = connect().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                maxId = resultSet.getInt("max_id");
            }

            preparedStatement.close();
            connect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maxId;
    }

    /**
     * Retrieves a box with the specified receiver's name if it exists (for testing purposes)
     *
     * @param name a String for the box's receiver name
     * @return a box object
     */
    public Box getBoxByName(String name) {
        Box box = new Box();
        try {
            String sql = "SELECT * FROM `boxes` WHERE  `name`=?";

            PreparedStatement preparedStatement = connect().prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                box.setId(rs.getInt("id"));
                box.setName(rs.getString("name"));
                box.setWeight(rs.getDouble("weight"));
                box.setColor(rs.getString("color"));
                box.setCountry(rs.getString("country"));
            }

            preparedStatement.close();
            connect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return box;
    }

    /**
     * Deletes a box with the specified receiver's name (for testing purposes)
     *
     * @param name a String for the box's receiver name
     */
    public void deleteBoxByName(String name) {
        try {
            String sql = "DELETE FROM `boxes` WHERE  `name`=?";

            PreparedStatement preparedStatement = connect().prepareStatement(sql);
            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getNumberOfBoxes() {
        int count = 0;
        try {
            String sql = "SELECT count(*) AS max_id FROM `boxes`";
            PreparedStatement preparedStatement = connect().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                count = resultSet.getInt(1);

            preparedStatement.close();
            connect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
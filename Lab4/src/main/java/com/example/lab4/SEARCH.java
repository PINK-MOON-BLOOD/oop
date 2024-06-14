package com.example.lab4;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Optional;

public class SEARCH {
    public static void SEARCH(ArrayList<Puppet> puppe) {

        Dialog<Puppet> dialog = new Dialog<>();
        dialog.setTitle("Search MicroObject");

        TextField nameField = new TextField();
        TextField damageField = new TextField();
        TextField healthField = new TextField();
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");


        nameField.setPromptText("Name");
        comboBox.setPromptText("Level");
        damageField.setPromptText("Damage");
        healthField.setPromptText("HP");

        GridPane grid = new GridPane();
        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("State"), 0, 1);
        ;
        grid.add(new Label("Level:"), 0, 2);
        grid.add(comboBox, 1, 2);
        grid.add(new Label("Damage:"), 0, 3);
        grid.add(damageField, 1, 3);
        grid.add(new Label(" HP:"), 0, 5);
        grid.add(healthField, 1, 5);

        dialog.getDialogPane().setContent(grid);

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                try {
                    String name = nameField.getText();
                    int level = comboBox.getValue() == null ? 0 : Integer.parseInt(String.valueOf(comboBox.getValue()));
                    double damage = damageField.getText().isEmpty() ? 0 : Double.parseDouble(damageField.getText());
                    int health = healthField.getText().isEmpty() ? 0 : Integer.parseInt(healthField.getText());
                    boolean red = false;
                    boolean blue = false;
                    boolean netral = true;

                    for (Puppet puppet1 : puppe) {
                        if (puppet1.getName().equals(name) && puppet1.getLevel() == level && puppet1.getdamage() == damage && puppet1.getHealth() == health) {

                            System.out.println(" Координати по X :" + puppet1.getxPos());
                            System.out.println("Координати по Y :" + puppet1.getyPos());
                            if (puppet1.isRed() == true) {
                                System.out.println("Об'єкт належить до Червоної бази.");
                            }
                            if (puppet1.isBlue() == true) {
                                System.out.println("Об'єкт належить до Cиньої бази бази.");
                            }
                            if (puppet1.isNetral() == true) {
                                System.out.println("Об'єкт не належить до Червоної та Cиньої бази.");
                            }
                        }
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Default preset");
                }
            }
            return null;
        });

        Optional<Puppet> result = dialog.showAndWait();
        //result.ifPresent(puppe -> System.out.println("Created"));


    }
}

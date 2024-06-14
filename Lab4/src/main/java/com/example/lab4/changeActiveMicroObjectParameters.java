package com.example.lab4;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import static com.example.lab4.HelloApplication.puppe;

public class changeActiveMicroObjectParameters {
    public static void changeActiveMicroObjectParameters() {
        for (Puppet puppet : puppe) {
            if (puppet.isActive()) {
                Dialog<Puppet> dialog = new Dialog<>();
                dialog.setTitle("Change MicroObject Parameters");
                dialog.setHeaderText("Enter new parameters:");

                //TextField levelField = new TextField();
                ComboBox comboBox = new ComboBox();
                comboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
                TextField damageField = new TextField();
                TextField healthField = new TextField();
                TextField xPosField = new TextField();
                TextField yPosField = new TextField();

                comboBox.setPromptText("New Level");
                damageField.setPromptText("New Damage");
                healthField.setPromptText("New HP");
                xPosField.setPromptText("New X Position");
                yPosField.setPromptText("New Y Position");

                GridPane grid = new GridPane();
                grid.add(new Label("Level:"), 0, 0);
                grid.add(comboBox, 1, 0);
                grid.add(new Label("Damage:"), 0, 1);
                grid.add(damageField, 1, 1);
                grid.add(new Label("HP:"), 0, 2);
                grid.add(healthField, 1, 2);
                grid.add(new Label("X Position:"), 0, 3);
                grid.add(xPosField, 1, 3);
                grid.add(new Label("Y Position:"), 0, 4);
                grid.add(yPosField, 1, 4);

                dialog.getDialogPane().setContent(grid);

                ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == okButtonType) {
                        try {
                            int newLevel = Integer.parseInt(comboBox.getValue().toString());
                            double newDamage = Double.parseDouble(damageField.getText());
                            int newHealth = Integer.parseInt(healthField.getText());
                            float newXPos = Float.parseFloat(xPosField.getText());
                            float newYPos = Float.parseFloat(yPosField.getText());

                            puppet.setLevel(newLevel);
                            puppet.setDamage(newDamage);
                            puppet.setHealth(newHealth);
                            puppet.setPosition(newXPos, newYPos);
                            System.out.println("was created" + puppet.toString());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Parameters remain unchanged.");
                        }
                    }
                    return null;
                });

                dialog.showAndWait();
            }
        }


    }
}

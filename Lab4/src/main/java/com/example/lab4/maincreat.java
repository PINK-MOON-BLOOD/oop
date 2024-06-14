//package com.example.lab4;
//
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//
//import java.util.Optional;
//
//import static com.example.lab4.HelloApplication.group;
//
//public class maincreat { // Class names should start with an uppercase letter
//
//    public void createMicroObjectDialog() {
//
//
//
//        Dialog<Object> dialog = new Dialog<>();
//        dialog.setTitle("Create MicroObject");
//        dialog.setHeaderText("Enter MicroObject details:");
//
//        TextField nameField = new TextField();
//        TextField levelField = new TextField();
//        TextField damageField = new TextField();
//        CheckBox isAccumulatedUltCheckBox = new CheckBox("Is Accumulated Ult");
//        TextField healthField = new TextField();
//        TextField xPosField = new TextField();
//        TextField yPosField = new TextField();
//        RadioButton radioButton1 = new RadioButton("Knight");
//        RadioButton radioButton2 = new RadioButton("Paladin");
//        RadioButton radioButton3 = new RadioButton("Wizard");
//
//        nameField.setPromptText("Name");
//        levelField.setPromptText("Level (optional)");
//        damageField.setPromptText("Weapon");
//        healthField.setPromptText("Shooting Experience");
//        xPosField.setPromptText("X Position");
//        yPosField.setPromptText("Y Position");
//
//        GridPane grid = new GridPane();
//        grid.add(new Label("Name:"), 0, 0);
//        grid.add(nameField, 1, 0);
//        grid.add(new Label("Level:"), 0, 1);
//        grid.add(levelField, 1, 1);
//        grid.add(new Label("Damage:"), 0, 2);
//        grid.add(damageField, 1, 2);
//        grid.add(isAccumulatedUltCheckBox, 0, 3);
//        grid.add(radioButton1, 0, 4);
//        grid.add(radioButton2, 1, 4);
//        grid.add(radioButton3, 2, 4);
//        grid.add(new Label("Shooting Experience:"), 0, 5);
//        grid.add(healthField, 1, 5);
//        grid.add(new Label("X Position:"), 0, 6);
//        grid.add(xPosField, 1, 6);
//        grid.add(new Label("Y Position:"), 0, 7);
//        grid.add(yPosField, 1, 7);
//
//        dialog.getDialogPane().setContent(grid);
//
//        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
//        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
//
//        dialog.setResultConverter(dialogButton -> {
//            if (dialogButton == okButtonType) {
//                try {
//                    String name = nameField.getText();
//                    int level = levelField.getText().isEmpty() ? 0 : (int) Double.parseDouble(levelField.getText());
//                    double damage =damageField.getText().isEmpty() ? 0 : (int) Double.parseDouble(damageField.getText());
//                    int health = healthField.getText().isEmpty() ? 0 : (int) Double.parseDouble(healthField.getText());
//                    float xPos = Float.parseFloat(xPosField.getText());
//                    float yPos = Float.parseFloat(yPosField.getText());
//
//                    if (radioButton1.isSelected()) {
//                        Puppet knight = new Puppet(name, level, damage, health, xPos, yPos); // Assuming level, damage, health are defaults
//                        HelloApplication.puppe.add(knight);
//                        return knight;
//                    } else if (radioButton2.isSelected()) {
//                        Puppet_of_the_future paladin = new Puppet_of_the_future(name, level, damage, health, xPos, yPos); // Assuming Puppet_of_the_future constructor doesn't require parameters
//                        HelloApplication.puppe.add(paladin);
//                        return paladin;
//                    } else if (radioButton3.isSelected()) {
//                        Lead_of_Parade wizard = new Lead_of_Parade(name, level, damage, health, xPos, yPos); // Assuming Lead_of_Parade constructor doesn't require parameters
//                        HelloApplication.puppe.add(wizard);
//                        return wizard;
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid input. Default preset");
//                }
//            }
//            return null; // Need to return something in all cases
//        });
//
//        Optional<Object> result = dialog.showAndWait();
//
//        result.ifPresent(microObject -> System.out.println("Created"));
//
//
//    }
//
//}

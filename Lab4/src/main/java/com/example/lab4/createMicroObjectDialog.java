package com.example.lab4;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

import static com.example.lab4.HelloApplication.puppe;

public class createMicroObjectDialog {
    public static void createMicroObjectDialog(Puppet[] puppet0, Puppet_of_the_future[] puppet1, Lead_of_Parade[] puppet2) {

        Dialog<Puppet> dialog = new Dialog<>();
        dialog.setTitle("Create MicroObject");
        dialog.setHeaderText("Enter MicroObject details:");

        TextField nameField = new TextField();
        //TextField stateFeild = new TextField();
        TextField damageField = new TextField();

        TextField healthField = new TextField();
        TextField xPosField = new TextField();
        TextField yPosField = new TextField();
        RadioButton radioButton1 = new RadioButton("Puppet");
        RadioButton radioButton2 = new RadioButton("PuppetOFTheFuture");
        RadioButton radioButton3 = new RadioButton("Lead_of_Parade");

        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

//        ComboBox comboBox1 = new ComboBox();
//        comboBox1.getItems().addAll("RedTeam", "BlueTeam", "FreePuppet");

        nameField.setPromptText("Name");
        // stateFeild.setPromptText("Стан об'єкта");
        comboBox.setPromptText("Level");
        //comboBox1.setPromptText("RASA");
        damageField.setPromptText("Level");
        healthField.setPromptText("HP");
        xPosField.setPromptText("X Position");
        yPosField.setPromptText("Y Position");

        GridPane grid = new GridPane();
        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("State"), 0, 1);
        // grid.add(stateFeild,1,1);
        grid.add(new Label("Level:"), 0, 2);
        grid.add(comboBox, 1, 2);
        grid.add(new Label("Damage:"), 0, 3);
        grid.add(damageField, 1, 3);

        grid.add(radioButton1, 0, 4);
        grid.add(radioButton2, 1, 4);
        grid.add(radioButton3, 2, 4);
        grid.add(new Label(" HP:"), 0, 5);
        grid.add(healthField, 1, 5);
        grid.add(new Label("X Position:"), 0, 6);
        grid.add(xPosField, 1, 6);
        grid.add(new Label("Y Position:"), 0, 7);
        grid.add(yPosField, 1, 7);
        // grid.add(new Label("RASA:"),0,8);
        // grid.add(comboBox1,1,8);

        dialog.getDialogPane().setContent(grid);

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                try {
                    String name = nameField.getText();
                    // String stateObj = stateFeild.getText();
                    int level = comboBox.getValue() == null ? 0 : Integer.parseInt(String.valueOf(comboBox.getValue()));
                    double damage = damageField.getText().isEmpty() ? 0 : Double.parseDouble(damageField.getText());
                    int health = healthField.getText().isEmpty() ? 0 : Integer.parseInt(healthField.getText());
                    float xPos = Float.parseFloat(xPosField.getText());
                    float yPos = Float.parseFloat(yPosField.getText());
                    boolean red = false;
                    boolean blue = false;
                    boolean netral = true;


                    if (radioButton1.isSelected()) {
                        for (int i = 3; i < puppet0.length; ++i) {
                            puppet0[i] = new Puppet(name, level, damage, health, xPos, yPos, red, blue, netral);
                            puppet0[i].resurrect();
                            puppe.add(puppet0[i]);
                            System.out.println("was created" + puppet0[i].toString());
                            break;
                        }

                    } else if (radioButton2.isSelected()) {
                        for (int i = 3; i < puppet1.length; ++i) {
                            puppet1[i] = new Puppet_of_the_future(name, level, damage, health, xPos, yPos, red, blue, netral);
                            puppet1[i].resurrectt();
                            puppe.add(puppet1[i]);
                            System.out.println("was created" + puppet1[i].toString());
                            break;

                        }


                    } else if (radioButton3.isSelected()) {
                        for (int i = 3; i < puppet2.length; ++i) {
                            puppet2[i] = new Lead_of_Parade(name, level, damage, health, xPos, yPos, red, blue, netral);
                            puppet2[i].resurrecttt();
                            puppe.add(puppet2[i]);
                            System.out.println("was created" + puppet2[i].toString() + ".");
                            break;
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

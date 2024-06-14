package com.example.lab4;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Optional;

public class Counter {
    public static  void Counter(ArrayList<Puppet> puppe) {
        Dialog<Puppet> dialog = new Dialog<>();
        dialog.setTitle("COUNTER MICRO");

        CheckBox checkBox0 = new CheckBox();
        CheckBox checkBox1 = new CheckBox();
        CheckBox checkBox2 = new CheckBox();
        CheckBox checkBox3 = new CheckBox();

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("ActiveMicro:"), 0, 0);
        gridPane.add(checkBox0, 3, 0);
        gridPane.add(new Label("NonactiveMicro:"), 0, 2);
        gridPane.add(checkBox1, 3, 2);
        gridPane.add(new Label("REd_BAse:"), 0, 3);
        gridPane.add(checkBox2, 3, 3);
        gridPane.add(new Label("BLUE_BAse:"), 0, 4);
        gridPane.add(checkBox3, 3, 4);
        dialog.getDialogPane().setContent(gridPane);

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                try {
                    //int NonactiveMicro = 0;
                    // int REd_BAse = 0;
                    // int BLUE_BAse = 0;

                    if (checkBox0.isSelected()) {
                        long activeMicroObjects = puppe.stream()
                                .filter(Puppet::isActive)
                                .filter(p -> !p.isDeleted())
                                .count();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Active Micro Objects");
                        alert.setHeaderText(null);
                        alert.setContentText("Number of Active Micro Objects: " + activeMicroObjects);
                        alert.showAndWait();
                    }

                    if (checkBox1.isSelected()) {
                        long NonactiveMicro = puppe.stream()
                                .filter(p -> !p.isActive() && !p.isDeleted())
                                .count();

                        Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                        alert0.setTitle("Active Micro Objects");
                        alert0.setHeaderText(null);
                        alert0.setContentText("Number of NO-Active Micro Objects: " + NonactiveMicro);
                        alert0.showAndWait();
                    }

                    if (checkBox2.isSelected()) {
                        long REd_BAse = puppe.stream()
                                .filter(Puppet::isRed )
                                .filter(p -> !p.isDeleted())
                                .count();

                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Active Micro Objects");
                        alert1.setHeaderText(null);
                        alert1.setContentText("Number of RED-TEAM Micro Objects: " + REd_BAse);
                        alert1.showAndWait();
                    }
                    if (checkBox3.isSelected()) {
                        long BLUE_BAse = puppe.stream()
                                .filter(Puppet::isBlue)
                                .filter(p -> !p.isDeleted())
                                .count();


                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Active Micro Objects");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Number of BLUE-TEAM Micro Objects: " + BLUE_BAse);
                        alert2.showAndWait();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            return null;
        });
        Optional<Puppet> result = dialog.showAndWait();


    }
}

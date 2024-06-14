package com.example.lab4;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Optional;

public class ChangeMicro {
    public static void ChangeMicro(ArrayList<Puppet> puppe) {

        Dialog<Puppet> dialogChange = new Dialog<>();
        dialogChange.setTitle("ChangeMicro");


        Label label0 = new Label("Factory_Krat:");
        Label label1 = new Label("Factory_Tark:");
        Label label2 = new Label("Free_Puppet:");

        RadioButton radioButton0 = new RadioButton();
        RadioButton radioButton1 = new RadioButton();
        RadioButton radioButton2 = new RadioButton();

        GridPane gridPane = new GridPane();

        gridPane.add(label0, 0, 0);
        gridPane.add(radioButton0, 2, 0);
        gridPane.add(label1, 0, 1);
        gridPane.add(radioButton1, 2, 1);
        gridPane.add(label2, 0, 2);
        gridPane.add(radioButton2, 2, 2);

        dialogChange.getDialogPane().setContent(gridPane);

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialogChange.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        dialogChange.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                try {

                    if (radioButton0.isSelected()) {


                        /// Для МАСИВУ
                        for (int i = 0; i < puppe.size(); i++) { // ЗМІНЮЄ НА ЧЕРВОНИЙ

                            if (puppe.get(i) instanceof Lead_of_Parade) {
                                if ((puppe.get(i).isActive() && puppe.get(i).isNetral()) || (puppe.get(i).isActive() && puppe.get(i).isBlue())) {

                                    puppe.get(i).RecRED();
                                    puppe.get(i).TextRED();
                                    puppe.get(i).setRed(true);
                                    puppe.get(i).setNetral(false);
                                    puppe.get(i).setBlue(false);
                                    System.out.println(puppe.get(i));
                                    break;
                                }
                            }

                        }

                        for (int i = 0; i < puppe.size(); i++) {

                            if (puppe.get(i) instanceof Puppet_of_the_future) {
                                if ((puppe.get(i).isActive() && puppe.get(i).isNetral()) || (puppe.get(i).isActive() && puppe.get(i).isBlue())) {
                                    puppe.get(i).RecRED();
                                    puppe.get(i).TextRED();
                                    puppe.get(i).setRed(true);
                                    puppe.get(i).setNetral(false);
                                    puppe.get(i).setBlue(false);
                                    System.out.println(puppe.get(i));
                                    break;
                                }
                            }
                        }

                        for (int i = 0; i < puppe.size(); i++) {

                            if (puppe.get(i) instanceof Puppet) {
                                if ((puppe.get(i).isActive() && puppe.get(i).isNetral()) || (puppe.get(i).isActive() && puppe.get(i).isBlue())) {
                                    puppe.get(i).RecRED();
                                    puppe.get(i).TextRED();
                                    puppe.get(i).setRed(true);
                                    puppe.get(i).setNetral(false);
                                    puppe.get(i).setBlue(false);
                                    System.out.println(puppe.get(i));
                                    break;
                                }
                            }
                        }
                        //
                    } else if (radioButton1.isSelected()) {// ЗМІНЮЄ НА СИНІЙ

                        for (int i = 0; i < puppe.size(); i++) {

                            if (puppe.get(i) instanceof Lead_of_Parade) {
                                if ((puppe.get(i).isActive() && puppe.get(i).isNetral()) || (puppe.get(i).isActive() && puppe.get(i).isRed())) {
                                    puppe.get(i).RecBLUE();
                                    puppe.get(i).TextBlue();
                                    puppe.get(i).setRed(false);
                                    puppe.get(i).setNetral(false);
                                    puppe.get(i).setBlue(true);
                                    System.out.println(puppe.get(i));
                                    break;
                                }
                            }

                        }

                        for (int i = 0; i < puppe.size(); i++) {

                            if (puppe.get(i) instanceof Puppet_of_the_future) {
                                if ((puppe.get(i).isActive() && puppe.get(i).isNetral()) || (puppe.get(i).isActive() && puppe.get(i).isRed())) {
                                    puppe.get(i).RecBLUE();
                                    puppe.get(i).TextBlue();
                                    puppe.get(i).setRed(false);
                                    puppe.get(i).setNetral(false);
                                    puppe.get(i).setBlue(true);
                                    System.out.println(puppe.get(i));
                                    break;
                                }
                            }
                        }

                        for (int i = 0; i < puppe.size(); i++) {

                            if (puppe.get(i) instanceof Puppet) {
                                if ((puppe.get(i).isActive() && puppe.get(i).isNetral()) || (puppe.get(i).isActive() && puppe.get(i).isRed())) {
                                    puppe.get(i).RecBLUE();
                                    puppe.get(i).TextBlue();
                                    puppe.get(i).setRed(false);
                                    puppe.get(i).setNetral(false);
                                    puppe.get(i).setBlue(true);
                                    System.out.println(puppe.get(i));
                                    break;
                                }
                            }
                        }

                    } else if (radioButton2.isSelected()) {// ЗМІНЮЄ НА НЕЙТРАЛЬНИЙ

                        for (int i = 0; i < puppe.size(); i++) {

                            if (puppe.get(i) instanceof Lead_of_Parade) {
                                if ((puppe.get(i).isActive() && puppe.get(i).isBlue()) || (puppe.get(i).isActive() && puppe.get(i).isRed())) {
                                    puppe.get(i).RecGray();
                                    puppe.get(i).OriginalText();
                                    puppe.get(i).setRed(false);
                                    puppe.get(i).setNetral(true);
                                    puppe.get(i).setBlue(false);
                                    System.out.println(puppe.get(i));
                                    break;
                                }
                            }

                        }

                        for (int i = 0; i < puppe.size(); i++) {

                            if (puppe.get(i) instanceof Puppet_of_the_future) {
                                if ((puppe.get(i).isActive() && puppe.get(i).isBlue()) || (puppe.get(i).isActive() && puppe.get(i).isRed())) {
                                    puppe.get(i).RecGray();
                                    puppe.get(i).OriginalText();
                                    puppe.get(i).setRed(false);
                                    puppe.get(i).setNetral(true);
                                    puppe.get(i).setBlue(false);
                                    System.out.println(puppe.get(i));
                                    break;
                                }
                            }
                        }

                        for (int i = 0; i < puppe.size(); i++) {

                            if (puppe.get(i) instanceof Puppet) {
                                if ((puppe.get(i).isActive() && puppe.get(i).isBlue()) || (puppe.get(i).isActive() && puppe.get(i).isRed())) {
                                    puppe.get(i).RecGray();
                                    puppe.get(i).OriginalText();
                                    puppe.get(i).setRed(false);
                                    puppe.get(i).setNetral(true);
                                    puppe.get(i).setBlue(false);
                                    System.out.println(puppe.get(i));
                                    break;
                                }
                            }
                        }

                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Default preset");
                }
            }

            return null;
        });
        Optional<Puppet> result = dialogChange.showAndWait();
        System.out.println("Created");
    }
}

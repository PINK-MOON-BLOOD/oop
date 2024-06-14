package com.example.lab4;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

public class Sort {
    public static void PerelikMicro(ArrayList<Puppet> puppe){
        Dialog<Puppet> dialog = new Dialog<>();
        dialog.setTitle("SEARCH MICRO WHO ");

        CheckBox checkBox1 = new CheckBox();
        CheckBox checkBox2 = new CheckBox();
        CheckBox checkBox3 = new CheckBox();
        RadioButton radioButton = new RadioButton();
        RadioButton radioButton1 = new RadioButton();
        RadioButton radioButton2 = new RadioButton();

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Factory_Krat:"),0,0);
        gridPane.add(checkBox1,3,0);
        gridPane.add(new Label("Factory_Tark:"),0,2);
        gridPane.add(checkBox2,3,2);
        gridPane.add(new Label("FREE-PUPPET:"),0,3);
        gridPane.add(checkBox3,3,3);
        gridPane.add(new Label("Sort Name: "),0,4);
        gridPane.add(radioButton,3,4);
        gridPane.add(new Label("Sort Level: "),0,5);
        gridPane.add(radioButton1,3,5);
        gridPane.add(new Label("Sort Health: "),0,6);
        gridPane.add(radioButton2,3,6);

        dialog.getDialogPane().setContent(gridPane);

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton ->{
            if(dialogButton == okButtonType){
                try {
                    if(radioButton.isSelected()){
                        if(checkBox1.isSelected()){
                            StringBuilder redTeamMicroObjects = new StringBuilder();
                            Collections.sort(puppe, Comparator.comparing(Puppet::getName));
                            for(Puppet p : puppe){
                                if (p.isRed() && !p.isDeleted()) {  // Перевіряємо, що об'єкт не видалений
                                    redTeamMicroObjects.append(p.toString()).append("\n");
                                }
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle(" Micro Objects");
                            alert.setHeaderText(null);
                            alert.setContentText("Micro Objects READ-TEAM: " + "\n" + redTeamMicroObjects);
                            alert.showAndWait();
                        }

                        if(checkBox2.isSelected()){
                            StringBuilder blueTeamMicroObjects = new StringBuilder();
                            Collections.sort(puppe, Comparator.comparing(Puppet::getName));
                            for(Puppet p : puppe){
                                if(p.isBlue() && !p.isDeleted()){

                                    blueTeamMicroObjects.append(p.toString()).append("\n");
                                }
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle(" Micro Objects");
                            alert.setHeaderText(null);
                            alert.setContentText("Micro Objects BLUE-TEAM: " + "\n" + blueTeamMicroObjects);
                            alert.showAndWait();
                        }

                        if(checkBox3.isSelected()){
                            StringBuilder free_puppet = new StringBuilder();
                            Collections.sort(puppe, Comparator.comparing(Puppet::getName));
                            for(Puppet p : puppe){
                                if(p.isNetral() && !p.isDeleted()){

                                    free_puppet.append(p.toString()).append("\n");
                                }
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle(" Micro Objects");
                            alert.setHeaderText(null);
                            alert.setContentText("Micro Objects FREE_PUPPET: " + "\n" + free_puppet);
                            alert.showAndWait();
                        }
                    }

                    if(radioButton1.isSelected()){
                        if(checkBox1.isSelected()){
                            StringBuilder redTeamMicroObjects = new StringBuilder();
                            Collections.sort(puppe, Comparator.comparing(Puppet::getLevel));
                            for(Puppet p : puppe){
                                if(p.isRed() && !p.isDeleted()){
                                    redTeamMicroObjects.append(p.toString()).append("\n");
                                }
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle(" Micro Objects");
                            alert.setHeaderText(null);
                            alert.setContentText("Micro Objects READ-TEAM: " + "\n" + redTeamMicroObjects);
                            alert.showAndWait();
                        }

                        if(checkBox2.isSelected()){
                            StringBuilder blueTeamMicroObjects = new StringBuilder();
                            Collections.sort(puppe, Comparator.comparing(Puppet::getLevel));
                            for(Puppet p : puppe){
                                if(p.isBlue() && !p.isDeleted()){

                                    blueTeamMicroObjects.append(p.toString()).append("\n");
                                }
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle(" Micro Objects");
                            alert.setHeaderText(null);
                            alert.setContentText("Micro Objects BLUE-TEAM: " + "\n" + blueTeamMicroObjects);
                            alert.showAndWait();
                        }

                        if(checkBox3.isSelected()){
                            StringBuilder free_puppet = new StringBuilder();
                            Collections.sort(puppe, Comparator.comparing(Puppet::getLevel));
                            for(Puppet p : puppe){
                                if(p.isNetral() && !p.isDeleted()){

                                    free_puppet.append(p.toString()).append("\n");
                                }
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle(" Micro Objects");
                            alert.setHeaderText(null);
                            alert.setContentText("Micro Objects FREE_PUPPET: " + "\n" + free_puppet);
                            alert.showAndWait();
                        }
                    }

                    if(radioButton2.isSelected()){
                        if(checkBox1.isSelected()){
                            StringBuilder redTeamMicroObjects = new StringBuilder();
                            Collections.sort(puppe, Comparator.comparing(Puppet::getHealth));
                            for(Puppet p : puppe){
                                if(p.isRed() && !p.isDeleted()){
                                    redTeamMicroObjects.append(p.toString()).append("\n");
                                }
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle(" Micro Objects");
                            alert.setHeaderText(null);
                            alert.setContentText("Micro Objects READ-TEAM: " + "\n" + redTeamMicroObjects);
                            alert.showAndWait();
                        }

                        if(checkBox2.isSelected()){
                            StringBuilder blueTeamMicroObjects = new StringBuilder();
                            Collections.sort(puppe, Comparator.comparing(Puppet::getHealth));
                            for(Puppet p : puppe){
                                if(p.isBlue() && !p.isDeleted()){

                                    blueTeamMicroObjects.append(p.toString()).append("\n");
                                }
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle(" Micro Objects");
                            alert.setHeaderText(null);
                            alert.setContentText("Micro Objects BLUE-TEAM: " + "\n" + blueTeamMicroObjects);
                            alert.showAndWait();
                        }

                        if(checkBox3.isSelected()){
                            StringBuilder free_puppet = new StringBuilder();
                            Collections.sort(puppe, Comparator.comparing(Puppet::getHealth));
                            for(Puppet p : puppe){
                                if(p.isNetral() && !p.isDeleted()){

                                    free_puppet.append(p.toString()).append("\n");
                                }
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle(" Micro Objects");
                            alert.setHeaderText(null);
                            alert.setContentText("Micro Objects FREE_PUPPET: " + "\n" + free_puppet);
                            alert.showAndWait();
                        }
                    }

                }catch (Exception e){
                    System.out.println(e);
                }
            }
            return null;
        });
        Optional<Puppet> result = dialog.showAndWait();


    }
}

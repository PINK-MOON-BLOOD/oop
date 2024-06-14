package com.example.lab4;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.net.URL;

public class Factory_Tark extends Factory_Krat{
    private static final long serialVersionUID = 7532129694979576624L;
    transient private Label labelTark;

    transient private Rectangle recTark;

    transient private ImageView Tark;

    public Factory_Tark(String name, float x, float y, double height, double width) {
        super(name, x, y, height, width);

        labelTark = new Label(getName());
        labelTark.setLayoutX(getxPos()+85);
        labelTark.setLayoutY(getyPos()-12);


        Tark = new ImageView(HelloApplication.Tark);
        Tark.setX(getxPos());
        Tark.setY(getyPos());


        recTark= new Rectangle(100,22);
        recTark.setFill(Color.TRANSPARENT);
        recTark.setStrokeWidth(3);
        recTark.setStroke(Color.CORAL);
        recTark.setX(getxPos()+80);
        recTark.setY(getyPos()-12);

    }

//    public Puppet interact(Puppet puppet){
//        if(puppet instanceof Lead_of_Parade){
//            //Lead_of_Parade leadOfParade = (Lead_of_Parade) puppet;
//            if (puppet.isNetral() || puppet.isRed()){
//                puppet.setHealth(1000);
//                System.out.println(puppet);
//            }
//
//        }
//
//        else if (puppet instanceof Puppet_of_the_future) {
//
//        }
//        else if (puppet instanceof Puppet) {
//
//        }
//
//        return puppet;
//    }
public Puppet interact(Puppet puppet) {
    if (puppet instanceof Lead_of_Parade) {
        if (puppet.isNetral() || puppet.isRed()) {
            puppet.setHealth(puppet.getHealth() - 1);
            if (puppet.getHealth() <= 0) {
                puppet.deleteActiveMicroObject();
            }
        } else if (puppet.isBlue()) {
            puppet.setHealth(puppet.getHealth() + 1);
            if (puppet.getHealth() >= 115) {
                puppet.setHealth(115);
            }
        }

    } else if (puppet instanceof Puppet_of_the_future) {
        if (puppet.isNetral() || puppet.isRed()) {
                puppet.setHealth(puppet.getHealth() - 1);
                if (puppet.getHealth() <= 0) {
                    puppet.deleteActiveMicroObject();
                }
            } else if (puppet.isBlue()) {
                puppet.setHealth(puppet.getHealth() + 1);
                if (puppet.getHealth() >= 115) {
                    puppet.setHealth(95);
                }

        }

    }else if (puppet instanceof Puppet) {
        if (puppet.isNetral() || puppet.isRed()) {
            puppet.setHealth(puppet.getHealth() - 1);
            if (puppet.getHealth() <= 0) {
                puppet.deleteActiveMicroObject();
            }
        } else if (puppet.isBlue()) {
            puppet.setHealth(puppet.getHealth() + 1);
            if (puppet.getHealth() >= 115) {
                puppet.setHealth(85);
            }
        }
    }

    return puppet;
}





    public  void seeTark(){ HelloApplication.group.getChildren().addAll(labelTark,Tark,recTark);}

    public Label getLabelTark() {
        return labelTark;
    }

    public void setLabelTark(Label labelTark) {
        this.labelTark = labelTark;
    }

    public Rectangle getRecTark() {
        return recTark;
    }

    public void setRecTark(Rectangle recTark) {
        this.recTark = recTark;
    }

    public ImageView getTark() {
        return Tark;
    }

    public void setTark(ImageView tark) {
        Tark = tark;
    }
}

package com.example.lab4;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.lab4.Puppet.imageDeltaY;

public class Factory_Krat   implements Serializable {
    private static final long serialVersionUID = 987654321L;

    protected String name;
  //  protected ArrayList<Puppet> microObjects;
   // protected ArrayList<String> microObjectsNames;
    protected double height;
    protected double width;
    protected float xPos;
    protected float yPos;

    transient private Label label;

    transient private Rectangle rec;

    transient private ImageView Krat;

    public Factory_Krat(String name, float x, float y , double height, double width) {
        this.name = name;
        this.xPos = x;
        this.yPos = y;
        this.height = height;
        this.width = width;

        label = new Label(getName());
        label.setLayoutX(getxPos()+85);
        label.setLayoutY(getyPos());


        Krat = new ImageView(HelloApplication.Krat);
        Krat.setX(getxPos());
        Krat.setY(getyPos());


        rec= new Rectangle(100,22);
        rec.setFill(Color.TRANSPARENT);
        rec.setStrokeWidth(3);
        rec.setStroke(Color.CORAL);
        rec.setX(getxPos()+80);
        rec.setY(getyPos());


    }

    public void seeKrat(){
        HelloApplication.group.getChildren().addAll(label, Krat, rec);

    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public String getName() {
        return name;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Rectangle getRec() {
        return rec;
    }

    public void setRec(Rectangle rec) {
        this.rec = rec;
    }

    public ImageView getKrat() {
        return Krat;
    }

    public void setKrat(ImageView krat) {
        Krat = krat;
    }

    @Override
    public String toString() {
        return "Factory_Krat{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", xPos=" + xPos +
                ", yPos=" + yPos +
                '}'+"\n";
    }

    public Puppet interact(Puppet puppet) {
        if (puppet instanceof Lead_of_Parade) {
            if (puppet.isNetral() || puppet.isBlue()) {
                puppet.setHealth(puppet.getHealth() - 1);
                if (puppet.getHealth() <= 0) {
                    puppet.deleteActiveMicroObject();
                }
            } else if (puppet.isRed()) {
                puppet.setHealth(puppet.getHealth() + 1);
                if (puppet.getHealth() >= 115) {
                    puppet.setHealth(115); // Ensure health does not exceed 150
                }
            }

        } else if (puppet instanceof Puppet_of_the_future) {
            if (puppet.isNetral() || puppet.isBlue()) {
                    puppet.setHealth(puppet.getHealth() - 1);
                    if (puppet.getHealth() <= 0) {
                        puppet.deleteActiveMicroObject();
                    }
                } else if (puppet.isRed()) {
                    puppet.setHealth(puppet.getHealth() + 1);
                    if (puppet.getHealth() >= 115) {
                        puppet.setHealth(95); // Ensure health does not exceed 150
                    }

            }

        }else if (puppet instanceof Puppet) {
            if (puppet.isNetral() || puppet.isBlue()) {
                puppet.setHealth(puppet.getHealth() - 1);
                if (puppet.getHealth() <= 0) {
                    puppet.deleteActiveMicroObject();
                }
            } else if (puppet.isRed()) {
                puppet.setHealth(puppet.getHealth() + 1);
                if (puppet.getHealth() >= 115) {
                    puppet.setHealth(85); // Ensure health does not exceed 150
                }
            }
        }

        return puppet;
    }

}

package com.example.lab4;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Tower extends Factory_Krat {
    private static final long serialVersionUID = -1982940773675461889L;
    transient private Label labelTower;

    transient private Rectangle recTower;

    transient private ImageView Tower;

    public Tower(String name, float x, float y, double height, double width) {
        super(name, x, y, height, width);

        labelTower = new Label(getName());
        labelTower.setLayoutX(getxPos()+15);
        labelTower.setLayoutY(getyPos()-30);


        Tower = new ImageView(HelloApplication.Tower);
        Tower.setX(getxPos());
        Tower.setY(getyPos());


        recTower= new Rectangle(100,22);
        recTower.setFill(Color.TRANSPARENT);
        recTower.setStrokeWidth(3);
        recTower.setStroke(Color.CORAL);
        recTower.setX(getxPos()-10);
        recTower.setY(getyPos()-30);
    }


    public  void seeTower(){ HelloApplication.group.getChildren().addAll(labelTower,Tower,recTower);}

    public Label getLabelTower() {
        return labelTower;
    }

    public void setLabelTower(Label labelTower) {
        this.labelTower = labelTower;
    }

    public Rectangle getRecTower() {
        return recTower;
    }

    public void setRecTower(Rectangle recTower) {
        this.recTower = recTower;
    }

    public ImageView getTower() {
        return Tower;
    }

    public void setTower(ImageView tower) {
        Tower = tower;
    }

//|| puppet.isBlue() || puppet.isRed()
    // КРИВО ПРАЦЮЄ
 transient private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//scheduler.shutdown();
    public Puppet interact(Puppet puppet){
        if(puppet instanceof Lead_of_Parade){
            if (puppet.isNetral()) {
                scheduler.schedule(() -> recTower.setStroke(Color.GRAY), 1, TimeUnit.SECONDS);

            } else if (puppet.isRed()) {
                scheduler.schedule(() -> recTower.setStroke(Color.RED), 1, TimeUnit.SECONDS);
            } else if (puppet.isBlue()) {
                scheduler.schedule(() -> recTower.setStroke(Color.BLUE), 1, TimeUnit.SECONDS);
            }

        } else if (puppet instanceof Puppet_of_the_future) {
            if (puppet.isNetral()) {
                scheduler.schedule(() -> recTower.setStroke(Color.GRAY), 1, TimeUnit.SECONDS);

            } else if (puppet.isRed()) {
                scheduler.schedule(() -> recTower.setStroke(Color.RED), 1, TimeUnit.SECONDS);
            } else if (puppet.isBlue()) {
                scheduler.schedule(() -> recTower.setStroke(Color.BLUE), 1, TimeUnit.SECONDS);
            }

        } else if (puppet instanceof Puppet) {
            if (puppet.isNetral()) {
                scheduler.schedule(() -> recTower.setStroke(Color.GRAY), 1, TimeUnit.SECONDS);

            } else if (puppet.isRed()) {
                scheduler.schedule(() -> recTower.setStroke(Color.RED), 1, TimeUnit.SECONDS);
            } else if (puppet.isBlue()) {
                scheduler.schedule(() -> recTower.setStroke(Color.BLUE), 1, TimeUnit.SECONDS);
            }

        }

        return puppet;
    }
}
// else if (puppet instanceof Puppet_of_the_future) {
//            if (puppet.isNetral() || puppet.isBlue()|| puppet.isRed()){
//                puppet.setHealth(10);
//
//
//            }
//        }
//        else if (puppet instanceof Puppet) {
//            if (puppet.isNetral() || puppet.isBlue()|| puppet.isRed()){
//                puppet.setHealth(123);
//
//
//            }
//        }


package com.example.lab4;
/*
import com.dlsc.formsfx.model.structure.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class Micro {

    private static Micro instance;

    private ArrayList<Puppet> micro;



    private Micro(){
      micro = new ArrayList<>();
      Puppet puppet = new Puppet("Puppet",10,10 ,100 ,50);
      micro.add(puppet);

         Puppet_of_the_future puppetOfTheFuture = new Puppet_of_the_future("Puppet_of_the_future",10,10 ,250 ,120);
         micro.add(puppetOfTheFuture);

        Lead_of_Parade leadOfParade = new Lead_of_Parade("Lead_of_Parade",10,10 ,350 ,140);
         micro.add(leadOfParade);


    }



    public void activate(double clickX, double clickY) {
        for (Puppet puppet : micro) {
            if (clickX >= puppet.getX() && clickX <= puppet.getX() + puppet.getWidth() &&
                    clickY >= puppet.getY() && clickY <= puppet.getY() + puppet.getHeight()) {
                puppet.setActive(!puppet.isActive()); // Toggle the active state
            } else {
                puppet.setActive(false); // Deactivate all other puppets
            }
        }
    }


    public static synchronized Micro getInstance() {
        if (instance == null) {
            instance = new Micro();
        }
        return instance;
    }

    public void move(KeyCode keyCode) {
        for (Puppet puppet : micro) {
            if (puppet.isActive()) {
                keyPressed(puppet, keyCode);
            }
        }
    }
    private void keyPressed(Object object, KeyCode keyCode){
        if(object instanceof Puppet){
            switch (keyCode) {
                case W: {
                    ((Puppet) object).setyPos(((Puppet) object).getY() - 5);
                    break;
                }
                case A: {
                    ((Puppet) object).setxPos(((Puppet) object).getX() - 5);
                    break;
                }
                case S:{
                    ((Puppet) object).setyPos(((Puppet) object).getY() + 5);
                    break;
                }
                case D:{
                    ((Puppet) object).setxPos(((Puppet) object).getX() + 5);
                    break;
                }
            }

        }
    }



}
*/
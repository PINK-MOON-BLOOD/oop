package com.example.lab4;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import static com.example.lab4.Puppet.imageDeltaX;
import static com.example.lab4.Puppet.imageDeltaY;

public class HelloApplication extends Application {


    ScrollBar scrollBarH = new ScrollBar();
    ScrollBar scrollBarV = new ScrollBar();
    public static Rectangle rectMinimap;
    public static Rectangle miniMap;
    //ScrollPane scrollPane = new ScrollPane();

    public static Group group;
    boolean map = false;
    public static ImageView imgviewmap;

    static LocalDateTime beginTime = LocalDateTime.now();
    static int frames = 0;

    public static Scene scene;
    public static Image puppet;
    public static Image puppetOfTheFuture;
    public static Image LeadOfParad;
    public static ImageView imgpuppet;
    public static ImageView imgPuppetOfTheFuture;
    public static ImageView imgLeadOfParad;

    //
    public static Image Krat;
    public static Image Tark;
    public static Image Tower;
    public static ImageView imgKrat;
    public static ImageView imgTark;
    public static ImageView imgTower;

     public static double x;
     public static double y;



    public static double keyStepX = 10.0;

    public static double keyStepY = 10.0;
    public static ArrayList<Puppet> puppe = new ArrayList<>();
    public static ArrayList<Factory_Krat> Macro = new ArrayList<>();
    public static Label microObjectsLabel = new Label();

    public void updateAllMicroObjects() {
        for (int i = 0; i < puppe.size(); i++) {
            Puppet interactPuppet = puppe.get(i);
            for (Factory_Krat interactMacro : Macro) {
                // Перевірка чи координати і розміри puppet знаходяться всередині macro
                if ((interactPuppet.getxPos() >= interactMacro.getxPos() &&
                        interactPuppet.getxPos() + interactPuppet.getWidth() <= interactMacro.getxPos() + interactMacro.getWidth()) &&
                        (interactPuppet.getyPos() >= interactMacro.getyPos() &&
                                interactPuppet.getyPos() + interactPuppet.getHeight() <= interactMacro.getyPos() + interactMacro.getHeight())) {

                    Puppet updatedFan = interactMacro.interact(interactPuppet);
                    if (updatedFan != interactPuppet) {
                        puppe.set(i, updatedFan);
                    }
                    break;
                }
            }
        }
    }


    public void updateAllMicroObjectsWithAllMicroObjects() {
        for (int i = 0; i < puppe.size(); i++) {
            Puppet interactPuppet = puppe.get(i);
            for (Puppet interactPuppet1 : puppe) {
                // if (interactPuppet == interactPuppet1) continue;
                // Перевірка чи координати і розміри puppet знаходяться всередині macro
                if ((interactPuppet.getxPos() >= interactPuppet1.getxPos() &&
                        interactPuppet.getxPos() + interactPuppet.getWidth() <= interactPuppet1.getxPos() + interactPuppet1.getWidth()) &&
                        (interactPuppet.getyPos() >= interactPuppet1.getyPos() &&
                                interactPuppet.getyPos() + interactPuppet.getHeight() <= interactPuppet1.getyPos() + interactPuppet1.getHeight())) {

                    //  Puppet updatedFan = interactPuppet1.interactWithAll(interactPuppet);
//                    if (updatedFan != interactPuppet) {
//                        puppe.set(i, updatedFan);
//                    }
                }
            }
        }
    }


    //    protected double vidstan (double x1, double x2, double y1, double y2) {
//        return Math.sqrt (Math.pow ( (x1 - x2), 2) + Math.pow ( (y1 - y2), 2));
//    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (imgviewmap != null) {
                    group.getChildren().remove(imgviewmap);
                    group.getChildren().remove(miniMap);
                }
                group.getChildren().remove(rectMinimap);
               // group.getChildren().remove(miniMap);
                WritableImage snapshot = group.snapshot(new SnapshotParameters(), null);

                imgviewmap = new ImageView(snapshot);
                Scale scale = new Scale();
                scale.setX(0.087);
                scale.setY(0.087);
                imgviewmap.getTransforms().add(scale);


                miniMap = new Rectangle(primaryStage.getWidth() / 5.0, primaryStage.getHeight() / 5.0, Color.TRANSPARENT);
                miniMap.setStroke(Color.RED);
                miniMap.setStrokeWidth(2);
//                miniMap.setWidth(snapshot.getWidth() * 0.233);
//                miniMap.setHeight((snapshot.getHeight()) * 0.4055);
                miniMap.setWidth(snapshot.getWidth() * 0.024);
                miniMap.setHeight((snapshot.getHeight()) * 0.05);

                // Встановлюємо положення нового miniMap
                miniMap.setLayoutX(scrollBarH.getValue());
                miniMap.setLayoutY(scrollBarV.getValue());


                rectMinimap.setWidth(snapshot.getWidth() * 0.087);
                rectMinimap.setHeight((snapshot.getHeight()+45) * 0.087);

                group.getChildren().add(rectMinimap);
                group.getChildren().add(imgviewmap);
               // group.getChildren().add(miniMap);
                // Переміщення зображення карти разом з прямокутником
                imgviewmap.setLayoutX(imgviewmap.getLayoutX() + scrollBarH.getValue());
                imgviewmap.setLayoutY(imgviewmap.getLayoutY() + scrollBarV.getValue());

                // автоматичний рух
                for (Puppet puppetAouto: puppe) {
                    if (puppetAouto instanceof  Lead_of_Parade){
                        if(!puppetAouto.isActive()){
                            ((Lead_of_Parade) puppetAouto).moveAutoLD();
                        }

                    } else if (puppetAouto instanceof Puppet_of_the_future) {
                        if (!puppetAouto.isActive()){
                            ((Puppet_of_the_future) puppetAouto).moveAutoPF();
                        }
                    } else if (puppetAouto instanceof Puppet) {
                        if (!puppetAouto.isActive()){
                            puppetAouto.moveAuto();
                        }else if (puppetAouto.isActive()) {
                            puppetAouto.move(0,0);
                        }
                    }
                }






            }
        };

        timer.start();



        group = new Group();
        Pane pane = new Pane();
        double wight = 1250;
        double height = 750;
        double widthScroll = 5000;
        double heightScroll = 3000;


        scrollBarV.setPrefHeight(height);
        scrollBarV.setOrientation(Orientation.VERTICAL);
        scrollBarV.setMin(1);
        scrollBarV.setMax(heightScroll);
        scrollBarV.setValue(0);
        scrollBarV.setLayoutX(wight - scrollBarV.getWidth() + 5); // Позиція скролів

        scrollBarH.setPrefWidth(wight - 17);
        scrollBarH.setOrientation(Orientation.HORIZONTAL);
        scrollBarH.setMin(1);
        scrollBarH.setMax(widthScroll);
        scrollBarH.setValue(0);
        scrollBarH.setLayoutY(height - scrollBarH.getHeight() + 85); // Позиція скролів

        group.getChildren().addAll(scrollBarH, scrollBarV);
        // scene = new Scene(group,wight,height);



        scrollBarV.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            group.setLayoutY(-((double) newValue));
            rectMinimap.setLayoutY(((double) newValue));
            imgviewmap.setLayoutY(((double) newValue));
           // miniMap.setLayoutY(scrollBarV.getValue());



        });

        scrollBarH.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            group.setLayoutX(-((double) newValue));
            rectMinimap.setLayoutX(((double) newValue));
            imgviewmap.setLayoutX(((double) newValue));
           //miniMap.setLayoutX(scrollBarH.getValue());


        });



        pane.getChildren().addAll(group, scrollBarH, scrollBarV);
        scene = new Scene(pane, wight, height);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Lies of P");
        primaryStage.show();


//        double getX = scene.getX() + scrollPane.getHvalue() * (vbox.getBoundsInParent().getWidth() - scrollPane.getViewportBounds().getWidth());
//        double getY = scene.getY() + scrollPane.getVvalue() * (vbox.getBoundsInParent().getHeight() - scrollPane.getViewportBounds().getHeight());


        rectMinimap = new Rectangle(scene.getX() / 5.0 + 10, scene.getY() / 5.0 + 10, Color.TRANSPARENT);
        rectMinimap.setStroke(Color.BLACK);
        rectMinimap.setStrokeWidth(7.0);

//        public void moveTo(double x, double y) {
//            if (x < miniMap.getWidth() / 2) {
//                scrollBarH.setValue(0);
//            } else if (x > pane.getWidth() - miniMap.getWidth() / 2) {
//                scrollBarH.setValue(1);
//            } else {
//                scrollBarH.setValue(x / pane.getWidth());
//            }
//
//            if (y < miniMap.getHeight() / 2) {
//                scrollBarV.setValue(0);
//            } else if (y > pane.getHeight() - miniMap.getHeight() / 2) {
//                scrollBarV.setValue(1);
//            } else {
//                scrollBarV.setValue(y / pane.getHeight());
//            }
//        }
        // Виклик метода базового класу

        puppet = new Image(Objects.requireNonNull(HelloApplication.class.getResource("Puppet.png")).toString(), 100, 100, false, false);
        System.out.println(Objects.requireNonNull(HelloApplication.class.getResource("Puppet.png")).toString());
        imgpuppet = new ImageView(puppet);

        puppetOfTheFuture = new Image(Objects.requireNonNull(HelloApplication.class.getResource("PuppetOfTheFuture.png")).toString(), 100, 100, false, false);

        System.out.println(HelloApplication.class.getResource("PuppetOfTheFuture.png").toString());
        imgPuppetOfTheFuture = new ImageView(puppetOfTheFuture);

        LeadOfParad = new Image(Objects.requireNonNull(HelloApplication.class.getResource("LeadOfParad.png")).toString(), 100, 100, false, false);

        System.out.println(Objects.requireNonNull(HelloApplication.class.getResource("LeadOfParad.png")).toString());
        imgLeadOfParad = new ImageView(LeadOfParad);

        /////////////////////////
        //
        //
        Krat = new Image(Objects.requireNonNull(HelloApplication.class.getResource("крат.jpg")).toString(), 280, 300, false, false);
        System.out.println(Objects.requireNonNull(HelloApplication.class.getResource("крат.jpg")).toString());
        imgKrat = new ImageView(Krat);

        Tark = new Image(Objects.requireNonNull(HelloApplication.class.getResource("тарк.jpg")).toString(), 280, 300, false, false);
        System.out.println(HelloApplication.class.getResource("тарк.jpg").toString());
        imgTark = new ImageView(Tark);

        Tower = new Image(Objects.requireNonNull(HelloApplication.class.getResource("Tower_Radiant_model.png")).toString(), -120, -140, false, false);
        System.out.println(HelloApplication.class.getResource("Tower_Radiant_model.png").toString());
        imgTower = new ImageView(Tower);
        //////////////

// StateObj stateObj = new StateObj();
        //+Integer.toString(i+1)

        //виправи розташування папетів для бази
        Puppet[] puppet0 = new Puppet[15];
        Random rnd = new Random();
        for (int i = 0; i < puppet0.length; ++i) {
            //Виклик конструктора базового класу

            if (i == 0) {
                puppet0[i] = new Puppet("Bob" + Integer.toString(i + 1), 2, 25, (int) (100.0), rnd.nextDouble() * scene.getWidth(), rnd.nextDouble() * scene.getHeight(), false, false, true);
                puppet0[i].resurrect();
                puppe.add(puppet0[i]);
                System.out.println(puppet0[i]);

            } else if (i == 1) {
                puppet0[i] = new Puppet("Jon" + Integer.toString(i + 1), 3, 25, (int) (100.0), 15, 1480, true, false, false);
                puppet0[i].resurrect();
                puppe.add(puppet0[i]);
                puppet0[i].RecRED();
                puppet0[i].TextRED();
                System.out.println(puppet0[i]);
            } else if (i == 2) {
                puppet0[i] = new Puppet("Drake" + Integer.toString(i + 1), 4, 25,  (int) (100.0), 980, 1490, false, true, false);
                puppet0[i].resurrect();
                puppe.add(puppet0[i]);
                puppet0[i].RecBLUE();
                puppet0[i].TextBlue();
                System.out.println(puppet0[i]);
            }
            //System.out.println(puppet0[i].toString());
        }

        Puppet_of_the_future[] puppet1 = new Puppet_of_the_future[15];
        Random nd = new Random();
        for (int i = 0; i < puppet1.length; ++i) {
            //Виклик конструктора базового класу

            if (i == 0) {
                puppet1[i] = new Puppet_of_the_future("Alex" + Integer.toString(i + 1), 7, 35,  (120), nd.nextDouble() * scene.getWidth(),
                        nd.nextDouble() * scene.getHeight(), false, false, true);
                puppet1[i].resurrectt();
                puppe.add(puppet1[i]);
                puppet1[i].setNetral(true);
                System.out.println(puppet1[i]);
            } else if (i == 1) {
                puppet1[i] = new Puppet_of_the_future("Sacha" + Integer.toString(i + 1), 1, 35,  (120), 15, 1490, true, false, false);
                puppet1[i].resurrectt();
                puppet1[i].RecRED();
                puppet1[i].TextRED();
                puppe.add(puppet1[i]);
                puppet1[i].setRed(true);
                System.out.println(puppet1[i]);
            } else if (i == 2) {
                puppet1[i] = new Puppet_of_the_future("Tori" + Integer.toString(i + 1), 3, 35, (120), 990, 1500, false, true, false);
                puppet1[i].resurrectt();
                puppet1[i].RecBLUE();
                puppet1[i].TextBlue();
                puppe.add(puppet1[i]);
                puppet1[i].setBlue(true);
                System.out.println(puppet1[i]);
            }
            // System.out.println(puppet1[i].toString());
        }

        Lead_of_Parade[] puppet2 = new Lead_of_Parade[15];
        Random rnvd = new Random();
        for (int i = 0; i < puppet2.length; ++i) {
            //Виклик конструктора базового класу

            if (i == 0) {
                puppet2[i] = new Lead_of_Parade("Lil Peep" + Integer.toString(i + 1), 6, 40, (int) (150.0),
                        rnvd.nextDouble() * scene.getWidth(),
                        rnvd.nextDouble() * scene.getHeight(), false, false, true);
                puppet2[i].resurrecttt();
                puppet2[i].setNetral(true);
                puppe.add(puppet2[i]);
                System.out.println(puppet2[i]);
            } else if (i == 1) {
                puppet2[i] = new Lead_of_Parade("Uzi" + Integer.toString(i + 1), 8, 40, (int) (150.0), 30, 1480, true, false, false);
                puppet2[i].resurrecttt();
                puppet2[i].RecRED();
                puppet2[i].TextRED();
                puppet2[i].setRed(true);
                puppe.add(puppet2[i]);
                System.out.println(puppet2[i]);
            } else if (i == 2) {
                puppet2[i] = new Lead_of_Parade("SixNine" + Integer.toString(i + 1), 10, 40, (int) (150.0), 1000, 1485, false, true, false);
                puppet2[i].resurrecttt();
                puppet2[i].RecBLUE();
                puppet2[i].TextBlue();
                puppet2[i].setBlue(true);
                puppe.add(puppet2[i]);
                System.out.println(puppet2[i]);
            }


            //System.out.println(puppet2[i].toString());

        }

        //////////////////////////////
        Factory_Krat[] factoryKrats = new Factory_Krat[1];
        for (int i = 0; i < factoryKrats.length; ++i) {
            factoryKrats[i] = new Factory_Krat("Factory_Krat", 0, 2500, 180, 250);

            factoryKrats[i].seeKrat();
            Macro.add(factoryKrats[i]);
        }

        Factory_Tark[] factoryTarks = new Factory_Tark[1];
        for (int i = 0; i < factoryTarks.length; ++i) {
            //Виклик конструктора базового класу
            factoryTarks[i] = new Factory_Tark("Factory_Tark", 4500, 1500, 180, 250);

            factoryTarks[i].seeTark();
            Macro.add(factoryTarks[i]);
        }

        Tower[] tower = new Tower[10];
        for (int i = 0; i < factoryKrats.length; ++i) {
            //Виклик конструктора базового класу
            tower[i] = new Tower("Tower", 500, 310, 120, 70);
            System.out.println(tower[i]);
            tower[i].seeTower();
            Macro.add(tower[i]);

            tower[i] = new Tower("Tower", 1500, 310, 120, 70);
            System.out.println(tower[i]);
            tower[i].seeTower();
            Macro.add(tower[i]);

            tower[i] = new Tower("Tower", 2000, 310, 120, 70);
            System.out.println(tower[i]);
            tower[i].seeTower();
            Macro.add(tower[i]);

            tower[i] = new Tower("Tower", 4000, 1010, 120, 70);
            System.out.println(tower[i]);
            tower[i].seeTower();
            Macro.add(tower[i]);


        }


        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {


            public void handle(MouseEvent mouseEvent) {

                double sceneX = mouseEvent.getX();
                double sceneY = mouseEvent.getY();

                double getX = group.getLayoutX() + scrollBarH.getValue() * (pane.getWidth() - (wight + 0.40002441));
                double getY = group.getLayoutY() + scrollBarV.getValue() * (pane.getHeight() - (height + 0.40002441));

                for (Puppet puppet : puppe) {
                    if (puppet instanceof Lead_of_Parade) {
                        ((Lead_of_Parade) puppet).tryActivate(sceneX - getX, sceneY - getY);
                        // System.out.println(puppet instanceof  Puppet);
                        //updateAllMicroObjects();

                    } else if (puppet instanceof Puppet_of_the_future) {
                        ((Puppet_of_the_future) puppet).tryActivate(sceneX - getX, sceneY - getY);
                        //System.out.println(puppet instanceof  Puppet_of_the_future);
                        // updateAllMicroObjects();

                    } else if (puppet instanceof Puppet) {
                        ((Puppet) puppet).tryActivate(sceneX - getX, sceneY - getY);
                        //System.out.println(puppet instanceof  Lead_of_Parade);
                        // updateAllMicroObjects();
                    }

                }

            }
        });


        //Криво працює видалення
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                double dx = 0.0, dy = 0.0;

                switch (keyEvent.getCode()) {
                    case W:
                        // ВИКОРИСТАЙ ПОЛОЖЕННЯ КАРТИНКИ
                        dy -=keyStepY;

//                        for (Puppet move : puppe) {
//                            double currentYPos = move.getyPos();
//                            if (currentYPos > 0) {
//                                move.setyPos(Math.max(currentYPos - HelloApplication.keyStepY, 0));
////                                dy -=HelloApplication.keyStepY;
//                            }
//                        }
                        break;
                    case S:
                        dy =keyStepY;

//                        for (Puppet move : puppe) {
//                            double currentYPos = move.getyPos();
//                            if (currentYPos < heightScroll) {
//                                move.setyPos(Math.min(currentYPos + HelloApplication.keyStepY, heightScroll));
//                            }
//                        }
                        break;
                    case A:
                        dx -=keyStepX;

//                        for (Puppet move : puppe) {
//                            double currentXPos = move.getxPos();
//                            if (currentXPos > 0) {
//                                move.setxPos(Math.max(currentXPos - HelloApplication.keyStepX, 0));
//                            }
//                        }
                        break;
                    case D:
                        dx = keyStepX;

//                        for (Puppet move : puppe) {
//                            double currentXPos = move.getxPos();
//                            if (currentXPos < widthScroll) {
//                                move.setxPos(Math.min(currentXPos + HelloApplication.keyStepX, widthScroll));
//                            }
//                        }
                        break;
                    case F5:
                        SERIALIZATON(puppe, Macro, primaryStage);

                        break;
                    case F9:
                        try {
                            DESERIALIZATON(primaryStage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        break;


                    case DELETE:

                        for (Puppet puppet : puppe) {
                            if (puppet instanceof Puppet) {
                                ((Puppet) puppet).deleteActiveMicroObject();
                            } else if (puppet instanceof Puppet_of_the_future) {
                                ((Puppet_of_the_future) puppet).deleteActiveMicroObject();
                            } else if (puppet instanceof Lead_of_Parade) {
                                ((Lead_of_Parade) puppet).deleteActiveMicroObject();
                            }
//                            System.out.println(puppet instanceof  Puppet);
//                            System.out.println(puppet instanceof  Puppet_of_the_future);
//                            System.out.println(puppet instanceof  Lead_of_Parade);
                            //puppet.deleteActiveMicroObject();
                        }
                        break;
                    case ESCAPE:

                        for (Puppet puppet : puppe) {
                            puppet.handleEscKeyPress();
                        }
                        break;
                    case TAB:
                        for (Puppet puppet : puppe) {
                            puppet.updateMicroObjectsLabel();
                        }
                        break;
                    case Z:
                        createMicroObjectDialog.createMicroObjectDialog(puppet0, puppet1, puppet2);
                        break;
                    case Q:
                        changeActiveMicroObjectParameters.changeActiveMicroObjectParameters();
                        break;
                    case UP:
                        if (scrollBarV.getValue() > 0) {
                            scrollBarV.setValue(scrollBarV.getValue() - 20);
                            miniMap.setLayoutY(imgviewmap.getLayoutY() + scrollBarV.getValue()-20);
                        }
                        break;
                    case DOWN:
                        if (scrollBarV.getValue() < heightScroll) {
                            scrollBarV.setValue(scrollBarV.getValue() + 20);
                            miniMap.setLayoutY(imgviewmap.getLayoutY() + scrollBarV.getValue()+20);

                        }

                        break;
                    case LEFT:
                        if (scrollBarH.getValue() > 0) {
                            scrollBarH.setValue(scrollBarH.getValue() - 20);
                            miniMap.setLayoutX(scrollBarH.getValue()-20);
                        }
                        break;
                    case RIGHT:
                        if (scrollBarH.getValue() < widthScroll) {
                            scrollBarH.setValue(scrollBarH.getValue() + 20);
                            miniMap.setLayoutX(scrollBarH.getValue()+20);
                        }
                        break;
                    case T:
                       ChangeMicro.ChangeMicro(puppe);
                        break;
                    case Y:
                        SEARCH.SEARCH(puppe);
                        break;
                    case E:
                        Sort.PerelikMicro(puppe);
                        break;
                    case F:
                        Counter.Counter(puppe);
                        break;
                    case X:
                        try {
                            Puppet clonedPuppet = (Puppet) puppet0[0].clone();
                            // Add clonedPuppet to scene or do further operations
                            System.out.println("Cloned Puppet:");
                            System.out.println(clonedPuppet);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        break;

                }
                // ОНОВЛЕННЯ ЦЬОГО МЕТОДУ


                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(1.0), event -> {
                            updateAllMicroObjects();
                            updateAllMicroObjectsWithAllMicroObjects();
                            StateObj.updataST(puppe);
                        })
                );
                timeline.setCycleCount(1);
                timeline.play();
//
//                for (int i = 0; i < puppet0.length; ++i) {
//                    if (puppet0[i] != null && puppet0[i].isActive()) {
//                        puppet0[i].move(dx, dy);
//
//                    }
//                }
//
//                for (int i = 0; i < puppet1.length; ++i) {
//                    if (puppet1[i] != null && puppet1[i].isActive()) {
//                        puppet1[i].move(dx, dy);
//
//                    }
//                }
//
//                for (int i = 0; i < puppet2.length; ++i) {
//                    if (puppet2[i] != null && puppet2[i].isActive()) {
//                        puppet2[i].move(dx, dy);
//
//                    }
//                }

                for(Puppet move : puppe){
                    if(move.isActive()){}
                    move.move(dx,dy);
                }

            }
        });


    }

//    public static void updateST(ArrayList<Puppet> puppets) {
//        for (Puppet st : puppets) {
//            if (st instanceof Lead_of_Parade) {
//                ((Lead_of_Parade) st).stateObjPF.setStateObj(st.getHealth());
//            } else if (st instanceof Puppet_of_the_future) {
//                ((Puppet_of_the_future) st).stateObjPF.setStateObj(st.getHealth());
//            } else if (st instanceof Puppet) {
//                st.stateObj.setStateObj(st.getHealth());
//            }
//        }
//    }

    public static void main(String[] args) {

        launch();

    }

//    public void ChangeMicro(ArrayList<Puppet> puppe) {
//
//        Dialog<Puppet> dialogChange = new Dialog<>();
//        dialogChange.setTitle("ChangeMicro");
//
//
//        Label label0 = new Label("Factory_Krat:");
//        Label label1 = new Label("Factory_Tark:");
//        Label label2 = new Label("Free_Puppet:");
//
//        RadioButton radioButton0 = new RadioButton();
//        RadioButton radioButton1 = new RadioButton();
//        RadioButton radioButton2 = new RadioButton();
//
//        GridPane gridPane = new GridPane();
//
//        gridPane.add(label0, 0, 0);
//        gridPane.add(radioButton0, 2, 0);
//        gridPane.add(label1, 0, 1);
//        gridPane.add(radioButton1, 2, 1);
//        gridPane.add(label2, 0, 2);
//        gridPane.add(radioButton2, 2, 2);
//
//        dialogChange.getDialogPane().setContent(gridPane);
//
//        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
//        dialogChange.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
//
//        dialogChange.setResultConverter(dialogButton -> {
//            if (dialogButton == okButtonType) {
//                try {
//
//                    if (radioButton0.isSelected()) {
//
//
//                        /// Для МАСИВУ
//                        for (int i = 0; i < puppe.size(); i++) { // ЗМІНЮЄ НА ЧЕРВОНИЙ
//
//                            if (puppe.get(i) instanceof Lead_of_Parade) {
//                                if ((puppe.get(i).isActive() && puppe.get(i).isNetral()) || (puppe.get(i).isActive() && puppe.get(i).isBlue())) {
//
//                                    puppe.get(i).RecRED();
//                                    puppe.get(i).TextRED();
//                                    puppe.get(i).setRed(true);
//                                    puppe.get(i).setNetral(false);
//                                    puppe.get(i).setBlue(false);
//                                    System.out.println(puppe.get(i));
//                                    break;
//                                }
//                            }
//
//                        }
//
//                        for (int i = 0; i < puppe.size(); i++) {
//
//                            if (puppe.get(i) instanceof Puppet_of_the_future) {
//                                if ((puppe.get(i).isActive() && puppe.get(i).isNetral()) || (puppe.get(i).isActive() && puppe.get(i).isBlue())) {
//                                    puppe.get(i).RecRED();
//                                    puppe.get(i).TextRED();
//                                    puppe.get(i).setRed(true);
//                                    puppe.get(i).setNetral(false);
//                                    puppe.get(i).setBlue(false);
//                                    System.out.println(puppe.get(i));
//                                    break;
//                                }
//                            }
//                        }
//
//                        for (int i = 0; i < puppe.size(); i++) {
//
//                            if (puppe.get(i) instanceof Puppet) {
//                                if ((puppe.get(i).isActive() && puppe.get(i).isNetral()) || (puppe.get(i).isActive() && puppe.get(i).isBlue())) {
//                                    puppe.get(i).RecRED();
//                                    puppe.get(i).TextRED();
//                                    puppe.get(i).setRed(true);
//                                    puppe.get(i).setNetral(false);
//                                    puppe.get(i).setBlue(false);
//                                    System.out.println(puppe.get(i));
//                                    break;
//                                }
//                            }
//                        }
//                        //
//                    } else if (radioButton1.isSelected()) {// ЗМІНЮЄ НА СИНІЙ
//
//                        for (int i = 0; i < puppe.size(); i++) {
//
//                            if (puppe.get(i) instanceof Lead_of_Parade) {
//                                if ((puppe.get(i).isActive() && puppe.get(i).isNetral()) || (puppe.get(i).isActive() && puppe.get(i).isRed())) {
//                                    puppe.get(i).RecBLUE();
//                                    puppe.get(i).TextBlue();
//                                    puppe.get(i).setRed(false);
//                                    puppe.get(i).setNetral(false);
//                                    puppe.get(i).setBlue(true);
//                                    System.out.println(puppe.get(i));
//                                    break;
//                                }
//                            }
//
//                        }
//
//                        for (int i = 0; i < puppe.size(); i++) {
//
//                            if (puppe.get(i) instanceof Puppet_of_the_future) {
//                                if ((puppe.get(i).isActive() && puppe.get(i).isNetral()) || (puppe.get(i).isActive() && puppe.get(i).isRed())) {
//                                    puppe.get(i).RecBLUE();
//                                    puppe.get(i).TextBlue();
//                                    puppe.get(i).setRed(false);
//                                    puppe.get(i).setNetral(false);
//                                    puppe.get(i).setBlue(true);
//                                    System.out.println(puppe.get(i));
//                                    break;
//                                }
//                            }
//                        }
//
//                        for (int i = 0; i < puppe.size(); i++) {
//
//                            if (puppe.get(i) instanceof Puppet) {
//                                if ((puppe.get(i).isActive() && puppe.get(i).isNetral()) || (puppe.get(i).isActive() && puppe.get(i).isRed())) {
//                                    puppe.get(i).RecBLUE();
//                                    puppe.get(i).TextBlue();
//                                    puppe.get(i).setRed(false);
//                                    puppe.get(i).setNetral(false);
//                                    puppe.get(i).setBlue(true);
//                                    System.out.println(puppe.get(i));
//                                    break;
//                                }
//                            }
//                        }
//
//                    } else if (radioButton2.isSelected()) {// ЗМІНЮЄ НА НЕЙТРАЛЬНИЙ
//
//                        for (int i = 0; i < puppe.size(); i++) {
//
//                            if (puppe.get(i) instanceof Lead_of_Parade) {
//                                if ((puppe.get(i).isActive() && puppe.get(i).isBlue()) || (puppe.get(i).isActive() && puppe.get(i).isRed())) {
//                                    puppe.get(i).RecGray();
//                                    puppe.get(i).OriginalText();
//                                    puppe.get(i).setRed(false);
//                                    puppe.get(i).setNetral(true);
//                                    puppe.get(i).setBlue(false);
//                                    System.out.println(puppe.get(i));
//                                    break;
//                                }
//                            }
//
//                        }
//
//                        for (int i = 0; i < puppe.size(); i++) {
//
//                            if (puppe.get(i) instanceof Puppet_of_the_future) {
//                                if ((puppe.get(i).isActive() && puppe.get(i).isBlue()) || (puppe.get(i).isActive() && puppe.get(i).isRed())) {
//                                    puppe.get(i).RecGray();
//                                    puppe.get(i).OriginalText();
//                                    puppe.get(i).setRed(false);
//                                    puppe.get(i).setNetral(true);
//                                    puppe.get(i).setBlue(false);
//                                    System.out.println(puppe.get(i));
//                                    break;
//                                }
//                            }
//                        }
//
//                        for (int i = 0; i < puppe.size(); i++) {
//
//                            if (puppe.get(i) instanceof Puppet) {
//                                if ((puppe.get(i).isActive() && puppe.get(i).isBlue()) || (puppe.get(i).isActive() && puppe.get(i).isRed())) {
//                                    puppe.get(i).RecGray();
//                                    puppe.get(i).OriginalText();
//                                    puppe.get(i).setRed(false);
//                                    puppe.get(i).setNetral(true);
//                                    puppe.get(i).setBlue(false);
//                                    System.out.println(puppe.get(i));
//                                    break;
//                                }
//                            }
//                        }
//
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid input. Default preset");
//                }
//            }
//
//            return null;
//        });
//        Optional<Puppet> result = dialogChange.showAndWait();
//        System.out.println("Created");
//    }

    // СПРОБУЙ ПЕРЕРОБИТИ ПВД АРЕЙЛІСТ
//    public void createMicroObjectDialog(Puppet[] puppet0, Puppet_of_the_future[] puppet1, Lead_of_Parade[] puppet2) {
//
//        Dialog<Puppet> dialog = new Dialog<>();
//        dialog.setTitle("Create MicroObject");
//        dialog.setHeaderText("Enter MicroObject details:");
//
//        TextField nameField = new TextField();
//        //TextField stateFeild = new TextField();
//        TextField damageField = new TextField();
//
//        TextField healthField = new TextField();
//        TextField xPosField = new TextField();
//        TextField yPosField = new TextField();
//        RadioButton radioButton1 = new RadioButton("Puppet");
//        RadioButton radioButton2 = new RadioButton("PuppetOFTheFuture");
//        RadioButton radioButton3 = new RadioButton("Lead_of_Parade");
//
//        ComboBox comboBox = new ComboBox();
//        comboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
//
////        ComboBox comboBox1 = new ComboBox();
////        comboBox1.getItems().addAll("RedTeam", "BlueTeam", "FreePuppet");
//
//        nameField.setPromptText("Name");
//        // stateFeild.setPromptText("Стан об'єкта");
//        comboBox.setPromptText("Level");
//        //comboBox1.setPromptText("RASA");
//        damageField.setPromptText("Level");
//        healthField.setPromptText("HP");
//        xPosField.setPromptText("X Position");
//        yPosField.setPromptText("Y Position");
//
//        GridPane grid = new GridPane();
//        grid.add(new Label("Name:"), 0, 0);
//        grid.add(nameField, 1, 0);
//        grid.add(new Label("State"), 0, 1);
//        // grid.add(stateFeild,1,1);
//        grid.add(new Label("Level:"), 0, 2);
//        grid.add(comboBox, 1, 2);
//        grid.add(new Label("Damage:"), 0, 3);
//        grid.add(damageField, 1, 3);
//
//        grid.add(radioButton1, 0, 4);
//        grid.add(radioButton2, 1, 4);
//        grid.add(radioButton3, 2, 4);
//        grid.add(new Label(" HP:"), 0, 5);
//        grid.add(healthField, 1, 5);
//        grid.add(new Label("X Position:"), 0, 6);
//        grid.add(xPosField, 1, 6);
//        grid.add(new Label("Y Position:"), 0, 7);
//        grid.add(yPosField, 1, 7);
//        // grid.add(new Label("RASA:"),0,8);
//        // grid.add(comboBox1,1,8);
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
//                    // String stateObj = stateFeild.getText();
//                    int level = comboBox.getValue() == null ? 0 : Integer.parseInt(String.valueOf(comboBox.getValue()));
//                    double damage = damageField.getText().isEmpty() ? 0 : Double.parseDouble(damageField.getText());
//                    int health = healthField.getText().isEmpty() ? 0 : Integer.parseInt(healthField.getText());
//                    float xPos = Float.parseFloat(xPosField.getText());
//                    float yPos = Float.parseFloat(yPosField.getText());
//                    boolean red = false;
//                    boolean blue = false;
//                    boolean netral = true;
//
//
//                    if (radioButton1.isSelected()) {
//                        for (int i = 3; i < puppet0.length; ++i) {
//                            puppet0[i] = new Puppet(name, level, damage, health, xPos, yPos, red, blue, netral);
//                            puppet0[i].resurrect();
//                            puppe.add(puppet0[i]);
//                            System.out.println("was created" + puppet0[i].toString());
//                            break;
//                        }
//
//                    } else if (radioButton2.isSelected()) {
//                        for (int i = 3; i < puppet1.length; ++i) {
//                            puppet1[i] = new Puppet_of_the_future(name, level, damage, health, xPos, yPos, red, blue, netral);
//                            puppet1[i].resurrectt();
//                            puppe.add(puppet1[i]);
//                            System.out.println("was created" + puppet1[i].toString());
//                            break;
//
//                        }
//
//
//                    } else if (radioButton3.isSelected()) {
//                        for (int i = 3; i < puppet2.length; ++i) {
//                            puppet2[i] = new Lead_of_Parade(name, level, damage, health, xPos, yPos, red, blue, netral);
//                            puppet2[i].resurrecttt();
//                            puppe.add(puppet2[i]);
//                            System.out.println("was created" + puppet2[i].toString() + ".");
//                            break;
//                        }
//
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid input. Default preset");
//                }
//            }
//            return null;
//        });
//
//        Optional<Puppet> result = dialog.showAndWait();
//        //result.ifPresent(puppe -> System.out.println("Created"));
//
//
//    }

//    public void changeActiveMicroObjectParameters() {
//        for (Puppet puppet : puppe) {
//            if (puppet.isActive()) {
//                Dialog<Puppet> dialog = new Dialog<>();
//                dialog.setTitle("Change MicroObject Parameters");
//                dialog.setHeaderText("Enter new parameters:");
//
//                //TextField levelField = new TextField();
//                ComboBox comboBox = new ComboBox();
//                comboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
//                TextField damageField = new TextField();
//                TextField healthField = new TextField();
//                TextField xPosField = new TextField();
//                TextField yPosField = new TextField();
//
//                comboBox.setPromptText("New Level");
//                damageField.setPromptText("New Damage");
//                healthField.setPromptText("New HP");
//                xPosField.setPromptText("New X Position");
//                yPosField.setPromptText("New Y Position");
//
//                GridPane grid = new GridPane();
//                grid.add(new Label("Level:"), 0, 0);
//                grid.add(comboBox, 1, 0);
//                grid.add(new Label("Damage:"), 0, 1);
//                grid.add(damageField, 1, 1);
//                grid.add(new Label("HP:"), 0, 2);
//                grid.add(healthField, 1, 2);
//                grid.add(new Label("X Position:"), 0, 3);
//                grid.add(xPosField, 1, 3);
//                grid.add(new Label("Y Position:"), 0, 4);
//                grid.add(yPosField, 1, 4);
//
//                dialog.getDialogPane().setContent(grid);
//
//                ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
//                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
//
//                dialog.setResultConverter(dialogButton -> {
//                    if (dialogButton == okButtonType) {
//                        try {
//                            int newLevel = Integer.parseInt(comboBox.getValue().toString());
//                            double newDamage = Double.parseDouble(damageField.getText());
//                            int newHealth = Integer.parseInt(healthField.getText());
//                            float newXPos = Float.parseFloat(xPosField.getText());
//                            float newYPos = Float.parseFloat(yPosField.getText());
//
//                            puppet.setLevel(newLevel);
//                            puppet.setDamage(newDamage);
//                            puppet.setHealth(newHealth);
//                            puppet.setPosition(newXPos, newYPos);
//                            System.out.println("was created" + puppet.toString());
//                        } catch (NumberFormatException e) {
//                            System.out.println("Invalid input. Parameters remain unchanged.");
//                        }
//                    }
//                    return null;
//                });
//
//                dialog.showAndWait();
//            }
//        }
//
//
//    }

//    public void SEARCH(ArrayList<Puppet> puppe) {
//
//        Dialog<Puppet> dialog = new Dialog<>();
//        dialog.setTitle("Search MicroObject");
//
//        TextField nameField = new TextField();
//        TextField damageField = new TextField();
//        TextField healthField = new TextField();
//        ComboBox comboBox = new ComboBox();
//        comboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
//
//
//        nameField.setPromptText("Name");
//        comboBox.setPromptText("Level");
//        damageField.setPromptText("Damage");
//        healthField.setPromptText("HP");
//
//        GridPane grid = new GridPane();
//        grid.add(new Label("Name:"), 0, 0);
//        grid.add(nameField, 1, 0);
//        grid.add(new Label("State"), 0, 1);
//        ;
//        grid.add(new Label("Level:"), 0, 2);
//        grid.add(comboBox, 1, 2);
//        grid.add(new Label("Damage:"), 0, 3);
//        grid.add(damageField, 1, 3);
//        grid.add(new Label(" HP:"), 0, 5);
//        grid.add(healthField, 1, 5);
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
//                    int level = comboBox.getValue() == null ? 0 : Integer.parseInt(String.valueOf(comboBox.getValue()));
//                    double damage = damageField.getText().isEmpty() ? 0 : Double.parseDouble(damageField.getText());
//                    int health = healthField.getText().isEmpty() ? 0 : Integer.parseInt(healthField.getText());
//                    boolean red = false;
//                    boolean blue = false;
//                    boolean netral = true;
//
//                    for (Puppet puppet1 : puppe) {
//                        if (puppet1.getName().equals(name) && puppet1.getLevel() == level && puppet1.getdamage() == damage && puppet1.getHealth() == health) {
//
//                            System.out.println(" Координати по X :" + puppet1.getxPos());
//                            System.out.println("Координати по Y :" + puppet1.getyPos());
//                            if (puppet1.isRed() == true) {
//                                System.out.println("Об'єкт належить до Червоної бази.");
//                            }
//                            if (puppet1.isBlue() == true) {
//                                System.out.println("Об'єкт належить до Cиньої бази бази.");
//                            }
//                            if (puppet1.isNetral() == true) {
//                                System.out.println("Об'єкт не належить до Червоної та Cиньої бази.");
//                            }
//                        }
//                    }
//
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid input. Default preset");
//                }
//            }
//            return null;
//        });
//
//        Optional<Puppet> result = dialog.showAndWait();
//        //result.ifPresent(puppe -> System.out.println("Created"));
//
//
//    }


//    public void Counter(ArrayList<Puppet> puppe) {
//        Dialog<Puppet> dialog = new Dialog<>();
//        dialog.setTitle("COUNTER MICRO");
//
//        CheckBox checkBox0 = new CheckBox();
//        CheckBox checkBox1 = new CheckBox();
//        CheckBox checkBox2 = new CheckBox();
//        CheckBox checkBox3 = new CheckBox();
//
//        GridPane gridPane = new GridPane();
//        gridPane.add(new Label("ActiveMicro:"), 0, 0);
//        gridPane.add(checkBox0, 3, 0);
//        gridPane.add(new Label("NonactiveMicro:"), 0, 2);
//        gridPane.add(checkBox1, 3, 2);
//        gridPane.add(new Label("REd_BAse:"), 0, 3);
//        gridPane.add(checkBox2, 3, 3);
//        gridPane.add(new Label("BLUE_BAse:"), 0, 4);
//        gridPane.add(checkBox3, 3, 4);
//        dialog.getDialogPane().setContent(gridPane);
//
//        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
//        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
//
//        dialog.setResultConverter(dialogButton -> {
//            if (dialogButton == okButtonType) {
//                try {
//                    //int NonactiveMicro = 0;
//                    // int REd_BAse = 0;
//                    // int BLUE_BAse = 0;
//
//                    if (checkBox0.isSelected()) {
//                        long activeMicroObjects = puppe.stream()
//                                .filter(Puppet::isActive)
//                                .filter(p -> !p.isDeleted())
//                                .count();
//
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Active Micro Objects");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Number of Active Micro Objects: " + activeMicroObjects);
//                        alert.showAndWait();
//                    }
//
//                    if (checkBox1.isSelected()) {
//                        long NonactiveMicro = puppe.stream()
//                                .filter(p -> !p.isActive() && !p.isDeleted())
//                                .count();
//
//                        Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
//                        alert0.setTitle("Active Micro Objects");
//                        alert0.setHeaderText(null);
//                        alert0.setContentText("Number of NO-Active Micro Objects: " + NonactiveMicro);
//                        alert0.showAndWait();
//                    }
//
//                    if (checkBox2.isSelected()) {
//                        long REd_BAse = puppe.stream()
//                                .filter(Puppet::isRed )
//                                .filter(p -> !p.isDeleted())
//                                .count();
//
//                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
//                        alert1.setTitle("Active Micro Objects");
//                        alert1.setHeaderText(null);
//                        alert1.setContentText("Number of RED-TEAM Micro Objects: " + REd_BAse);
//                        alert1.showAndWait();
//                    }
//                    if (checkBox3.isSelected()) {
//                        long BLUE_BAse = puppe.stream()
//                                .filter(Puppet::isBlue)
//                                .filter(p -> !p.isDeleted())
//                                .count();
//
//
//                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
//                        alert2.setTitle("Active Micro Objects");
//                        alert2.setHeaderText(null);
//                        alert2.setContentText("Number of BLUE-TEAM Micro Objects: " + BLUE_BAse);
//                        alert2.showAndWait();
//                    }
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//            }
//            return null;
//        });
//        Optional<Puppet> result = dialog.showAndWait();
//
//
//    }


    //СЕРІАЛІЗАЦІЯ/ДЕСЕРІАЛІЗАЦІЯ
    public static void SERIALIZATON(ArrayList<Puppet> puppe, ArrayList<Factory_Krat> Macro, Stage primaryStage) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("SERIALIZATION");
            File file = fileChooser.showOpenDialog(primaryStage);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));

            objectOutputStream.writeObject(puppe);
            objectOutputStream.writeObject(Macro);
            System.out.println("SERIALIZATON : ");
            System.out.println(puppe);
            System.out.println(Macro);
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    public static void DESERIALIZATON(Stage primaryStage) {
//        try {
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.setTitle("DESERIALIZATION");
//            File file = fileChooser.showOpenDialog(primaryStage);
//            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
//
//            ArrayList<Puppet> puppe = (ArrayList<Puppet>) objectInputStream.readObject();
//            ArrayList<Factory_Krat> Macro = (ArrayList<Factory_Krat>) objectInputStream.readObject();
//
//            group.getChildren().clear();
//
//            for (Puppet puppet : puppe) {
//                if (puppet instanceof Lead_of_Parade) {
//                    createLeadOfParadeGraphics((Lead_of_Parade) puppet);
//                } else if (puppet instanceof Puppet_of_the_future) {
//                    createPuppetOfTheFutureGraphics((Puppet_of_the_future) puppet);
//                } else if (puppet instanceof Puppet) {
//                    createPuppetGraphics(puppet);
//                }
//            }
//
//            System.out.println("DESERIALIZATON : ");
//            System.out.println("START");
//            System.out.println("MICRO" + puppe);
//            System.out.println("-----------");
//            System.out.println("MACRO" + Macro);
//            System.out.println("END");
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    private static void createLeadOfParadeGraphics(Lead_of_Parade puppet) {
//        Label labelNameLD = new Label(puppet.getName());
//        labelNameLD.setLayoutX(puppet.getxPos());
//        labelNameLD.setLayoutY(puppet.getyPos());
//
//        Line lifeLD = new Line(puppet.getxPos(), puppet.getyPos() + 15, puppet.getxPos() + puppet.getHealth(), puppet.getyPos() + 15);
//        lifeLD.setStrokeWidth(5);
//        lifeLD.setStroke(Color.LIGHTGREEN);
//
//        Rectangle rectActiveLD = new Rectangle();
//        rectActiveLD.setFill(Color.TRANSPARENT);
//        rectActiveLD.setStrokeWidth(2);
//        rectActiveLD.setStroke(Color.RED);
//        rectActiveLD.setX(puppet.getxPos() - 5);
//        rectActiveLD.setY(puppet.getyPos() - 5);
//        rectActiveLD.setWidth(105);
//        rectActiveLD.setHeight(105 + imageDeltaY());
//
//        ImageView imageViewLD = new ImageView("D:\\ЛАби з ОП\\Lab4\\src\\main\\resources\\com\\example\\lab4\\LeadOfParad.png");
//        imageViewLD.setFitHeight(puppet.getHeight());
//        imageViewLD.setFitWidth(puppet.getWidth());
//
//        puppet.setLabelLF(labelNameLD);
//        puppet.setLife(lifeLD);
//        puppet.setRectActiveLP(rectActiveLD);
//        puppet.setLeadOfParad(imageViewLD);
//        updateGraphics((Puppet) puppet);
//
//        // Додаємо елементи до групи лише один раз
//        group.getChildren().addAll(labelNameLD, lifeLD, rectActiveLD, imageViewLD);
//        puppet.resurrecttt();
//        puppet.move(3, 3);
//        puppet.setCoordinates(95);
//        System.out.println(puppet);
//    }
//
//    private static void createPuppetOfTheFutureGraphics(Puppet_of_the_future puppet) {
//        Label labelNamePF = new Label(puppet.getName());
//        labelNamePF.setLayoutX(puppet.getxPos());
//        labelNamePF.setLayoutY(puppet.getyPos());
//
//        Line lifePF = new Line(puppet.getxPos(), puppet.getyPos() + 15, puppet.getxPos() + puppet.getHealth(), puppet.getyPos() + 15);
//        lifePF.setStrokeWidth(5);
//        lifePF.setStroke(Color.LIGHTGREEN);
//
//        Rectangle rectActivePF = new Rectangle();
//        rectActivePF.setFill(Color.TRANSPARENT);
//        rectActivePF.setStrokeWidth(2);
//        rectActivePF.setStroke(Color.RED);
//        rectActivePF.setX(puppet.getxPos() - 5);
//        rectActivePF.setY(puppet.getyPos() - 5);
//        rectActivePF.setWidth(105);
//        rectActivePF.setHeight(105 + imageDeltaY());
//
//        ImageView imageViewPF = new ImageView("D:\\ЛАби з ОП\\Lab4\\src\\main\\resources\\com\\example\\lab4\\PuppetOfTheFuture.png");
//        imageViewPF.setFitHeight(100);
//        imageViewPF.setFitWidth(100);
//
//        puppet.setLabelPF(labelNamePF);
//        puppet.setLife(lifePF);
//        puppet.setRectActivePF(rectActivePF);
//        puppet.setPuppetOfTheFuture(imageViewPF);
//        updateGraphics((Puppet) puppet);
//
//        // Додаємо елементи до групи лише один раз
//        group.getChildren().addAll(labelNamePF, lifePF, rectActivePF, imageViewPF);
//        puppet.resurrectt();
//        puppet.move(3, 3);
//        puppet.setCoordinates(85);
//        System.out.println(puppet);
//        System.out.println(rectActivePF);
//    }
//
//    private static void createPuppetGraphics(Puppet puppet) {
//        Label labelName = new Label(puppet.getName());
//        labelName.setLayoutX(puppet.getxPos());
//        labelName.setLayoutY(puppet.getyPos());
//
//        Line life = new Line(puppet.getxPos(), puppet.getyPos() + 15, puppet.getxPos() + puppet.getHealth(), puppet.getyPos() + 15);
//        life.setStrokeWidth(5);
//        life.setStroke(Color.LIGHTGREEN);
//
//        Rectangle rectActive = new Rectangle();
//        rectActive.setFill(Color.TRANSPARENT);
//        rectActive.setStrokeWidth(2);
//        rectActive.setStroke(Color.GRAY);
//        rectActive.setX(puppet.getxPos() - 5);
//        rectActive.setY(puppet.getyPos() - 5);
//        rectActive.setWidth(105);
//        rectActive.setHeight(105 + imageDeltaY());
//
//        ImageView imageView = new ImageView(HelloApplication.class.getResource("/com/example/lab4/Puppet.png").toExternalForm());
//        imageView.setFitHeight(100);
//        imageView.setFitWidth(100);
//        imageView.setX(puppet.getxPos() + imageDeltaX());
//        imageView.setY(puppet.getyPos() + imageDeltaY());
//
//        puppet.setLabelName(labelName);
//        puppet.setLife(life);
//        puppet.setRectActive(rectActive);
//        puppet.setPuppetImage(imageView);
//        updateGraphics(puppet);
//
//        // Додаємо елементи до групи лише один раз
//        group.getChildren().addAll(labelName, life, rectActive, imageView);
//        puppet.resurrect();
//        puppet.setCoordinates(puppet.getHealth());
//        puppet.move(3, 3);
//        System.out.println(puppet);
//    }
//
//    private static void updateGraphics(Puppet puppet) {
//        Label label = puppet.getLabelName();
//        if (label != null) {
//            label.setLayoutX(puppet.getxPos());
//            label.setLayoutY(puppet.getyPos());
//        }
//        Line life = puppet.getLife();
//        if (life != null) {
//            life.setStartX(puppet.getxPos());
//            life.setStartY(puppet.getyPos() + 15);
//            life.setEndX(puppet.getxPos() + puppet.getHealth());
//            life.setEndY(puppet.getyPos() + 15);
//        }
//        Rectangle rectActive = puppet.getRectActive();
//        if (rectActive != null) {
//            rectActive.setX(puppet.getxPos() - 5);
//            rectActive.setY(puppet.getyPos() - 5);
//        }
//        ImageView imageView = puppet.getPuppetImage();
//        if (imageView != null) {
//            imageView.setX(puppet.getxPos() + imageDeltaX());
//            imageView.setY(puppet.getyPos() + imageDeltaY());
//        }
//    }

    //  ArrayList<Puppet> puppe, ArrayList<Factory_Krat>Macro
    public static void DESERIALIZATON(Stage primaryStage) throws IOException, ClassNotFoundException {
        try {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("DESERIALIZATION");
            File file = fileChooser.showOpenDialog(primaryStage);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));

            ArrayList<Puppet> puppe = (ArrayList<Puppet>) objectInputStream.readObject();
            ArrayList<Factory_Krat> Macro = (ArrayList<Factory_Krat>) objectInputStream.readObject();

            // group.getChildren().removeAll();
            group.getChildren().clear();

            for (Puppet puppet : puppe) {

                if (puppet instanceof Lead_of_Parade) {
                    // Створюємо нову графіку.
                    Label labelNameLD = new Label();
                    labelNameLD.setText(puppet.getName());
                    labelNameLD.setLayoutX(puppet.getxPos());
                    labelNameLD.setLayoutY(puppet.getyPos());

                    Line lifeLD = new Line(puppet.getxPos(), puppet.getyPos() + 15, puppet.getxPos() + puppet.getHealth(), puppet.getyPos() + 15);
                    lifeLD.setStrokeWidth(5);
                    lifeLD.setStroke(Color.LIGHTGREEN);

                    Rectangle rectActiveLD = new Rectangle(puppet.getxPos() + 5, puppet.getyPos() + 5, 105, 105 + imageDeltaY());
                    rectActiveLD.setFill(Color.RED);
                    rectActiveLD.setStrokeWidth(2);
                    rectActiveLD.setStroke(Color.RED);
                    rectActiveLD.setX(puppet.getxPos());
                    rectActiveLD.setY(puppet.getyPos());
                    rectActiveLD.setWidth(105);
                    rectActiveLD.setHeight(105 + imageDeltaY());

                    ImageView imageViewLD = new ImageView("D:\\ЛАби з ОП\\Lab4\\src\\main\\resources\\com\\example\\lab4\\LeadOfParad.png");
                    imageViewLD.setFitHeight(puppet.getHeight());
                    imageViewLD.setFitWidth(puppet.getWidth());

                    // Перезаписуємо данні в графіку
                    ((Lead_of_Parade) puppet).setLabelLF(labelNameLD);
                    ((Lead_of_Parade) puppet).setLife(lifeLD);
                    ((Lead_of_Parade) puppet).setRectActiveLP(rectActiveLD);
                    ((Lead_of_Parade) puppet).setLeadOfParad(imageViewLD);
                    // Додаємо графіку до GROUP
                    puppet.setxPos(puppet.getxPos());
                    puppet.setyPos(puppet.getyPos());
                    ((Lead_of_Parade) puppet).resurrecttt();
                    puppet.move(3, 3);
                    puppet.setCoordinates(puppet.getHealth());
                    System.out.println(puppet);

//                }
//                else if (puppet instanceof Puppet_of_the_future) {
//                    // Створюємо нову графіку.
//                    Label labelNamePF = new Label();
//                    labelNamePF.setText(puppet.getName());
//                    labelNamePF.setLayoutX(puppet.getxPos());
//                    labelNamePF.setLayoutY(puppet.getyPos());
//
//                    Line lifePF = new Line(puppet.getxPos(),puppet.getyPos()+15, puppet.getxPos()+puppet.getHealth(),puppet.getyPos()+15);
//                    lifePF.setStrokeWidth(5);
//                    lifePF.setStroke(Color.LIGHTGREEN);
//
//                    Rectangle rectActivePF = new Rectangle();
//                    rectActivePF.setFill(Color.TRANSPARENT);
//                    rectActivePF.setStrokeWidth(2);
//                    rectActivePF.setStroke(Color.RED);
//                    rectActivePF.setX(puppet.getLayoutX()-5);
//                    rectActivePF.setY(puppet.getLayoutY()-5);
//                    rectActivePF.setWidth(105);
//                    rectActivePF.setHeight(105 + imageDeltaY());
//
//                    ImageView imageViewPF = new ImageView("D:\\ЛАби з ОП\\Lab4\\src\\main\\resources\\com\\example\\lab4\\PuppetOfTheFuture.png");
//                    imageViewPF.setFitHeight(100);
//                    imageViewPF.setFitWidth(100);
//                    // Перезаписуємо данні в графіку
//                    ((Puppet_of_the_future) puppet).setLabelPF(labelNamePF);
//                     puppet.setLife(lifePF);
//                    ((Puppet_of_the_future) puppet).setRectActivePF(rectActivePF);
//                    ((Puppet_of_the_future) puppet).setPuppetOfTheFuture(imageViewPF);
//                    // Додаємо графіку до GROUP
//                    puppet.setxPos(puppet.getxPos());
//                    puppet.setyPos(puppet.getyPos());
//                    ((Puppet_of_the_future) puppet).resurrectt();
//                    puppet.move(3,3);
//                    puppet.setCoordinates(85);
//                    System.out.println(puppet);
//                    System.out.println(rectActivePF);
//
//                } else if (puppet instanceof Puppet) {
//                    // Створюємо нову графіку.
//                    Label labelName = new Label();
//                    labelName.setText(puppet.getName());
//                    labelName.setLayoutX(puppet.getxPos());
//                    labelName.setLayoutY(puppet.getyPos());
//
//                    Line life = new Line(puppet.getxPos(),puppet.getyPos()+15, puppet.getxPos()+puppet.getHealth(),puppet.getyPos()+15);
//                    life.setStrokeWidth(5);
//                    life.setStroke(Color.LIGHTGREEN);
//                    life.setStartX(puppet.getxPos());
//                    life.setStartY(puppet.getyPos()+15);
//                    life.setEndX(puppet.getxPos()+puppet.getHealth());
//                    life.setEndY(puppet.getyPos()+15);
//
//                    // пробдема з розташуванням
//                    Rectangle rectActive = new Rectangle();
//                    rectActive.setFill(Color.TRANSPARENT);
//                    rectActive.setStrokeWidth(2);
//                    rectActive.setStroke(Color.GRAY);
//                    rectActive.setX(puppet.getLayoutX()-5);
//                    rectActive.setY(puppet.getLayoutY()-5);
//                    rectActive.setWidth(105);
//                    rectActive.setHeight(105 + imageDeltaY());
//
//
//                    ImageView imageView = new ImageView(HelloApplication.class.getResource("/com/example/lab4/Puppet.png").toExternalForm());
//                    imageView.setFitHeight(100);
//                    imageView.setFitWidth(100);
//                    imageView.setX(puppet.getxPos()+imageDeltaX());
//                    imageView.setY(puppet.getyPos()+imageDeltaY());
//
//                    // Перезаписуємо данні в графіку
//                    puppet.setLabelName(labelName);
//                    puppet.setLife(life);
//                    puppet.setRectActive(rectActive);
//                    puppet.setPuppetImage(imageView);
//
////                    puppet.tryActivate(puppet.getxPos(),puppet.getyPos());
////                    puppet.setPosition(puppet.getxPos(),puppet.getyPos());
//
//                    // Додаємо графіку до GROUP
//                    puppet.resurrect();
//                    puppet.setCoordinates(puppet.getHealth());
//                    puppet.move(3,3);
//                    System.out.println(puppet);
//
//                }
                }


//            System.out.println("DESERIALIZATON : ");
//            System.out.println("START");
//            System.out.println("MICRO" + puppe);
//            System.out.println("-----------");
//            System.out.println("MACRO" + Macro);
//            System.out.println("END");
//
//        }catch (Exception e){
//            System.out.println(e);
//        }

                for (Factory_Krat macro : Macro) {

                    if (macro instanceof Tower) {

                        Label labelTOWER = new Label(macro.getName());
                        labelTOWER.setLayoutX(macro.getxPos() + 15);
                        labelTOWER.setLayoutY(macro.getyPos() - 30);

                        Rectangle rectangleTOWER = new Rectangle(100, 22);
                        rectangleTOWER.setFill(Color.TRANSPARENT);
                        rectangleTOWER.setStrokeWidth(3);
                        rectangleTOWER.setStroke(Color.CORAL);
                        rectangleTOWER.setX(macro.getxPos() - 10);
                        rectangleTOWER.setY(macro.getyPos() - 30);

                        ImageView imageViewTOWER = new ImageView("D:\\ЛАби з ОП\\Lab4\\src\\main\\resources\\com\\example\\lab4\\Tower_Radiant_model.png");
                        imageViewTOWER.setFitWidth(90);
                        imageViewTOWER.setFitHeight(100);
                        imageViewTOWER.setX(macro.getxPos());
                        imageViewTOWER.setY(macro.getyPos());

                        ((Tower) macro).setLabelTower(labelTOWER);
                        ((Tower) macro).setRecTower(rectangleTOWER);
                        ((Tower) macro).setTower(imageViewTOWER);
                        ((Tower) macro).seeTower();


                    } else if (macro instanceof Factory_Tark) {

                        Label labelTAVR = new Label(macro.getName());
                        labelTAVR.setLayoutX(macro.getxPos() + 85);
                        labelTAVR.setLayoutY(macro.getyPos());

                        Rectangle rectangleTAVR = new Rectangle(100, 22);
                        rectangleTAVR.setFill(Color.TRANSPARENT);
                        rectangleTAVR.setStrokeWidth(3);
                        rectangleTAVR.setStroke(Color.CORAL);
                        rectangleTAVR.setX(macro.getxPos() + 80);
                        rectangleTAVR.setY(macro.getyPos());

                        ImageView imageViewTAVR = new ImageView("D:\\ЛАби з ОП\\Lab4\\src\\main\\resources\\com\\example\\lab4\\тарк.jpg");
                        imageViewTAVR.setFitHeight(280);
                        imageViewTAVR.setFitWidth(300);
                        imageViewTAVR.setX(macro.getxPos());
                        imageViewTAVR.setY(macro.getyPos());


                        ((Factory_Tark) macro).setLabelTark(labelTAVR);
                        ((Factory_Tark) macro).setRecTark(rectangleTAVR);
                        ((Factory_Tark) macro).setTark(imageViewTAVR);
                        ((Factory_Tark) macro).seeTark();


                    } else if (macro instanceof Factory_Krat) {

                        Label label = new Label(macro.getName());
                        label.setLayoutX(macro.getxPos() + 85);
                        label.setLayoutY(macro.getyPos());

                        Rectangle rectangle = new Rectangle(100, 22);
                        rectangle.setFill(Color.TRANSPARENT);
                        rectangle.setStrokeWidth(3);
                        rectangle.setStroke(Color.CORAL);
                        rectangle.setX(macro.getxPos() + 80);
                        rectangle.setY(macro.getyPos());

                        ImageView imageView = new ImageView("D:\\ЛАби з ОП\\Lab4\\src\\main\\resources\\com\\example\\lab4\\крат.jpg");
                        imageView.setFitHeight(280);
                        imageView.setFitWidth(300);
                        imageView.setX(macro.getxPos());
                        imageView.setY(macro.getyPos());

                        ((Factory_Krat) macro).setLabel(label);
                        ((Factory_Krat) macro).setRec(rectangle);
                        ((Factory_Krat) macro).setKrat(imageView);
                        macro.seeKrat();

                    }

                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}


//    public void deleteActiveMicroObject(ArrayList<Puppet> puppe) {
////        ArrayList<Puppet> puppe1 = new ArrayList<>();
//        for (Puppet ppp: puppe) {
//            HelloApplication.group.getChildren().removeAll(ppp.getLabelName(), ppp.getLife(), ppp.getPuppetImage(), ppp.getRectActive());
//
//        }
//
//    }

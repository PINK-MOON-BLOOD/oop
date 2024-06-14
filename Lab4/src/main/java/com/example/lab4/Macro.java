//package com.example.lab4;
//
//import javafx.scene.Group;
//
//import java.util.ArrayList;
//
//public class Macro {
//
//    private  static Macro instance;
//    private final ArrayList<Factory_Krat>  macro;
//
//    private Macro(){
//        macro = new ArrayList<>();
//        Factory_Krat factory_krat = new Factory_Krat("Factory_Krat", 100, 1500);
//        macro.add(factory_krat);
//
//
//        Factory_Tark factory_tark = new Factory_Tark("Factory_Tark", 4700, 1500 );
//        macro.add(factory_tark);
//
//        Tower tower = new Tower("Tower", 650, 100);
//        macro.add(tower);
//
//        Tower tower1 = new Tower("Tower", 650, 500);
//        macro.add(tower1);
//
//        Tower tower2 = new Tower("Tower", 770, 300);
//        macro.add(tower2);
//
//        Tower tower3 = new Tower("Tower", 900, 100);
//        macro.add(tower3);
//
//        Tower tower4 = new Tower("Tower", 900, 500);
//        macro.add(tower4);
//    }
///*
//    public void drawAllMacroObjects(Group drawmacro) {
//        for (Factory_Krat Object : macro) {
//            Object.draw(drawmacro);
//           // Object.loadImage();
//        }
//    }*/
//    public static synchronized Macro getInstance() {
//        if (instance == null) {
//            instance = new Macro();
//        }
//        return instance;
//    }
//
//}

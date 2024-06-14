package com.example.lab4;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Random;

import static com.example.lab4.HelloApplication.*;
import static com.example.lab4.HelloApplication.puppetOfTheFuture;

public class Puppet_of_the_future extends Puppet {

    private static final long serialVersionUID = -6417921819753105559L;
    transient  private ImageView puppetOfTheFuture;
    transient private Rectangle rectActivePF;
    transient  private Label labelPF;
    transient  private Label labelST;
    transient private Line life;
    public static double imageDeltaX() {
        return 0.0;
    }
    public static double imageDeltaY() {
        return 15;
    }
    //StateObj stateObjPF = new StateObj();
    private boolean deleted;
    private boolean isActivePF = false;

    //виклик конструктора базового класу
    public Puppet_of_the_future(String name, int level, double damage, int health, double xPos, double yPos, boolean red ,boolean blue , boolean netral) {
        super( name, level, damage, health, xPos, yPos,red,blue,netral);
       // System.out.println("Конструктор похідного класу");
        stateObj.setStateObj(getHealth());
        labelST = new Label(stateObj.getStateObj());
        labelST.setLayoutX(xPos + 60);
        labelST.setLayoutY(yPos);

        labelPF = new Label(name);
        labelPF.setLayoutX(xPos);
        labelPF.setLayoutY(yPos);

        isActivePF = false;

        life = new Line(getxPos(), getyPos() + 15, getxPos() + health, getyPos() + 15);
        life.setStrokeWidth(5);
        life.setStroke(Color.LIGHTGREEN);
        puppetOfTheFuture = new ImageView(HelloApplication.puppetOfTheFuture);
        puppetOfTheFuture.setX(getX() + imageDeltaX());
        puppetOfTheFuture.setY(getY() + imageDeltaY());
        rectActivePF = new Rectangle(getxPos() + 5, getyPos() + 5, 105, 105 + imageDeltaY());
        rectActivePF.setFill(Color.TRANSPARENT);
        rectActivePF.setStrokeWidth(2);
        rectActivePF.setStroke(Color.GRAY);
    }

//    public void load(){
//        puppetOfTheFuture.setX(getX() + imageDeltaX());
//        puppetOfTheFuture.setY(getY() + imageDeltaY());
//    }

    public void resurrectt() {
        HelloApplication.group.getChildren().addAll(labelPF,labelST, life, puppetOfTheFuture);
        if (isActive()) HelloApplication.group.getChildren().add(rectActivePF);
    }

    public boolean flipActivation1() {
        boolean currentlyActive1 = isActive(); // Зберегти поточний стан активації
        if(currentlyActive1) {
            HelloApplication.group.getChildren().remove(rectActivePF);
            System.out.println("microObject(PuppetOfTheFuture) is not active");
        } else {
            HelloApplication.group.getChildren().add(rectActivePF);
            System.out.println("microObject(PuppetOfTheFuture) is active");
        }
        setActive(!currentlyActive1); // Встановити новий стан активації
        return isActive(); // Повернути новий стан активації
    }

    // Як що додати аргемент в метод то це буде ститичний полік.
    public boolean tryActivate(double mx, double my) {
        if (puppetOfTheFuture.boundsInParentProperty().get().contains(mx, my)) {
            flipActivation1();
            return true;
        }
        return false;
    }


    public void setCoordinates(int health, int level) {
        labelST.setLayoutX(puppetOfTheFuture.getX()+60);
        labelST.setLayoutY(puppetOfTheFuture.getY());
        labelPF.setLayoutX(puppetOfTheFuture.getX());
        labelPF.setLayoutY(puppetOfTheFuture.getY());
        life.setStartX(puppetOfTheFuture.getX());
        life.setStartY(puppetOfTheFuture.getY() + 15);
        life.setEndX(puppetOfTheFuture.getX() + health);
        life.setEndY(puppetOfTheFuture.getY() + 15);
        puppetOfTheFuture.setX(puppetOfTheFuture.getX() + imageDeltaX());
        puppetOfTheFuture.setY(puppetOfTheFuture.getY() + imageDeltaY());
        rectActivePF.setX(puppetOfTheFuture.getX() );
        rectActivePF.setY(puppetOfTheFuture.getY() );
    }

    //виклик методу базового класу чи динамічний полік?
    @Override
    public void  move(double dx, double dy){
        super.move( dx, dy);
        setCoordinates(getHealth(),getLevel());
        // System.out.println("Active micro-object(PuppetOfTheFutures) move");
    }

    int directionPF = new Random().nextInt(360);
    public void moveAutoPF() {
        double radians = Math.toRadians(directionPF);
        double dx = keyStepX * Math.cos(radians);
        double dy = keyStepY * Math.sin(radians);

        puppetOfTheFuture.setX(puppetOfTheFuture.getX() + dx);
        puppetOfTheFuture.setY(puppetOfTheFuture.getY() + dy);
        setCoordinates(getHealth(), getLevel());
        checkBoundsPF();  // Викликаємо checkBoundsPF() для обробки перевірки та виправлення меж
    }

    private void checkBoundsPF() {
        if (puppetOfTheFuture.getX() < 0 || puppetOfTheFuture.getX() > 5000) {
            directionPF = 180 - directionPF;
            puppetOfTheFuture.setX(Math.max(0, Math.min(puppetOfTheFuture.getX(), 0)));
        }
        if (puppetOfTheFuture.getY() < 0 || puppetOfTheFuture.getY() > 3000) {
            directionPF = 360 - directionPF;
            puppetOfTheFuture.setY(Math.max(0, Math.min(puppetOfTheFuture.getY(), 0)));
        }

        // Вирівнюємо directionPF у межах [0, 360)
        if (directionPF < 0) {
            directionPF += 360;
        }
        if (directionPF >= 360) {
            directionPF -= 360;
        }
    }

    // Динамічний полік
    @Override
    public void handleEscKeyPress() {
        boolean Active1 = isActive();
        if (isActive()) {
            HelloApplication.group.getChildren().remove(rectActivePF);
            setActive(!Active1);
            System.out.println("Active micro-object(PuppetOfTheFuture) state non-Active.");
        }
    }
    public void deleteActiveMicroObject() {
        if (isActive()) {  HelloApplication.group.getChildren().removeAll(labelPF, life, puppetOfTheFuture, labelST);
            if(isActive())HelloApplication.group.getChildren().remove(rectActivePF);
            setDeleted(true);
        }


    }


    public ImageView getPuppetOfTheFuture() {
        return puppetOfTheFuture;
    }

    public void setPuppetOfTheFuture(ImageView puppetOfTheFuture) {
        this.puppetOfTheFuture = puppetOfTheFuture;
    }

    public Rectangle getRectActivePF() {
        return rectActivePF;
    }

    public void setRectActivePF(Rectangle rectActivePF) {
        this.rectActivePF = rectActivePF;
    }

    public Label getLabelPF() {
        return labelPF;
    }

    public void setLabelPF(Label labelPF) {
        this.labelPF = labelPF;
    }

    public Line getLife() {
        return life;
    }

    public void setLife(Line life) {
        this.life = life;
    }

    @Override
    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public  void RecYELLOW(){
        rectActivePF.setStroke(Color.YELLOW);
    }
    public  void RecRED(){
        rectActivePF.setStroke(Color.RED);
    }
    public  void RecGray(){
        rectActivePF.setStroke(Color.GRAY);
    }
    public  void RecBLUE(){
        rectActivePF.setStroke(Color.BLUE);
    }

    public  void TextRED(){
        labelPF.setTextFill(Color.RED);
    }
    public  void TextBlue(){
        labelPF.setTextFill(Color.BLUE);
    }
    public  void OriginalText(){
        labelPF.setTextFill(Color.BLACK);
    }

}

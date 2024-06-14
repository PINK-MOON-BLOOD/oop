package com.example.lab4;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import static com.example.lab4.HelloApplication.*;

public class Lead_of_Parade extends Puppet_of_the_future{
    private static final long serialVersionUID =2509298587758378259L;

    transient  private ImageView LeadOfParad;
    transient private Rectangle rectActiveLP;
    transient private Label labelLF;
    transient private Label labelST;
    private boolean deleted;
    transient  private Line life;
    //виклик конструктора базового класу
    public Lead_of_Parade(String name, int level, double damage, int health, double xPos, double yPos ,boolean red ,boolean blue , boolean netral) {
        super( name, level,damage, health, xPos, yPos,red,blue,netral);
       // System.out.println("Конструктор похідного класу");
        stateObj.setStateObj(getHealth());
        labelST = new Label(stateObj.getStateObj());
        labelST.setLayoutX(xPos + 60);
        labelST.setLayoutY(yPos);

        LeadOfParad = new ImageView(HelloApplication.LeadOfParad);
        LeadOfParad.setX(getX()+imageDeltaX());
        LeadOfParad.setY(getY()+imageDeltaY());
        labelLF = new Label(name);
        labelLF.setLayoutX(xPos);
        labelLF.setLayoutY(yPos);
        life = new Line(getxPos(),getyPos()+15, getxPos()+health,getyPos()+15);
        life.setStrokeWidth(5);
        life.setStroke(Color.LIGHTGREEN);
        rectActiveLP= new Rectangle(getX()+5,getY()+5,105,105+imageDeltaY());
        rectActiveLP.setFill(Color.TRANSPARENT);
        rectActiveLP.setStrokeWidth(2);
        rectActiveLP.setStroke(Color.GRAY);

    }

    public void resurrecttt(){
        HelloApplication.group.getChildren().addAll(labelLF,labelST, life, LeadOfParad);
         if(isActive())HelloApplication.group.getChildren().add(rectActiveLP);
    }
    public boolean flipActivation2() {
        boolean currentlyActive2 = isActive(); // Зберегти поточний стан активації
        if(currentlyActive2) {
            HelloApplication.group.getChildren().remove(rectActiveLP);
            System.out.println("microObject(LeadOfParad) is not active");
        } else {
            HelloApplication.group.getChildren().add(rectActiveLP);
            System.out.println("microObject(LeadOfParad) is  active");
        }
        setActive(!currentlyActive2); // Змінити стан активації
        return isActive(); // Повернути новий стан активації
    }
    public boolean tryActivate( double mx, double my ){
        if(LeadOfParad.boundsInParentProperty().get().contains(mx,my)){
            flipActivation2();
            return true;
        }
        return false;
    }

    public void setCoordinates(int health ,boolean red ,boolean blue , boolean netral){
        labelST.setLayoutX(LeadOfParad.getX()+60);
        labelST.setLayoutY(LeadOfParad.getY());
        labelLF.setLayoutX(LeadOfParad.getX());
        labelLF.setLayoutY(LeadOfParad.getY());
        life.setStartX(LeadOfParad.getX());
        life.setStartY(LeadOfParad.getY()+15);
        life.setEndX(LeadOfParad.getX()+health);
        life.setEndY(LeadOfParad.getY()+15);
        LeadOfParad.setX(LeadOfParad.getX()+imageDeltaX());
        LeadOfParad.setY(LeadOfParad.getY()+imageDeltaY());
        rectActiveLP.setX(LeadOfParad.getX());
        rectActiveLP.setY(LeadOfParad.getY());
    }


   //Виклик методу базового класу чи динамічний полік?
    @Override
    public void move( double dx, double dy ){
        super.move(dx,dy);
        setCoordinates(getHealth(),isRed(),isBlue(),isNetral());
        checkBoundsLD();
        //System.out.println("Active micro-object(LeadOfParad) move");
    }

    public void moveAutoLD() {
        // Оновлюємо позиції на основі швидкості та напрямку
        double radians = Math.toRadians(direction);
        double dx = keyStepX * Math.cos(radians);
        double dy = keyStepY * Math.sin(radians);

        LeadOfParad.setX(LeadOfParad.getX() + dx);
        LeadOfParad.setY(LeadOfParad.getY() + dy);
        setCoordinates(getHealth(),isRed(),isBlue(),isNetral());


        // Додаємо логіку для зміни напрямку при досягненні краю області
        checkBoundsLD();
    }
    private void checkBoundsLD() {
        // Приклад обмежень для області розміром 800x600
        if ((LeadOfParad.getX() < 0) || (LeadOfParad.getX() > 5000)) {
            direction = 180 - direction; // Зміна напрямку при відбитті від вертикальної межі
            LeadOfParad.setX(Math.max(0, Math.min(LeadOfParad.getX(), 0)));
        }
        if ((LeadOfParad.getY() < 0) || (LeadOfParad.getY() > 3000)) {
            direction = 360 - direction; // Зміна напрямку при відбитті від горизонтальної межі
            LeadOfParad.setY(Math.max(0, Math.min(LeadOfParad.getY(), 0)));
        }
        // Забезпечення, що напрямок залишається в межах 0-360 градусів
        if (direction < 0) {
            direction += 360;
        }
        if (direction >= 360) {
            direction -= 360;
        }
    }
    // Динамічний полік
    @Override
    public void handleEscKeyPress() {
        boolean Active2 = isActive();
        if (isActive()) {
            HelloApplication.group.getChildren().remove(rectActiveLP);
            setActive(!Active2);
            System.out.println("Active micro-object state(LeadOfParad) non-Active.");
        }
    }

    public void deleteActiveMicroObject() {
        boolean act2 = isActive();
        if (isActive()) {  HelloApplication.group.getChildren().removeAll(labelLF, life, LeadOfParad, labelST);
            if(isActive())HelloApplication.group.getChildren().remove(rectActiveLP);
            setDeleted(true);
            //System.out.println("Active micro-object(LeadOfParad) is deleted.");
        }

    }

    public ImageView getLeadOfParad() {
        return LeadOfParad;
    }

    public void setLeadOfParad(ImageView leadOfParad) {
        LeadOfParad = leadOfParad;
    }

    public Rectangle getRectActiveLP() {
        return rectActiveLP;
    }

    public void setRectActiveLP(Rectangle rectActiveLP) {
        this.rectActiveLP = rectActiveLP;
    }

    public Label getLabelLF() {
        return labelLF;
    }

    public void setLabelLF(Label labelLF) {
        this.labelLF = labelLF;
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

    public  void RecRED(){
        rectActiveLP.setStroke(Color.RED);
    }
    public  void RecGray(){
        rectActiveLP.setStroke(Color.GRAY);
    }
    public  void RecBLUE(){
        rectActiveLP.setStroke(Color.BLUE);
    }

    public  void TextRED(){
        labelLF.setTextFill(Color.RED);
    }
    public  void TextBlue(){
        labelLF.setTextFill(Color.BLUE);
    }
    public  void OriginalText(){
        labelLF.setTextFill(Color.BLACK);
        return ;
    }

}

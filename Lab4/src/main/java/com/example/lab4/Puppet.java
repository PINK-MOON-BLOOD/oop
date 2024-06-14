    package com.example.lab4;

    import javafx.scene.Node;
    import javafx.scene.control.*;
    import javafx.scene.image.ImageView;
    import javafx.scene.paint.Color;
    import javafx.scene.shape.Line;
    import javafx.scene.shape.Rectangle;

    import java.io.Serializable;
    import java.util.*;

    import static com.example.lab4.HelloApplication.*;

    public class Puppet extends Node implements Cloneable, Comparable<Puppet>, Serializable {

        //private static final long serialVersionUID = 6893502611794759743L;
        private static final long serialVersionUID = 123456789L;
          protected double x, y;
         private double kut;
           private  boolean netral;
         private  boolean blue;
          private  boolean red;
        private int Health;
        private String name;
        private double damage;
        private int level;
        private static boolean deleted;

        public StateObj stateObj = new StateObj();

       // private String stateObj;
       //transient ImageView puppetImage;
        private double xPos;
        private double yPos;
        private int width;
        private int height;
        private boolean isActive = false;
        transient private Label labelName;
        private  Label labelState;
       transient private Line life;
        transient private ImageView puppet;
        transient private Rectangle rectActive;

        //------------------------------------

        // Copy constructor
        public Puppet(Puppet puppet) {
            this.Health = puppet.Health;
            this.name = puppet.name;
            this.damage = puppet.damage;
            this.level = puppet.level;
            this.stateObj = new StateObj(puppet.stateObj.getStateObj());
            this.xPos = puppet.xPos;
            this.yPos = puppet.yPos;
            this.red = puppet.red;
            this.blue = puppet.blue;
            this.netral = puppet.netral;

            this.labelName = new Label(puppet.labelName.getText());
            this.labelState = new Label(puppet.labelState.getText());
            this.life = new Line(puppet.life.getStartX(), puppet.life.getStartY(), puppet.life.getEndX(), puppet.life.getEndY());
            this.puppet = new ImageView(puppet.puppet.getImage());
            this.isActive = puppet.isActive;
            this.rectActive = new Rectangle(puppet.rectActive.getX(), puppet.rectActive.getY(), puppet.rectActive.getWidth(), puppet.rectActive.getHeight());


            System.out.println("Constructor Puppet(String name) was called. An object was created with parameters: " + toString());
            System.out.println();

        }

        // Метод  копіювання
        @Override
       protected Object clone() throws CloneNotSupportedException {
            Puppet clonedPuppet = (Puppet) super.clone();
            clonedPuppet.name = new String(this.name);
            clonedPuppet.stateObj = (StateObj) this.stateObj.clone();
            clonedPuppet.labelName = new Label(this.labelName.getText());
            clonedPuppet.labelState = new Label(this.labelState.getText());
            clonedPuppet.life = new Line(this.life.getStartX(), this.life.getStartY(), this.life.getEndX(), this.life.getEndY());
            clonedPuppet.puppet = new ImageView(this.puppet.getImage());
            clonedPuppet.rectActive = new Rectangle(this.rectActive.getX(), this.rectActive.getY(), this.rectActive.getWidth(), this.rectActive.getHeight());
            return clonedPuppet;
        }


        //------------------------------------
        private static boolean alive;
        public static boolean isAlive() {
            return alive;
        }
        public static void setAlive(boolean alive) {

            Puppet.alive = alive;
        }

        //Статичний блок
        static {
            System.out.println("Виконався статичний блок ініціалізації Puppet ");
            setAlive(true);


        }
        // Нестатичний блок
        {
            System.out.println("Виконався нестатичний блок ініціалізації Puppet ");
            setAlive(true);
        }

    //-----------------------------------------------

        // Перший конструктор.
        public static double imageDeltaX(){
            return 0.0;
        }
        public static double imageDeltaY(){
            return 15;
        }
/*
        public Puppet(int health, String name, double damage, int level, String stateObj) {
            Health = health;
            this.name = name;
            this.damage = damage;
            this.level = level;
            this.stateObj = stateObj;

        }*/

        public Puppet() {
            this("Puppet",1,10,100,100,100,false,false,true);
            System.out.println("Викликано конструктор Puppet(). Створено " + toString());
        }
        public Puppet(String name, int level, double damage,int health, double xPos, double yPos, boolean red ,boolean blue , boolean netral){

            stateObj.setStateObj(getHealth());
            this.name = name;
            this.level = level;
            this.damage = damage;
            Health = health;
            this.xPos = xPos;
            this.yPos = yPos;
            this.red = red;
            this.blue = blue;
            this.netral = netral;

            labelName = new Label(name);
            labelName.setLayoutX(xPos);
            labelName.setLayoutY(yPos);
            labelState = new Label(stateObj.getStateObj());
            labelState.setLayoutX(xPos + 70);
            labelState.setLayoutY(yPos);

            life = new Line(getxPos(),getyPos()+15, getxPos()+health,getyPos()+15);
            life.setStrokeWidth(5);
            life.setStroke(Color.LIGHTGREEN);

            puppet = new ImageView(HelloApplication.puppet);
            puppet.setX(getX()+imageDeltaX());
            puppet.setY(getY()+imageDeltaY());

            isActive=false;

            rectActive= new Rectangle(getX()+5,getY()+5,105,105+imageDeltaY());
            rectActive.setFill(Color.TRANSPARENT);
            rectActive.setStrokeWidth(2);
            rectActive.setStroke(Color.GRAY);

           // System.out.println("Конмтруктор базового класу.");

        }

        public void resurrect(){
            HelloApplication.group.getChildren().addAll(labelName,labelState, life, puppet);
            if(isActive)HelloApplication.group.getChildren().add(rectActive);
            //System.out.println("Метод базового класу");
        }


        public boolean flipActivation(){
            if(isActive){
                HelloApplication.group.getChildren().remove(rectActive);
                System.out.println("microObject(Puppet) is not active");
            }
            else{
                HelloApplication.group.getChildren().add(rectActive);
                System.out.println("microObject(Puppet) is active");
            }
            isActive = !isActive;

            return isActive;
        }
        public boolean tryActivate( double mx, double my ){
            if(puppet.boundsInParentProperty().get().contains(mx,my)){
                flipActivation();
                return true;
            }
            return false;
        }

        public void setCoordinates(int health){
            labelName.setLayoutX(getX());
            labelName.setLayoutY(getY());
            labelState.setLayoutX(getX()+70);
            labelState.setLayoutY(getY());
            life.setStartX(getX());
            life.setStartY(getY()+15);
            life.setEndX(getX()+health);
            life.setEndY(getY()+15);
            puppet.setX(getX()+imageDeltaX());
            puppet.setY(getY()+imageDeltaY());
            rectActive.setX(getX()-5);
            rectActive.setY(getY()-5);

        }


        // Використовується як динамічний полік.
        // І ЯК ВИКОРИСТАННЯ ВІРТУАЛЬНИЙ ФУНКЦІЙ.
        public void move( double dx, double dy ){
            setxPos(getxPos() + dx);
            setyPos(getyPos() + dy);
            setCoordinates(getHealth());
            checkBounds();
            //System.out.println("Active micro-object(Puppet) move");
        }
        ///
        int direction = new Random().nextInt(360);
        public void moveAuto() {
            // Оновлюємо позиції на основі швидкості та напрямку
            double radians = Math.toRadians(direction);
            double dx = keyStepX * Math.cos(radians);
            double dy = keyStepY * Math.sin(radians);

            setxPos(getxPos() + dx);
            setyPos(getyPos() + dy);
            setCoordinates(getHealth());
            // Додаємо логіку для зміни напрямку при досягненні краю області
            checkBounds();
        }
        private void checkBounds() {
            // Приклад обмежень для області розміром 5000x3000
            if (xPos < 0 || xPos > 5000) {
                direction = 180 - direction; // Зміна напрямку при відбитті від вертикальної межі
                xPos = Math.max(0, Math.min(xPos, 5000));
            }
            if (yPos < 0 || yPos > 3000) {
                direction = 360 - direction; // Зміна напрямку при відбитті від горизонтальної межі
                yPos = Math.max(0, Math.min(yPos, 3000));
            }

            // Забезпечення, що напрямок залишається в межах 0-360 градусів
            if (direction < 0) {
                direction += 360;
            }
            if (direction >= 360) {
                direction -= 360;
            }
        }
        ////

        public void deleteActiveMicroObject() {
            if (isActive) {  HelloApplication.group.getChildren().removeAll(labelName, life, puppet, labelState);
                if(isActive)HelloApplication.group.getChildren().remove(rectActive);
                setDeleted(true);

                System.out.println("Active micro-object(Puppet) is deleted.");}
            System.out.println(deleted);

        }


        // Батьківський клас з методом  handleEscKeyPress()
        public void handleEscKeyPress() {
            if (isActive) {
                // Remove active micro-object from the scene
                HelloApplication.group.getChildren().remove(rectActive);
                isActive = false;
                System.out.println("Active micro-object(Puppet) state non-Active.");
            }
        }


        public static void updateMicroObjectsLabel() {
            StringBuilder stringBuilder = new StringBuilder("Список мікрооб'єктів:\n");
            for (Puppet microObject : puppe) {
                stringBuilder.append(microObject.getName()).append("\n");
            }
            microObjectsLabel.setText(stringBuilder.toString());
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Puppet puppet)) return false;
            return getHealth() == puppet.getHealth() && Double.compare(puppet.damage, damage) == 0
                    && getLevel() == puppet.getLevel() && Objects.equals(getName(), puppet.getName())
                    && Objects.equals(getStateObj(), puppet.getStateObj());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getHealth(), getName(), damage, getLevel(), getStateObj());
        }


        // функція String toString()

        @Override
        public String toString() {
            return "Puppet{" +
                    "Health=" + Health +
                    ", name='" + name + '\'' +
                    ", damage=" + damage +
                    ", level=" + level +
                    ", xPos=" + xPos +
                    ", yPos=" + yPos +
                    ", isActive=" + isActive +
                    ", isNetral="+ isNetral() +
                    ", isBlue="+ isBlue() +
                    ", isRed="+ isRed() +
                    '}'+"\n";
        }



        //АВТОМАТИЧНИЙ РУХ
        protected   void changeCords() {
        if(!isActive) {
            x += Math.sin(kut * (Math.PI / 180)) * 3;
            y -= Math.cos(kut * (Math.PI / 180)) * 3;
        }

        if(x > 1920 * 2 - 135) x = 1920 * 2 - 135;
        if(x < 10) x = 10;
        if(y > 1080 * 2 - 125) y = 1080 * 2 - 125;
        if(y < 30) y = 30;

        puppet.setX(x);
        puppet.setY(y);

        life.setLayoutX(x);
        life.setLayoutY(y - 20);
        labelName.setLayoutX(x + 10);
        labelName.setLayoutY(y);
        rectActive.setLayoutX(x);
        rectActive.setLayoutY(y);

           }
        protected void moveTo(double x, double y) {
            kut = Math.atan2(y - this.y, x - this.x) * 180 / Math.PI + 90;
        }
        public void interaction() {

                if (!isActive) { //TODO: if something forbids interaction it goes here

                    changeCords();
                }

            }




        // INTERACT
        public Puppet interactWithAll(Puppet puppet) {
            if (puppet instanceof Lead_of_Parade) {
                if (puppet.netral || puppet.blue  ) {
                  //  puppet.deleteActiveMicroObject();
                    puppet.setHealth(getHealth()-5);
                }
            } else if (puppet instanceof Puppet_of_the_future) {
                if (puppet.netral || puppet.blue) {
                    puppet.deleteActiveMicroObject();
                }
            } else if (puppet instanceof Puppet) {
                if (puppet.netral || puppet.blue) {
                    puppet.deleteActiveMicroObject();
                }
            }
            return puppet;
        }


                // Сеттери і Геттери.
        public int getHealth() {
            return Health;
        }

        public void setHealth(int health) {
            Health = health;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getdamage() {
            return damage;
        }

        public void setdamage(double damage) {
            this.damage = damage;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getStateObj() {
            return null;
        }

//        public void setStateObj(String stateObj) {
//            this.stateObj = stateObj;
//        }
         //Виводить на екран стан об'єкта.

//        public ImageView getImageIcon() {
//            return imageIcon;
//        }
//
//        private void setImageIcon(ImageView imageIcon) {
//            this.imageIcon = imageIcon;
//        }


        public void setStateObj(StateObj stateObj) {
            this.stateObj = stateObj;
        }

        protected double getX() {
            return xPos;
        }

        protected double getY() {
            return yPos;
        }
        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public double getxPos() {
            return xPos;
        }

        public void setxPos(double xPos) {
            this.xPos = xPos;
        }

        public double getyPos() {
            return yPos;
        }

        public void setyPos(double yPos) {
            this.yPos = yPos;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }


        public ImageView getPuppetImage() {
            return puppet;
        }

        public void setPuppetImage(ImageView puppet) {
            this.puppet = puppet;
        }

        public void print() {
            System.out.println(toString());
        }

        public void setDamage(double damage) {
            this.damage = damage;
        }
        public void setPosition(double newXPos, double newYPos) {
            xPos = newXPos;
            yPos = newYPos;
        }


        protected void setActive() {
            isActive = !isActive;
            if(isActive) {
                System.out.println(this + "activated");
            }else{
                System.out.println(this + "deactivated");
            }
        }



        public static class SBComparator implements Comparator<Puppet> {

            @Override
            public int compare(Puppet o1, Puppet o2) {
                if(o1.level<o2.level)return -1;
                if(o1.level>o2.level)return 1;

                return 0;
            }
        }

    public int compareTo(Puppet o){
        return this.name.compareTo(o.name);
    }

        public Label getLabelState() {
            return labelState;
        }

        public void setLabelState(Label labelState) {
            this.labelState = labelState;
        }

        public Label getLabelName() {
            return labelName;
        }

        public void setLabelName(Label labelName) {
            this.labelName = labelName;
        }

        public Line getLife() {
            return life;
        }

        public void setLife(Line life) {
            this.life = life;
        }

        public ImageView getPuppet() {
            return puppet;
        }

        public void setPuppet(ImageView puppet) {
            this.puppet = puppet;
        }

        public Rectangle getRectActive() {
            return rectActive;
        }

        public void setRectActive(Rectangle rectActive) {
            this.rectActive = rectActive;
        }

        public boolean isNetral() {
            return netral;
        }

        public void setNetral(boolean netral) {
            this.netral = netral;
        }

        public boolean isBlue() {
            return blue;
        }

        public void setBlue(boolean blue) {
            this.blue = blue;
        }

        public boolean isRed() {
            return red;
        }

        public void setRed(boolean red) {
            this.red = red;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public  void RecRED(){
            rectActive.setStroke(Color.RED);
        }
        public  void RecBLUE(){
            rectActive.setStroke(Color.BLUE);
        }
        public  void RecGray(){
            rectActive.setStroke(Color.GRAY);
        }
        public  void TextRED(){
            labelName.setTextFill(Color.RED);
        }
        public  void TextBlue(){
            labelName.setTextFill(Color.BLUE);
        }
        public  void OriginalText(){
            labelName.setTextFill(Color.BLACK);
        }



    }




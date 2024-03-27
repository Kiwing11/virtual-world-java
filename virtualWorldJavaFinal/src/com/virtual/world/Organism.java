package com.virtual.world;


import java.awt.*;
import java.util.Random;

import static com.virtual.world.C.*;

public abstract class Organism {
    protected int strength;
    protected int initiative;
    protected int age = 0;
    protected int range = C.DEFAULT_RANGE;
    protected int chanceToMove = 100;
    protected int chanceToSpread = 10;
    //KIERUNEK
    protected char symbol;
    protected String name;
    protected Color color;
    protected World whichWorld;
    protected Point pos;
    protected boolean isAlive = true;
    protected boolean specialAbility = false;
    protected boolean canReproduce = true;
    protected int timeToNextReproduce = 0;

    protected boolean[] avaiableDirections = {true, true, true, true};

    public Organism(){}
    public Organism(World _whichWorld, int _strength, int _initiative,int _age, Point _pos){
        whichWorld = _whichWorld;
        strength = _strength;
        initiative = _initiative;
        age = _age;
        pos = _pos;
    }
    public Organism(World world, Point position){
        whichWorld = world;
        pos = position;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getAge() {
        return age;
    }

    public Point getPos(){
        return pos;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRange() {
        return range;
    }

    public int getChanceToSpread() {
        return chanceToSpread;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setPos(int x, int y){
        pos.setLocation(x,y);
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point getRandomPos(Point currentPos){
        int x = currentPos.x;
        int y = currentPos.y;
        Point newPos = new Point(x,y);
        boolean[] freeDirections = new boolean[C.TOTAL_DIRECTIONS];
        for(int i = 0; i < C.TOTAL_DIRECTIONS; i++){
            freeDirections[i] = this.avaiableDirections[i];
        }

        //CZY ZWIERZE SIE PORUSZY
        Random rand = new Random();
        int r = rand.nextInt(100+1);
        if(r > this.chanceToMove){
            return currentPos;
        }

        while(true){
            if(!freeDirections[UP] && !freeDirections[C.RIGHT] && !freeDirections[C.DOWN] && !freeDirections[C.LEFT])
                break;
            r = rand.nextInt(C.TOTAL_DIRECTIONS); // 4 = 3+1

            //MIEJCE NA R CZLOWIEKA

            switch(r){
                case UP:
                    if((y == 0) || (y == (0 + this.range - DEFAULT_RANGE))  || !freeDirections[UP])
                        freeDirections[UP] = false;
                    else{
                        newPos.setLocation(x, y - this.range);
                        return newPos;
                    }
                    break;
                case RIGHT:
                    if((x == whichWorld.getX() - this.range) || (x == whichWorld.getX() - this.range + DEFAULT_RANGE)  || !freeDirections[RIGHT])
                        freeDirections[RIGHT] = false;
                    else{
                        newPos.setLocation(x + this.range, y);
                        return newPos;
                    }
                    break;
                case DOWN:
                    if((y == whichWorld.getY() - this.range) || (y == whichWorld.getY() - this.range + DEFAULT_RANGE)  || !freeDirections[DOWN])
                        freeDirections[DOWN] = false;
                    else{
                        newPos.setLocation(x, y + this.range);
                        return newPos;
                    }
                    break;
                case LEFT:
                    if((x == 0) || (x == 0 + this.range - DEFAULT_RANGE)  || !freeDirections[LEFT])
                        freeDirections[LEFT] = false;
                    else{
                        newPos.setLocation(x - this.range, y);
                        return newPos;
                    }
                    break;
            }
        }
        return newPos;
    }

    public Point getUnoccupiedRandomPos(Point currentPos){ // NIEZAJETE SASIEDNIE POLE WIEC ZAWSZE PRZESUWA SIE O 1 POLE
        int x = currentPos.x;
        int y = currentPos.y;
        Point newPos = new Point(x,y);
        boolean[] freeDirections = new boolean[C.TOTAL_DIRECTIONS];
        for(int i = 0; i < C.TOTAL_DIRECTIONS; i++){
            freeDirections[i] = this.avaiableDirections[i];
        }

        //CZY ORGANIZM SIE PORUSZY
        Random rand = new Random();
        while(true){
            if(!freeDirections[UP] && !freeDirections[C.RIGHT] && !freeDirections[C.DOWN] && !freeDirections[C.LEFT])
                break;
           int r = rand.nextInt(C.TOTAL_DIRECTIONS); // 4 = 3+1

            switch(r){
                case UP:
                    if((y == 0) || !freeDirections[UP])
                        freeDirections[UP] = false;
                    else{
                        if(whichWorld.getBoard()[x][y-DEFAULT_RANGE] == null){
                            newPos.setLocation(x,y-DEFAULT_RANGE);
                            return newPos;
                        }
                        else
                            freeDirections[UP] = false;
                    }
                    break;
                case RIGHT:
                    if((x == whichWorld.getX() - DEFAULT_RANGE) || !freeDirections[RIGHT])
                        freeDirections[RIGHT] = false;
                    else{
                        if(whichWorld.getBoard()[x+DEFAULT_RANGE][y] == null){
                            newPos.setLocation(x+DEFAULT_RANGE, y);
                            return newPos;
                        }
                        else
                            freeDirections[RIGHT] = false;
                    }
                    break;
                case DOWN:
                    if((y == whichWorld.getY() - DEFAULT_RANGE) || !freeDirections[DOWN])
                        freeDirections[DOWN] = false;
                    else{
                        if(whichWorld.getBoard()[x][y+DEFAULT_RANGE] == null){
                            newPos.setLocation(x, y + DEFAULT_RANGE);
                            return newPos;
                        }
                        else
                            freeDirections[DOWN] = false;
                    }
                    break;
                case LEFT:
                    if((x == 0) || !freeDirections[LEFT])
                        freeDirections[LEFT] = false;
                    else{
                        if(whichWorld.getBoard()[x - DEFAULT_RANGE][y] == null){
                            newPos.setLocation(x - DEFAULT_RANGE, y);
                            return newPos;
                        }
                        else{
                            freeDirections[LEFT] = false;
                        }
                    }
                    break;
            }
        }
        return newPos;
    }

    public void action(){}
    public void collision(Organism colliding){}
    public void move(Point where){}
    public void specificCollision(Organism colliding, Organism attacking){}
}

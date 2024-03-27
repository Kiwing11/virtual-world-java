package com.virtual.world;

import com.virtual.world.animals.*;

import java.awt.*;
import java.util.Objects;

public abstract class Animal extends Organism{
    public Animal(){}
    public Animal(World whichWorld, int strength, int initiative,int age, Point pos) {super(whichWorld,strength,initiative,age,pos);}
    public Animal(World whichWorld, Point pos){super(whichWorld,pos);}


    public boolean doesSpiecesMatch(Organism organism){
        return(this.getSymbol() == organism.getSymbol());
    }

    public boolean ifDeflected(Organism defender){
        if(this.getStrength() > defender.getStrength())
            return false;
        if(this.getStrength() < defender.getStrength())
            return true;
        if(this.getStrength() == defender.getStrength())
            return false;

        return true; // NIGDY SIE NIE WYDARZY
    }

    public void reproduce(Organism other){
        if(!this.canReproduce || !other.canReproduce)
            return;

        Point posTmpThis = getUnoccupiedRandomPos(this.getPos());
        Point posTmpOther = getUnoccupiedRandomPos(other.getPos());
        Organism organismTmp = null;

        if(posTmpThis.equals(this.getPos())){
            if(posTmpOther.equals(other.getPos()) ){
                return;
            }
            for(int i = 0; i < C.TOTAL_ANIMAL_SPIECES; i++){
                if(this.getClass() == Sheep.class){
                    organismTmp = new Sheep(whichWorld, posTmpThis);
                }
                else if(this.getClass() == Wolf.class){
                    organismTmp = new Wolf(whichWorld, posTmpThis);
                }
                else if(this.getClass() == Fox.class){
                    organismTmp = new Fox(whichWorld, posTmpThis);
                }
                else if(this.getClass() == Antelope.class){
                    organismTmp = new Antelope(whichWorld, posTmpThis);
                }
                else if(this.getClass() == Turtle.class){
                    organismTmp = new Turtle(whichWorld, posTmpThis);
                }
            }
        }
        else{
            for(int i = 0; i < C.TOTAL_ANIMAL_SPIECES; i++){
                if(this.getClass() == Sheep.class){
                    organismTmp = new Sheep(whichWorld, posTmpOther);
                }
                else if(this.getClass() == Wolf.class){
                    organismTmp = new Wolf(whichWorld, posTmpOther);
                }
                else if(this.getClass() == Fox.class){
                    organismTmp = new Fox(whichWorld, posTmpOther);
                }
                else if(this.getClass() == Antelope.class){
                    organismTmp = new Antelope(whichWorld, posTmpOther);
                }
                else if(this.getClass() == Turtle.class){
                    organismTmp = new Turtle(whichWorld, posTmpOther);
                }
            }
        }
        Description.describeBirth(organismTmp);
        whichWorld.organismsToBeAdded.add(organismTmp);
        this.canReproduce = false;
        this.timeToNextReproduce = 2;
        other.canReproduce = false;
        other.timeToNextReproduce = 2;
    }

    @Override
    public void move(Point newPos){
        int x = newPos.x;
        int y = newPos.y;
        if(!this.getPos().equals(newPos)) {
            whichWorld.getBoard()[this.pos.x][this.pos.y] = null;
            whichWorld.getBoard()[x][y] = this;
            this.pos.setLocation(x, y);
            System.out.println(this.getName() + " przemieszcza sie na pole: [" + x + "," + y + "]");
            Description.describeMove(this);
        }
    }

    @Override
    public void collision(Organism colliding){
        //CZY BEDZIE SIE ROZMNAZAC
        if(Objects.equals(this.getName(), colliding.getName())){
            this.reproduce(colliding);
            return;
        }

        //CZY POSIADA SPECJALNA WLASCIWOSC OBRONY/ATAKU
        if(this.specialAbility == true){
            specificCollision(colliding, this);
            return;
        }
        if(colliding.specialAbility == true){
            colliding.specificCollision(colliding,this);
            return;
        }
        //JESLI NIE TO
        if(!doesSpiecesMatch(colliding)){
            if(!ifDeflected(colliding)){
                if(colliding instanceof Animal){
                    Description.describeKill(this, colliding);
                }
                if(colliding instanceof Plant){
                    Description.describeEating(this, colliding);
                }

                whichWorld.delete(colliding);
                move(colliding.pos);
            }
            else{
                Description.describeKill(colliding,this);
                whichWorld.delete(this);
            }
        }
    }

    @Override
    public void action(){
        int x = this.pos.x;
        int y = this.pos.y;
        Point currentPos = new Point(x,y);
        Point newPos = getRandomPos(currentPos);
        int newX = newPos.x;
        int newY = newPos.y;

        if(whichWorld.getOrganismFromBoard(newX,newY) != null){
            if(whichWorld.getOrganismFromBoard(newX,newY) != this){       // ?
                collision(whichWorld.getOrganismFromBoard(newX,newY));
            }
        }
        else
            move(newPos);
    }
    @Override
    public void specificCollision(Organism colliding, Organism attacking){}
}

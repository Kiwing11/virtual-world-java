package com.virtual.world.animals;

import com.virtual.world.*;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Antelope extends Animal {
    public Antelope(World whichWorld, Point pos){
        super(whichWorld,pos);
        strength = 4;
        initiative = 4;
        name = "Antylopa";
        symbol = 'A';
        color = C.ANTELOPE_COLOR;
        range = 2;
        specialAbility = true;
    }

    public Antelope(World whichWorld, int strength, int initiative,int age, Point pos) {
        super(whichWorld,strength,initiative,age,pos);
        range = 2;
        name = "Antylopa";
        symbol = 'A';
        color = C.ANTELOPE_COLOR;
        specialAbility = true;
    }

    @Override
    public String getName(){return name;}

    @Override
    public char getSymbol(){return symbol;}

    @Override
    public Color getColor(){return color;}

    @Override
    public void specificCollision(Organism colliding, Organism attacking){
        Random rand = new Random();
        if((colliding.getSymbol() != attacking.getSymbol())){
            if(attacking == this){
                if(!ifDeflected(colliding)){
                    Description.describeKill(this,colliding);
                    whichWorld.delete(colliding);
                    move(colliding.getPos());
                }
                else{
                    Description.describeKill(colliding,this);
                    whichWorld.delete(this);
                }
            }
            else {
                if(colliding == this){
                    if(rand.nextInt(2) == 0) {
                        Description.describeRunAway(this, attacking);
                        Point newPos = getUnoccupiedRandomPos(this.pos);
                        attacking.move(this.pos);
                        move(newPos);
                        System.out.println(this.getName() + " ucieka przed atakiem " + attacking.getName());
                    }
                    else{
                        if(ifDeflected(attacking)) {
                            Description.describeKill(attacking, this);
                            whichWorld.delete(this);
                            attacking.move(this.getPos());
                        }
                    }
                }
            }
        }
    }
}

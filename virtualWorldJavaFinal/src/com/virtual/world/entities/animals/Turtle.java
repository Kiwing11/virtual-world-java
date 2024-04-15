package com.virtual.world.entities.animals;

import com.virtual.world.*;
import com.virtual.world.utils.Description;

import java.awt.*;
import java.util.Objects;

public class Turtle extends Animal {
    public Turtle(World whichWorld, Point pos){
        super(whichWorld,pos);
        specialAbility = true;
        chanceToMove = Constants.TURTLE_CHANCE_TO_MOVE;
        strength = 2;
        initiative = 1;
        name = "Zolw";
        symbol = 'Z';
        color = (Color.GREEN);
    }

    public Turtle(World whichWorld, int strength, int initiative,int age, Point pos) {
        super(whichWorld,strength,initiative,age,pos);
        specialAbility = true;
        chanceToMove = Constants.TURTLE_CHANCE_TO_MOVE;
        name = "Zolw";
        symbol = 'Z';
        color = (Color.GREEN);
    }

    @Override
    public String getName(){return name;}

    @Override
    public char getSymbol(){return symbol;}

    @Override
    public Color getColor(){return color;}

    @Override
    public void specificCollision(Organism colliding, Organism attacking){
        if(this == colliding){
            if(attacking.getStrength() < 5){
                //ODBICIE ATAKU
                System.out.println(this.getName()+"["+this.pos.x+","+this.pos.y+"]"+" odbija atak "+attacking.getName()+"["+attacking.getPos().x+","+attacking.getPos().y+"]" );
                Description.describeDeflection(this,attacking);
                return;
            }
            else{
                Description.describeKill(attacking,this);
                whichWorld.delete(this);
                attacking.move(colliding.getPos());
                return;
            }
        }
        if(this == attacking){
            if(!Objects.equals(colliding.getName(), "Zolw")){
                if(!ifDeflected(colliding)){
                    Description.describeKill(this,colliding);
                    whichWorld.delete(colliding);
                    move(colliding.getPos());
                }
                else{
                    whichWorld.delete(this);
                    Description.describeKill(colliding,this);
                }
            }
            else{
                return;
            }
        }
    }
}

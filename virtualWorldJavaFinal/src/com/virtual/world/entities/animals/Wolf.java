package com.virtual.world.entities.animals;

import com.virtual.world.Animal;
import com.virtual.world.World;

import java.awt.*;

public class Wolf extends Animal {
    public Wolf(World whichWorld, Point pos){
        super(whichWorld,pos);
        strength = 9;
        initiative = 5;
        name = "Wilk";
        symbol = 'W';
        color = (Color.GRAY);
    }
    public Wolf(World whichWorld, int strength, int initiative,int age, Point pos){
        super(whichWorld,strength,initiative,age,pos);
        name = "Wilk";
        symbol = 'W';
        color = (Color.GRAY);
    }
    @Override
    public String getName(){return name;}

    @Override
    public char getSymbol(){return symbol;}

    @Override
    public Color getColor(){return color;}
}

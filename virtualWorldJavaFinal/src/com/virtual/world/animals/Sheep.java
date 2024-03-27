package com.virtual.world.animals;

import com.virtual.world.Animal;
import com.virtual.world.World;

import java.awt.*;

public class Sheep extends Animal {
    public Sheep(World whichWorld, Point pos){
        super(whichWorld,pos);
        strength = 4;
        initiative = 4;
        name = "Owca";
        symbol = 'O';
        color = (Color.WHITE);
    }

    public Sheep(World whichWorld, int strength, int initiative,int age, Point pos) {
        super(whichWorld,strength,initiative,age,pos);
        name = "Owca";
        symbol = 'O';
        color = (Color.WHITE);
    }

    @Override
    public String getName(){return name;}

    @Override
    public char getSymbol(){return symbol;}

    @Override
    public Color getColor(){return color;}

}

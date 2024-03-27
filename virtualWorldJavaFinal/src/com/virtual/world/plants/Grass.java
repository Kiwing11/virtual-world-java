package com.virtual.world.plants;

import com.virtual.world.C;
import com.virtual.world.Plant;
import com.virtual.world.World;

import java.awt.*;

public class Grass extends Plant {
    public Grass(World whichWorld, Point pos){
        super(whichWorld,pos);
        strength = 0;
        initiative = 0;
        name = "Trawa";
        symbol = 'T';
        color = (C.GRASS_COLOR);
        chanceToSpread = C.DEFAULT_CHANCE_TO_SPREAD;
    }

    public Grass(World whichWorld, int strength, int initiative,int age, Point pos) {
        super(whichWorld,strength,initiative,age,pos);
        name = "Trawa";
        symbol = 'T';
        color = (C.GRASS_COLOR);
        chanceToSpread = C.DEFAULT_CHANCE_TO_SPREAD;
    }

    @Override
    public String getName(){return name;}

    @Override
    public char getSymbol(){return symbol;}

    @Override
    public Color getColor(){return color;}
}

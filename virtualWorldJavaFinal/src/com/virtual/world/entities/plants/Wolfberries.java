package com.virtual.world.entities.plants;

import com.virtual.world.Constants;
import com.virtual.world.Plant;
import com.virtual.world.World;

import java.awt.*;

public class Wolfberries extends Plant {
    public Wolfberries(World whichWorld, Point pos){
        super(whichWorld,pos);
        strength = 99;
        initiative = 0;
        name = "Wilcze Jagody";
        symbol = ':';
        color = (Constants.WOLFBERRIES_COLOR);
        chanceToSpread =  Constants.WOLFBERRIES_CHANCE_TO_SPREAD;
    }

    public Wolfberries(World whichWorld, int strength, int initiative,int age, Point pos) {
        super(whichWorld,strength,initiative,age,pos);
        name = "Wilcze Jagody";
        symbol = ':';
        color = (Constants.WOLFBERRIES_COLOR);
        chanceToSpread =  Constants.WOLFBERRIES_CHANCE_TO_SPREAD;
    }

    @Override
    public String getName(){return name;}

    @Override
    public char getSymbol(){return symbol;}

    @Override
    public Color getColor(){return color;}
}

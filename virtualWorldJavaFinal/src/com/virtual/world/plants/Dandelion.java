package com.virtual.world.plants;

import com.virtual.world.C;
import com.virtual.world.Plant;
import com.virtual.world.World;

import java.awt.*;
import java.util.Random;

public class Dandelion extends Plant {
    public Dandelion(World whichWorld, Point pos){
        super(whichWorld,pos);
        strength = 0;
        initiative = 0;
        name = "Mlecz";
        symbol = 'M';
        color = (Color.YELLOW);
        chanceToSpread =  C.DANDELION_CHANCE_TO_SPREAD;
    }

    public Dandelion(World whichWorld, int strength, int initiative,int age, Point pos) {
        super(whichWorld,strength,initiative,age,pos);
        name = "Mlecz";
        symbol = 'M';
        color = (Color.YELLOW);
        chanceToSpread =  C.DANDELION_CHANCE_TO_SPREAD;
    }

    @Override
    public String getName(){return name;}

    @Override
    public char getSymbol(){return symbol;}

    @Override
    public Color getColor(){return color;}

    @Override
    public void action(){
        Random rand = new Random();
        for(int i=0; i < C.DANDELION_ATTEMPS_TO_REPRODUCE; i++) { // PODEJMUJE 3 PROBY ROZMNOZENIA W 1 TURZE
            int rNumber = rand.nextInt(100);
            if (this.getChanceToSpread() > rNumber)
                this.spread();
        }
    }
}

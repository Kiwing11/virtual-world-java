package com.virtual.world.plants;

import com.virtual.world.*;

import java.awt.*;

public class Guarana extends Plant {
    public Guarana(World whichWorld, Point pos){
        super(whichWorld,pos);
        strength = 0;
        initiative = 0;
        name = "Guarana";
        symbol = 'g';
        color = (Color.RED);
        chanceToSpread =  C.DEFAULT_CHANCE_TO_SPREAD;
        specialAbility = true;
    }

    public Guarana(World whichWorld, int strength, int initiative,int age, Point pos) {
        super(whichWorld,strength,initiative,age,pos);
        name = "Guarana";
        symbol = 'g';
        color = (Color.RED);
        chanceToSpread =  C.DEFAULT_CHANCE_TO_SPREAD;
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
        Description.describeEating(attacking,this);
        whichWorld.delete(this);
        attacking.setStrength(attacking.getStrength() + C.GUARANAS_POWER);
        attacking.move(this.getPos());
        System.out.println(attacking.getStrength());
    }
}

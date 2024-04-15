package com.virtual.world;

import com.virtual.world.entities.plants.*;
import com.virtual.world.utils.Description;

import java.awt.*;
import java.util.Random;

public abstract class Plant extends Organism{
    public Plant(){}
    public Plant(World whichWorld, int strength, int initiative,int age, Point pos){super(whichWorld,strength,initiative,age,pos);}
    public Plant(World whichWorld, Point pos){super(whichWorld,pos);}

    @Override
    public void action(){
        Random rand = new Random();
        int rNumber = rand.nextInt(100);
        if(this.getChanceToSpread() > rNumber)
            spread();
    }

    protected void spread(){
        Point newPos = getUnoccupiedRandomPos(this.getPos());
        Plant plantTmp = null;

        if(newPos.equals(this.getPos()))
            return;
        if(this.getName() == "Trawa"){
            plantTmp = new Grass(whichWorld, newPos);
            whichWorld.organismsToBeAdded.add(plantTmp);
        }
        else if(this.getName() == "Mlecz"){
            plantTmp = new Dandelion(whichWorld, newPos);
            whichWorld.organismsToBeAdded.add(plantTmp);
        }
        else if(this.getName() == "Wilcze Jagody"){
            plantTmp = new Wolfberries(whichWorld, newPos);
            whichWorld.organismsToBeAdded.add(plantTmp);
        }
        else if(this.getName() == "Barszcz Sosnowskiego"){
            plantTmp = new SosnowskisHogweed(whichWorld, newPos);
            whichWorld.organismsToBeAdded.add(plantTmp);
        }
        else if(this.getName() == "Guarana"){
            plantTmp = new Guarana(whichWorld, newPos);
            whichWorld.organismsToBeAdded.add(plantTmp);
        }
        Description.describeSpread(plantTmp);
    }
}

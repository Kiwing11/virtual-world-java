package com.virtual.world.entities.plants;

import com.virtual.world.*;
import com.virtual.world.utils.Description;

import java.awt.*;

public class SosnowskisHogweed extends Plant {
    public SosnowskisHogweed(World whichWorld, Point pos){
        super(whichWorld,pos);
        strength = 10;
        initiative = 0;
        name = "Barszcz Sosnowskiego";
        symbol = ';';
        color = (Constants.SOSNOWSKISHOGWEED_COLOR);
        chanceToSpread =  Constants.DEFAULT_CHANCE_TO_SPREAD;
    }

    public SosnowskisHogweed(World whichWorld, int strength, int initiative,int age, Point pos) {
        super(whichWorld,strength,initiative,age,pos);
        name = "Barszcz Sosnowskiego";
        symbol = ';';
        color = (Constants.SOSNOWSKISHOGWEED_COLOR);
        chanceToSpread =  Constants.DEFAULT_CHANCE_TO_SPREAD;
    }

    @Override
    public String getName(){return name;}

    @Override
    public char getSymbol(){return symbol;}

    @Override
    public Color getColor(){return color;}

    @Override
    public void action(){
        int x = this.getPos().x;
        int y = this.getPos().y;

        for(int i = 0; i < Constants.TOTAL_DIRECTIONS; i++){
            if(i == Constants.UP){
                if((y != 0) && whichWorld.getOrganismFromBoard(x,y-1) != null){
                    if(whichWorld.getOrganismFromBoard(x,y-1) instanceof Animal) {
                        Description.describeKill(this,whichWorld.getOrganismFromBoard(x, y - 1));
                        whichWorld.delete(whichWorld.getOrganismFromBoard(x, y - 1));
                    }
                }
            }
            else if(i == Constants.RIGHT){
                if((x != whichWorld.getX() -1) && whichWorld.getOrganismFromBoard(x+1,y) != null){
                    if(whichWorld.getOrganismFromBoard(x+1,y) instanceof Animal) {
                        Description.describeKill(this,whichWorld.getOrganismFromBoard(x + 1, y));
                        whichWorld.delete(whichWorld.getOrganismFromBoard(x + 1, y));
                    }
                }
            }
            else if(i == Constants.DOWN){
                if((y != whichWorld.getY() - 1) && whichWorld.getOrganismFromBoard(x,y+1) != null){
                    if(whichWorld.getOrganismFromBoard(x,y+1) instanceof Animal) {
                        Description.describeKill(this, whichWorld.getOrganismFromBoard(x, y + 1));
                        whichWorld.delete(whichWorld.getOrganismFromBoard(x, y + 1));
                    }
                }
            }
            else if(i == Constants.LEFT){
                if((x != 0) && whichWorld.getOrganismFromBoard(x-1,y) != null){
                    if(whichWorld.getOrganismFromBoard(x-1,y) instanceof Animal) {
                        Description.describeKill(this,whichWorld.getOrganismFromBoard(x - 1, y));
                        whichWorld.delete(whichWorld.getOrganismFromBoard(x - 1, y));
                    }
                }
            }
        }
    }
}

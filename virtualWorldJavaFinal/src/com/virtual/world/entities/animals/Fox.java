package com.virtual.world.entities.animals;

import com.virtual.world.Animal;
import com.virtual.world.Constants;
import com.virtual.world.World;

import java.awt.*;
import java.util.Random;

import static com.virtual.world.Constants.*;
import static com.virtual.world.Constants.LEFT;

public class Fox extends Animal {
    public Fox(World whichWorld, Point pos){
        super(whichWorld,pos);
        strength = 3;
        initiative = 7;
        name = "Lis";
        symbol = 'L';
        color = (Color.ORANGE);
    }

    public Fox(World whichWorld, int strength, int initiative,int age, Point pos) {
        super(whichWorld,strength,initiative,age,pos);
        name = "Lis";
        symbol = 'L';
        color = (Color.ORANGE);
    }

    @Override
    public String getName(){return name;}

    @Override
    public char getSymbol(){return symbol;}

    @Override
    public Color getColor(){return color;}

    @Override
    public Point getRandomPos(Point currentPos){
        int x = currentPos.x;
        int y = currentPos.y;
        Point newPos = new Point(x,y);
        boolean[] freeDirections = new boolean[Constants.TOTAL_DIRECTIONS];
        for(int i = 0; i < Constants.TOTAL_DIRECTIONS; i++){
            freeDirections[i] = this.avaiableDirections[i];
        }

        //CZY LIS SIE PORUSZY
        Random rand = new Random();
        int r = rand.nextInt(100+1);
        if(r > this.chanceToMove){
            return currentPos;
        }

        while(true){
            if(!freeDirections[UP] && !freeDirections[Constants.RIGHT] && !freeDirections[Constants.DOWN] && !freeDirections[Constants.LEFT])
                break;
            r = rand.nextInt(Constants.TOTAL_DIRECTIONS); // 4 = 3+1

            switch(r){
                case UP:
                    if((y == 0) || !freeDirections[UP])
                        freeDirections[UP] = false;
                    else{
                        if(whichWorld.getBoard()[x][y-this.range] != null && !ifDeflected(whichWorld.getBoard()[x][y-this.range])) {
                            newPos.setLocation(x, y - this.range);
                            return newPos;
                        }
                        else if(whichWorld.getBoard()[x][y-this.range] == null){
                            newPos.setLocation(x,y-this.range);
                            return newPos;
                        }
                        else
                            freeDirections[UP] = false;
                    }
                    break;
                case RIGHT:
                    if((x == whichWorld.getX() - this.range) || !freeDirections[RIGHT])
                        freeDirections[RIGHT] = false;
                    else{
                        if(whichWorld.getBoard()[x+this.range][y] != null && !ifDeflected(whichWorld.getBoard()[x+this.range][y])) {
                            newPos.setLocation(x + this.range, y);
                            return newPos;
                        }
                        else if(whichWorld.getBoard()[x+this.range][y] == null){
                            newPos.setLocation(x+this.range, y);
                            return newPos;
                        }
                        else
                            freeDirections[RIGHT] = false;
                    }
                    break;
                case DOWN:
                    if((y == whichWorld.getY() - this.range) || !freeDirections[DOWN])
                        freeDirections[DOWN] = false;
                    else{
                        if(whichWorld.getBoard()[x][y+this.range] != null && !ifDeflected(whichWorld.getBoard()[x][y+this.range])) {
                            newPos.setLocation(x, y + this.range);
                            return newPos;
                        }
                        else if(whichWorld.getBoard()[x][y+this.range] == null){
                            newPos.setLocation(x, y + this.range);
                            return newPos;
                        }
                        else
                            freeDirections[DOWN] = false;
                    }
                    break;
                case LEFT:
                    if((x == 0) || !freeDirections[LEFT])
                        freeDirections[LEFT] = false;
                    else{
                        if(whichWorld.getBoard()[x - this.range][y] != null && !ifDeflected(whichWorld.getBoard()[x - this.range][y])) {
                            newPos.setLocation(x - this.range, y);
                            return newPos;
                        }
                        else if(whichWorld.getBoard()[x - this.range][y] == null){
                            newPos.setLocation(x - this.range, y);
                            return newPos;
                        }
                        else{
                            freeDirections[LEFT] = false;
                        }
                    }
                    break;
            }
        }
        return newPos;
    }
}

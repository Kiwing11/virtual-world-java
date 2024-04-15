package com.virtual.world.entities.animals;

import com.virtual.world.*;
import com.virtual.world.utils.Description;

import java.awt.*;

public class Human extends Animal {
    public int abilityCooldown = 0;
    public int duration = 0;
    public Human(World whichWorld, Point pos){
        super(whichWorld,pos);
        strength = 5;
        initiative = 4;
        name = "Czlowiek";
        symbol = 'C';
        color = (Color.BLACK);
        resetDirections();
    }

    public Human(World whichWorld, int strength, int initiative,int age, Point pos, int abilityCooldown, int duration) {
        super(whichWorld,strength,initiative,age,pos);
        name = "Czlowiek";
        symbol = 'C';
        color = (Color.BLACK);
        this.abilityCooldown = abilityCooldown;
        this.duration = duration;
        resetDirections();
    }

    @Override
    public String getName(){return name;}

    @Override
    public char getSymbol(){return symbol;}

    @Override
    public Color getColor(){return color;}

    public int getAbilityCooldown(){
        return abilityCooldown;
    }

    public int getDuration(){
        return duration;
    }

    public void setAbilityCooldown(int cdown) {
        abilityCooldown = cdown;
        if(abilityCooldown < 0){
            abilityCooldown = 0;
        }
    }

    public void setDuration(int dur){
        duration = dur;
        if(duration < 0){
            duration = 0;
        }
    }

    public void resetDirections(){
        this.avaiableDirections[Constants.UP] = false;
        this.avaiableDirections[Constants.RIGHT] = false;
        this.avaiableDirections[Constants.DOWN] = false;
        this.avaiableDirections[Constants.LEFT] = false;
    }

    public void setDirection(int _humansMove){
        resetDirections();
        //GORA
        if(_humansMove == Constants.UP){
            this.avaiableDirections[Constants.UP] = true;
        }
        //PRAWO
        else if(_humansMove == Constants.RIGHT){
            this.avaiableDirections[Constants.RIGHT] = true;
        }
        //DOL
        else if(_humansMove == Constants.DOWN){
            this.avaiableDirections[Constants.DOWN] = true;
        }
        //LEWO
        else if(_humansMove == Constants.LEFT){
            this.avaiableDirections[Constants.LEFT] = true;
        }
    }

    public void isAbilityOn(){
        if(duration <= 0){
            specialAbility = false;
            this.setColor(Color.BLACK);
        }
        else if(abilityCooldown == 0){
            specialAbility = false;
            this.setColor(Color.BLACK);
        }
        else {
            specialAbility = true;
            this.setColor(Constants.HUMAN_WITH_SHIELD_COLOR);
        }
    }

    public void AlzursShield(){
        if(whichWorld.getHuman().isAlive){
            this.specialAbility = true;
            this.abilityCooldown += Constants.HUMAN_ABILITY_COOLDOWN;
            this.duration += Constants.HUMAN_ABILITY_DURATION;
            this.setColor(Constants.HUMAN_WITH_SHIELD_COLOR);
        }
    }

    @Override
    public void specificCollision(Organism colliding, Organism attacking){
        if(this == colliding){
            Description.describeScareAway(this,attacking);
            attacking.move(getRandomPos(this.getPos()));
        }
        if(this == attacking){
            if(colliding instanceof Animal) {
                Description.describeScareAway(this,colliding);
                this.move(colliding.getPos());
                colliding.move(getRandomPos(colliding.getPos()));
            }
            else{
                if(!ifDeflected(colliding)){
                    Description.describeEating(this, colliding);
                    whichWorld.delete(colliding);
                    this.move(colliding.getPos());
                }
            }
        }
    }
}

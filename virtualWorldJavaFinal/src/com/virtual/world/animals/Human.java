package com.virtual.world.animals;

import com.virtual.world.*;

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
        this.avaiableDirections[C.UP] = false;
        this.avaiableDirections[C.RIGHT] = false;
        this.avaiableDirections[C.DOWN] = false;
        this.avaiableDirections[C.LEFT] = false;
    }

    public void setDirection(int _humansMove){
        resetDirections();
        //GORA
        if(_humansMove == C.UP){
            this.avaiableDirections[C.UP] = true;
        }
        //PRAWO
        else if(_humansMove == C.RIGHT){
            this.avaiableDirections[C.RIGHT] = true;
        }
        //DOL
        else if(_humansMove == C.DOWN){
            this.avaiableDirections[C.DOWN] = true;
        }
        //LEWO
        else if(_humansMove == C.LEFT){
            this.avaiableDirections[C.LEFT] = true;
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
            this.setColor(C.HUMAN_WITH_SHIELD_COLOR);
        }
    }

    public void AlzursShield(){
        if(whichWorld.getHuman().isAlive){
            this.specialAbility = true;
            this.abilityCooldown += C.HUMAN_ABILITY_COOLDOWN;
            this.duration += C.HUMAN_ABILITY_DURATION;
            this.setColor(C.HUMAN_WITH_SHIELD_COLOR);
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

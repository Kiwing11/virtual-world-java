package com.virtual.world.utils;

import com.virtual.world.World;
import com.virtual.world.entities.animals.*;
import com.virtual.world.entities.plants.*;

import java.awt.*;

public class FromStringToOrganism {
    public void addNewOrganism(String name, World _whichWorld, Point _pos){
        switch (name){
            case "Owca":
                _whichWorld.add(new Sheep(_whichWorld, _pos));
                break;
            case "Wilk":
                _whichWorld.add(new Wolf(_whichWorld, _pos));
                break;
            case "Antylopa":
                _whichWorld.add(new Antelope(_whichWorld, _pos));
                break;
            case "Lis":
                _whichWorld.add(new Fox(_whichWorld, _pos));
                break;
            case "Zolw":
                _whichWorld.add(new Turtle(_whichWorld, _pos));
                break;
            case "Trawa":
                _whichWorld.add(new Grass(_whichWorld, _pos));
                break;
            case "Mlecz":
                _whichWorld.add(new Dandelion(_whichWorld, _pos));
                break;
            case "Guarana":
                _whichWorld.add(new Guarana(_whichWorld, _pos));
                break;
            case "Wilcze Jagody":
                _whichWorld.add(new Wolfberries(_whichWorld, _pos));
                break;
            case "Barszcz Sosnowskiego":
                _whichWorld.add(new SosnowskisHogweed(_whichWorld, _pos));
                break;
        }
    }
}

package com.virtual.world;

public class Description {
    static String stateOfGame = "";

    static void reset(){
        stateOfGame = "";
    }

    public static void describeKill(Organism alive, Organism dead){
        String text = alive.getName()+" ["+ alive.getPos().x+","+ alive.getPos().y+"]"+" zabija "+dead.getName()+" ["+ dead.getPos().x+","+ dead.getPos().y+"]\n";
        stateOfGame += text;
    }

    public static void describeEating(Organism alive, Organism dead){
        String text = alive.getName()+" ["+ alive.getPos().x+","+ alive.getPos().y+"]"+" zjada "+dead.getName()+" ["+ dead.getPos().x+","+ dead.getPos().y+"]\n";
        stateOfGame += text;
    }

    static void describeMove(Organism o){
        String text = o.getName() +" rusza sie na pozycje "+ "["+o.getPos().x+","+o.getPos().y+"]\n";
        stateOfGame += text;
    }

    public static void describeDeflection(Organism deflecting, Organism attacking){
        String text = deflecting.getName()+" ["+ deflecting.getPos().x+","+ deflecting.getPos().y+"]"+" odbija atak "+attacking.getName()+" ["+ attacking.getPos().x+","+ attacking.getPos().y+"]\n";
        stateOfGame += text;
    }

    public static void describeScareAway(Organism o, Organism scaredAway){
        String text = o.getName()+" ["+ o.getPos().x+","+ o.getPos().y+"]"+" odstrasza "+scaredAway.getName()+" ["+ scaredAway.getPos().x+","+ scaredAway.getPos().y+"]\n";
        stateOfGame += text;
    }

    static void describeBirth(Organism o){
        String text = "Narodziny nowego organizmu: " + o.getName()+" ["+ o.getPos().x+","+ o.getPos().y+"]\n";
        stateOfGame += text;
    }

    static void describeSpread(Organism o){
        String text = o.getName() + " rosnie na polu "+" ["+ o.getPos().x+","+ o.getPos().y+"]\n";
        stateOfGame += text;
    }

    static void describeNextTurn(int turn){
        String text = "=======\n" + "TURA " + turn + "\n" + "=======\n";
        stateOfGame += text;
    }

    public static void describeRunAway(Organism running, Organism attacking){
        String text = running.getName() + " ucieka przed "+attacking.getName()+"\n";
        stateOfGame += text;
    }

    public static void describeSave(){
        String text = "======================\n"+"GRA POMYSLNIE ZAPISANA\n"+"======================\n";
        stateOfGame += text;
    }

}

package com.virtual.world;

import java.awt.*;

public class Constants {
    public final static int WINDOW_WIDTH = 1000;
    public final static int WINDOW_HEIGHT = 700;
    public final static int GAME_VIEW_WIDTH = 500;
    public final static int GAME_VIEW_HEIGHT = 500;
    public final static int LOG_PANEL_WIDTH = 350;
    public final static int LOG_PANEL_HEIGHT = 500;
    public final static int LIST_WIDTH = 100;
    public final static int LIST_HEIGHT = 350;
    public final static Color BEIGE = new Color(159,133,30);
    public final static Color ANTELOPE_COLOR = new Color(208,159,25);
    public final static Color GRASS_COLOR = new Color(53,172,10);
    public final static Color WOLFBERRIES_COLOR = new Color(153,0,153);
    public final static Color SOSNOWSKISHOGWEED_COLOR = new Color(153,255,153);
    public final static Color HUMAN_WITH_SHIELD_COLOR = new Color(0,0,153);
    public final static int MARGIN = 100;
    public final static int DEFAULT_RANGE = 1;
    public final static int TOTAL_DIRECTIONS = 4;
    public final static int DANDELION_ATTEMPS_TO_REPRODUCE = 3;
    public final static int GUARANAS_POWER = 3;
    public final static int TOTAL_SPIECES = 10;
    public final static int TOTAL_ANIMAL_SPIECES = 5;
    public final static int HUMAN_ABILITY_COOLDOWN = 10;
    public final static int HUMAN_ABILITY_DURATION = 5;
    public final static int TURTLE_CHANCE_TO_MOVE = 25;
    public final static int DANDELION_CHANCE_TO_SPREAD = 2;
    public final static int WOLFBERRIES_CHANCE_TO_SPREAD = 2;
    public final static int DEFAULT_CHANCE_TO_SPREAD = 3;

    //STATYSTYKI
    public final static int STRENGTH = 1;
    public final static int INITIATIVE = 2;
    public final static int AGE = 3;
    public final static int POS_X = 4;
    public final static int POS_Y = 5;
    public final static int H_CDOWN = 6;
    public final static int H_DUR = 7;

    //GATUNKI ZWIERZAT
    public final static int OWCA = 0;
    public final static int WILK = 1;
    public final static int LIS = 2;
    public final static int ANTYLOPA =3;
    public final static int ZOLW = 4;
    static String spieces[] = {"Owca","Wilk","Lis","Antylopa","Zolw"};

    //KIERUNKI
    public final static int UP = 0;
    public final static int RIGHT = 1;
    public final static int DOWN = 2;
    public final static int LEFT = 3;
    //WYMIARY GUZIKOW
    public final static int BUTTON_WIDTH = 100;
    public final static int BUTTON_HEIGHT = 50;
    public final static int BIG_BUTTON_WIDTH = WINDOW_WIDTH;
    public final static int BIG_BUTTON_HEIGHT = 100;
}

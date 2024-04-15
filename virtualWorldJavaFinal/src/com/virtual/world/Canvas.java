package com.virtual.world;

import javax.swing.*;
import java.awt.*;
public class Canvas extends JPanel {
    World worldToPrint;
        public Canvas(World world){
            worldToPrint = world;
            setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        }
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            draw(g);
        }
        void drawBackground(Graphics g){
            g.setColor(Constants.BEIGE);
            g.fillRect(0,0,getWidth(),getHeight());
        }
        void draw(Graphics g){
            g.clearRect(0,0,getWidth(),getHeight());
            drawBackground(g);
            worldToPrint.printBoard(g);
        }
}

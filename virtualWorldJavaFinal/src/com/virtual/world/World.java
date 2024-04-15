package com.virtual.world;


import com.virtual.world.entities.animals.*;
import com.virtual.world.entities.plants.*;
import com.virtual.world.utils.Description;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

import static com.virtual.world.Constants.TOTAL_SPIECES;

public class World extends JPanel {
    private int X, Y;
    private int turn;
    protected int rectWidth, rectHeight;
    protected boolean isHumanAlive;
    protected int humansMove;
    Human human;

    Vector<Organism> organisms;
    Vector<Organism> organismsToBeAdded;
    Vector<Organism> organismsToBeDeleted;
    Organism[][] board;

        public World(int width, int height) {
            X = width;
            Y = height;
            organisms = new Vector<Organism>();
            organismsToBeAdded = new Vector<Organism>();
            organismsToBeDeleted = new Vector<Organism>();
            board = new Organism[width][height];
        }

        public Organism[][] getBoard(){
            return board;
        }

        public void setIsHumanAlive(boolean statement){
            isHumanAlive = statement;
        }

        public Human getHuman(){
            return human;
        }

        public void setHumansMove(int dir){
            humansMove = dir;
        }

        public int getX() {
            return X;
        }

        public int getY() {
            return Y;
        }

        public void setTurn(int _turn){
            this.turn = _turn;
        }

        public  void add(Organism o){
                organisms.add(o);
                board[o.pos.x][o.pos.y] = o;

        }

        public void delete(Organism o){
            organismsToBeDeleted.add(o);
            o.isAlive = false;
            board[o.pos.x][o.pos.y] = null;
            if(Objects.equals(o.getName(), "Czlowiek")){
                this.isHumanAlive = false;
            }
        }

        public void printBoard(Graphics g){
            super.paint(g);
            for(Organism o: organisms){
                if(o.isAlive) {
                    g.setColor(o.color);
                    g.fillRect(o.pos.x * rectWidth, o.pos.y * rectHeight, rectWidth, rectHeight);
                }
            }
        }
        public Organism getOrganismFromBoard(int x, int y){
            return board[x][y];
        }

        public void sortOrganisms(){
            Collections.sort(organisms, new Comparator<Organism>() {
                @Override
                public int compare(Organism o1, Organism o2) {
                    if(o1.getInitiative() == o2.getInitiative())
                        return Integer.compare(o2.getInitiative(), o1.getInitiative());
                    else
                        return Integer.compare(o2.getInitiative(), o1.getInitiative());
                }
            });
        }

        public Point getRandomPlace(){
            Random rand = new Random();
            Point p = new Point();
            int randX, randY;
            for(int y = 0; y < Y; y++){
                for(int x = 0; x < X; x++){
                    if(board[x][y] == null){
                        while(true){
                            randX = rand.nextInt(X);
                            randY = rand.nextInt(Y);
                            if(board[randX][randY] == null){
                                p.setLocation(randX,randY);
                                return p;
                            }
                        }
                    }
                }
            }
            return p;// JESLI NIE ZNALEZIONO WOLNEGO POLA
        }

        public void nextTurn(){
            sortOrganisms();
            for(Organism o: organisms){
                if(o.isAlive){
                    if(o.getAge() != 0 || turn == 0){
                        o.action();
                    }
                    o.setAge(o.getAge() + 1);
                }
            }
            for(Organism t: organismsToBeAdded){
                this.add(t);
            }
            for(Organism d: organismsToBeDeleted){
                this.organisms.remove(d);
            }
            organismsToBeAdded.clear();
            if(isHumanAlive && human != null){
                human.resetDirections();
                human.setAbilityCooldown(human.abilityCooldown - 1);
                human.setDuration(human.duration - 1);
                human.isAbilityOn();
            }
            Description.describeNextTurn(turn);
            turn++;
        }

        public void generateOrganisms(){
            int numberOfOrganisms = (X*Y)/6;
            Point p = getRandomPlace();
            //STAWIANIE CZLOWIEKA
            this.human = (new Human(this, p));
            this.add(human);
            this.isHumanAlive = true;
            //
            for(int i = 0; i < numberOfOrganisms - 1; i++){
                p = getRandomPlace();
                if(p.x != X && p.y != Y){
                    if(i % TOTAL_SPIECES == 0){
                        this.add(new Wolf(this, p));
                        System.out.println("Wilk na pozycji ["+p.x+","+p.y+"]");
                    }
                    else if(i % TOTAL_SPIECES == 1){
                        this.add(new Sheep(this, p));
                        System.out.println("Owca na pozycji ["+p.x+","+p.y+"]");
                    }
                    else if(i % TOTAL_SPIECES == 2){
                        this.add(new Fox(this, p));
                        System.out.println("Lis na pozycji ["+p.x+","+p.y+"]");
                    }
                    else if(i % TOTAL_SPIECES == 3){
                        this.add(new Turtle(this, p));
                        System.out.println("Zolw na pozycji ["+p.x+","+p.y+"]");
                    }
                    else if (i % TOTAL_SPIECES == 4){
                        this.add(new Antelope(this, p));
                        System.out.println("Antylopa na pozycji ["+p.x+","+p.y+"]");
                    }
                    else if (i % TOTAL_SPIECES == 5){
                        this.add(new Grass(this, p));
                        System.out.println("Trawa na pozycji ["+p.x+","+p.y+"]");
                    }
                    else if(i % TOTAL_SPIECES == 6){
                        this.add(new Dandelion(this, p));
                        System.out.println("Mlecz na pozycji ["+p.x+","+p.y+"]");
                    }
                    else if(i % TOTAL_SPIECES == 7){
                        this.add(new Wolfberries(this, p));
                        System.out.println("Wilcze jagody na pozycji ["+p.x+","+p.y+"]");
                    }
                    else if(i % TOTAL_SPIECES == 8){
                        this.add(new SosnowskisHogweed(this, p));
                        System.out.println("Barszcz sosnowskiego na pozycji ["+p.x+","+p.y+"]");
                    }
                    else if(i % TOTAL_SPIECES == 9){
                        this.add(new Guarana(this, p));
                        System.out.println("Guarana na pozycji ["+p.x+","+p.y+"]");
                    }
                }
            }
        }

        public void save() throws IOException {
            try {
                long timeMillis = System.currentTimeMillis();
                String currentTime = String.valueOf(timeMillis);
                String gameSave = "";
                gameSave += X + " " + Y + " " + isHumanAlive + " " +  turn + "\n";
                for (Organism o : organisms) {
                    gameSave += (o.getSymbol() + " ") + (o.getStrength() + " ") + (o.getInitiative() + " ") + (o.getAge() + " ") + (o.getPos().x + " ") + (o.getPos().y + " ");
                    if (o instanceof Human) {
                        gameSave +=  (((Human) o).getAbilityCooldown() + " " + (((Human) o).getDuration() + " "));
                    }
                    gameSave += "\n";
                }

                String[] textToSave = gameSave.split("\n");
                String filePath = "gameSave"+currentTime+".txt";
                FileWriter fileToSave = new FileWriter(filePath);
                BufferedWriter writer = (new BufferedWriter(fileToSave));
                for (String s : textToSave) {
                    writer.write(s);
                    writer.newLine();
                }
                writer.close();
            }
            catch(IOException ie){
                ie.printStackTrace();
            }
        }
}

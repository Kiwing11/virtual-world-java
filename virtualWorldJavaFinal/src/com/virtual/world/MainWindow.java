package com.virtual.world;

import com.virtual.world.entities.animals.*;
import com.virtual.world.entities.plants.*;
import com.virtual.world.utils.Description;
import com.virtual.world.utils.FromStringToOrganism;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    private World worldToPrint;
    private Canvas canvas;
    private JButton newGame;
    private JButton nextTurn;
    private JButton save;
    private JFrame input;
    private JList c1;
    private JTextArea logs = new JTextArea(Description.stateOfGame);
    private JScrollPane scrollPane;
    private JButton load;
    private JFileChooser fileChooser;
    private ArrayList<JButton> mapButtons = new ArrayList<JButton>();

    public MainWindow(){
        Dimension d = new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setSize(d);
        setTitle("VirtualWorld Krystian Przybysz 188918");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setFocusable(true);

        drawUI();
    }

    void setRectSize(int N, int M){
        int rectWidth = Constants.GAME_VIEW_WIDTH/N;
        int rectHeight = Constants.GAME_VIEW_HEIGHT/M;
        worldToPrint.rectWidth = rectWidth;
        worldToPrint.rectHeight = rectHeight;
    }

    void drawUI(){
        //BUTTONS

        //NOWA GRA
    newGame = new JButton();
    newGame.setBounds(0, Constants.WINDOW_HEIGHT- 2* Constants.MARGIN, Constants.BIG_BUTTON_WIDTH, Constants.BIG_BUTTON_HEIGHT);
    newGame.setText("Nowa Gra");
    newGame.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e);
            //STWORZENIE INPUTU
            input = new JFrame();
            int N = Integer.parseInt(JOptionPane.showInputDialog(input,"Szerokość planszy", 10));
            int M = Integer.parseInt(JOptionPane.showInputDialog(input,"Wysokość planszy", 10));
            worldToPrint = new World(N,M);
            setRectSize(N,M);
            worldToPrint.generateOrganisms();
            //WYSWIETLENIE PLANSZY GRY
            canvas = new Canvas(worldToPrint);
            canvas.setBounds(0,0, Constants.GAME_VIEW_WIDTH, Constants.GAME_VIEW_HEIGHT);
            add(canvas);
            canvas.repaint();
            newGame.setVisible(false);
            load.setVisible(false);
            add(nextTurn);
            getContentPane().add(scrollPane);
            add(save);
            buttonBoard();
            repaint();
        }
    });

    //NASTEPNA TURA
    nextTurn = new JButton();
    nextTurn.setBounds(0 + Constants.BUTTON_WIDTH, Constants.WINDOW_HEIGHT - Constants.MARGIN, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
    nextTurn.setText("Nastepna Tura");
    nextTurn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e);
            worldToPrint.nextTurn();
            canvas.repaint();
            logs.append(Description.stateOfGame);
            logs.repaint();
            Description.reset();
            buttonBoard();
            requestFocus();
        }
    });
    add(newGame);
    //ZAPISZ GRE
    save = new JButton("Zapisz");
    save.setBounds(0 , Constants.WINDOW_HEIGHT - Constants.MARGIN, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
    save.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                worldToPrint.save();
                Description.describeSave();
                logs.append(Description.stateOfGame);
                logs.repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    });

    //WCZYTAJ GRE
    load = new JButton("Wczytaj");
    load.setBounds(0 , Constants.WINDOW_HEIGHT - 2 * Constants.MARGIN - Constants.BIG_BUTTON_HEIGHT, Constants.BIG_BUTTON_WIDTH, Constants.BIG_BUTTON_HEIGHT);
    load.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            fileChooser = new JFileChooser("D:\\Java_programy\\virtualWorldJavaFinal");
            fileChooser.showSaveDialog(null);
            File selectedFile = (fileChooser.getSelectedFile());
            System.out.println(selectedFile);

            try{
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                String line = reader.readLine();
                String[] data = line.split(" ");
                int tmpX = Integer.parseInt(data[0]);
                int tmpY = Integer.parseInt(data[1]);
                World tmpWorld = new World(tmpX,tmpY);
                tmpWorld.isHumanAlive = Boolean.parseBoolean(data[2]);
                tmpWorld.setTurn(Integer.parseInt(data[3]));
                while((line = reader.readLine()) != null){
                    data = line.split(" ");
                    String symbol = data[0];
                    switch (symbol){
                        case "W":
                            tmpWorld.add(new Wolf(tmpWorld, Integer.parseInt(data[Constants.STRENGTH]),Integer.parseInt(data[Constants.INITIATIVE]),Integer.parseInt(data[Constants.AGE]), new Point(Integer.parseInt(data[Constants.POS_X]), Integer.parseInt(data[Constants.POS_Y])) ));
                            break;
                        case "O":
                            tmpWorld.add(new Sheep(tmpWorld, Integer.parseInt(data[Constants.STRENGTH]),Integer.parseInt(data[Constants.INITIATIVE]),Integer.parseInt(data[Constants.AGE]), new Point(Integer.parseInt(data[Constants.POS_X]), Integer.parseInt(data[Constants.POS_Y])) ));
                            break;
                        case "L":
                            tmpWorld.add(new Fox(tmpWorld, Integer.parseInt(data[Constants.STRENGTH]),Integer.parseInt(data[Constants.INITIATIVE]),Integer.parseInt(data[Constants.AGE]), new Point(Integer.parseInt(data[Constants.POS_X]), Integer.parseInt(data[Constants.POS_Y])) ));
                            break;
                        case "A":
                            tmpWorld.add(new Antelope(tmpWorld, Integer.parseInt(data[Constants.STRENGTH]),Integer.parseInt(data[Constants.INITIATIVE]),Integer.parseInt(data[Constants.AGE]), new Point(Integer.parseInt(data[Constants.POS_X]), Integer.parseInt(data[Constants.POS_Y])) ));
                            break;
                        case "Z":
                            tmpWorld.add(new Turtle(tmpWorld,Integer.parseInt(data[Constants.STRENGTH]),Integer.parseInt(data[Constants.INITIATIVE]),Integer.parseInt(data[Constants.AGE]), new Point(Integer.parseInt(data[Constants.POS_X]), Integer.parseInt(data[Constants.POS_Y])) ));
                            break;
                        case "C":
                            Human tmpHuman = new Human(tmpWorld, Integer.parseInt(data[Constants.STRENGTH]),Integer.parseInt(data[Constants.INITIATIVE]),Integer.parseInt(data[Constants.AGE]), new Point(Integer.parseInt(data[Constants.POS_X]), Integer.parseInt(data[Constants.POS_Y])), Integer.parseInt(data[Constants.H_CDOWN]), Integer.parseInt(data[Constants.H_DUR]));
                            tmpWorld.add(tmpHuman);
                            tmpWorld.human = tmpHuman;
                            tmpHuman.isAbilityOn();
                            break;
                        case "M":
                            tmpWorld.add(new Dandelion(tmpWorld, Integer.parseInt(data[Constants.STRENGTH]),Integer.parseInt(data[Constants.INITIATIVE]),Integer.parseInt(data[Constants.AGE]), new Point(Integer.parseInt(data[Constants.POS_X]), Integer.parseInt(data[Constants.POS_Y])) ));
                            break;
                        case "T":
                            tmpWorld.add(new Grass(tmpWorld, Integer.parseInt(data[Constants.STRENGTH]),Integer.parseInt(data[Constants.INITIATIVE]),Integer.parseInt(data[Constants.AGE]), new Point(Integer.parseInt(data[Constants.POS_X]), Integer.parseInt(data[Constants.POS_Y])) ));
                            break;
                        case ":":
                            tmpWorld.add(new Wolfberries(tmpWorld, Integer.parseInt(data[Constants.STRENGTH]),Integer.parseInt(data[Constants.INITIATIVE]),Integer.parseInt(data[Constants.AGE]), new Point(Integer.parseInt(data[Constants.POS_X]), Integer.parseInt(data[Constants.POS_Y])) ));
                            break;
                        case ";":
                            tmpWorld.add(new SosnowskisHogweed(tmpWorld, Integer.parseInt(data[Constants.STRENGTH]),Integer.parseInt(data[Constants.INITIATIVE]),Integer.parseInt(data[Constants.AGE]), new Point(Integer.parseInt(data[Constants.POS_X]), Integer.parseInt(data[Constants.POS_Y])) ));
                            break;
                        case "g":
                            tmpWorld.add(new Guarana(tmpWorld, Integer.parseInt(data[Constants.STRENGTH]),Integer.parseInt(data[Constants.INITIATIVE]),Integer.parseInt(data[Constants.AGE]), new Point(Integer.parseInt(data[Constants.POS_X]), Integer.parseInt(data[Constants.POS_Y])) ));
                            break;

                    }
                }
                reader.close();
                worldToPrint = tmpWorld;
                setRectSize(tmpX,tmpY);
                canvas = new Canvas(worldToPrint);
                canvas.setBounds(0,0, Constants.GAME_VIEW_WIDTH, Constants.GAME_VIEW_HEIGHT);
                add(canvas);
                canvas.repaint();
                newGame.setVisible(false);
                load.setVisible(false);
                add(nextTurn);
                getContentPane().add(scrollPane);
                add(save);
                buttonBoard();
                repaint();
            }
            catch (IOException et) {
                System.out.println("Error: " + e);
            }

        }
    });

    add(load);

    //OBSLUGA KLAWISZY
    addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (worldToPrint != null) {
                int key = e.getKeyCode();
                System.out.println(e);
                if (worldToPrint.isHumanAlive && worldToPrint.human != null) {
                    if (key == KeyEvent.VK_UP) {
                        worldToPrint.setHumansMove(Constants.UP);
                        worldToPrint.getHuman().setDirection(Constants.UP);
                        worldToPrint.nextTurn();
                    } else if (key == KeyEvent.VK_RIGHT) {
                        worldToPrint.setHumansMove((Constants.RIGHT));
                        worldToPrint.getHuman().setDirection(Constants.RIGHT);
                        worldToPrint.nextTurn();
                    } else if (key == KeyEvent.VK_DOWN) {
                        worldToPrint.setHumansMove((Constants.DOWN));
                        worldToPrint.getHuman().setDirection(Constants.DOWN);
                        worldToPrint.nextTurn();
                    } else if (key == KeyEvent.VK_LEFT) {
                        worldToPrint.setHumansMove((Constants.LEFT));
                        worldToPrint.getHuman().setDirection(Constants.LEFT);
                        worldToPrint.nextTurn();
                    }
                    if (key == KeyEvent.VK_R) {
                        if (worldToPrint.getHuman().abilityCooldown == 0) {
                            worldToPrint.getHuman().AlzursShield();
                        }
                    }
                }
                canvas.repaint();
                logs.append(Description.stateOfGame);
                logs.repaint();
                Description.reset();
            }
        }
        public void keyTyped (KeyEvent e){}
        public void keyReleased (KeyEvent e){}
    });
        logs.setEditable(false);
        logs.setLineWrap(true);
        logs.setWrapStyleWord(true);
        scrollPane = new JScrollPane(logs);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(Constants.WINDOW_WIDTH- Constants.LOG_PANEL_WIDTH,0, Constants.LOG_PANEL_WIDTH, Constants.LOG_PANEL_HEIGHT);
    }
    //PLANSZA PRZYCISKOW DO DODAWANIA ZWIERZAT
    public void buttonBoard() {
        //USUWANIE POPRZEDNICH PRZYCISKOW
        for(JButton button: mapButtons){
            button.setVisible(false);
        }

        for (int i = 0; i < worldToPrint.getY(); i++) {
            for (int j = 0; j < worldToPrint.getX(); j++) {
                if (worldToPrint.getBoard()[j][i] == null) {
                    JButton addOrganismButton = new JButton();
                    addOrganismButton.setBounds(j * worldToPrint.rectWidth, i * worldToPrint.rectHeight, worldToPrint.rectWidth, worldToPrint.rectHeight);
                    addOrganismButton.setOpaque(false);
                    int finalI = i;
                    int finalJ = j;
                    addOrganismButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(e);
                            JFrame list = new JFrame();
                            list.setSize(Constants.LIST_WIDTH, Constants.LIST_HEIGHT);
                            String spieces[] = {"Owca","Wilk","Lis","Antylopa","Zolw","Trawa","Mlecz","Guarana","Wilcze Jagody","Barszcz Sosnowskiego"};
                            c1 = new JList(spieces);
                            c1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                            list.add(c1);
                            list.setVisible(true);
                            c1.addListSelectionListener(new ListSelectionListener() {
                                @Override
                                public void valueChanged(ListSelectionEvent e) {
                                    if(!e.getValueIsAdjusting()) {
                                        System.out.println(c1.getSelectedValue());
                                        String tempName = c1.getSelectedValue().toString();
                                        FromStringToOrganism f = new FromStringToOrganism();
                                        f.addNewOrganism(tempName,worldToPrint, new Point(finalJ, finalI));
                                        SwingUtilities.getWindowAncestor(c1).setVisible(false);
                                        System.out.println(worldToPrint.getOrganismFromBoard(finalJ,finalI));
                                        requestFocus();
                                    }
                                }
                            });
                        }
                    });
                    add(addOrganismButton);
                    mapButtons.add(addOrganismButton);
                }
            }
        }
    }

}

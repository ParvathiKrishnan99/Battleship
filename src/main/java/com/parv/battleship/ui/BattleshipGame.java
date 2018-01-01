package com.parv.battleship.ui;
/*
 * [BattleshipGame.java]
 * A GUI version of the classic battleship game
 * Author:Parvathi Krishnan
 * Date: May 21st, 2016
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.PrintWriter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.parv.battleship.StrategyProcessor; 

public class BattleshipGame extends JFrame implements ActionListener{
	StrategyProcessor processor = new StrategyProcessor();
  
//setting layout
  Object[] possibilities = {"Destroyer","Submarine","Cruiser","Battleship","Carrier"};
  JPanel startPanel = new JPanel();
  JPanel gamePanel = new JPanel();
  JPanel endPanel = new JPanel();
  JPanel mainPanel = new JPanel();
  JPanel topPanel = new JPanel();
  JLabel topLabel = new JLabel("Welcome to the Battleship Game!", JLabel.CENTER); // welcome
  JPanel middle = new JPanel();
  JPanel horGrid = new JPanel();
  JPanel verGrid = new JPanel();
  JLabel hor1 = new JLabel("        1   ");
  JLabel hor2 = new JLabel("   2   ");
  JLabel hor3 = new JLabel("   3   ");
  JLabel hor4 = new JLabel("   4   ");
  JLabel hor5 = new JLabel("   5   ");
  JLabel hor6 = new JLabel("   6   ");
  JLabel hor7 = new JLabel("   7   ");
  JLabel hor8 = new JLabel("   8   ");
  JLabel hor9 = new JLabel("   9   ");
  JLabel hor10 = new JLabel("  10 ");
  JLabel ver1 = new JLabel("1");
  JLabel ver2 = new JLabel("2");
  JLabel ver3 = new JLabel("3");
  JLabel ver4 = new JLabel("4");
  JLabel ver5 = new JLabel("5");
  JLabel ver6 = new JLabel("6");
  JLabel ver7 = new JLabel("7");
  JLabel ver8 = new JLabel("8");
  JLabel ver9 = new JLabel("9");
  JLabel ver10 = new JLabel("10");
  JLabel verlabel = new JLabel();
  JPanel bottomPanel = new JPanel();
  JButton start = new JButton("  START  ");
  JLabel instructions = new JLabel(getInstructions());
  JLabel userscores = new JLabel();
  JLabel compscores = new JLabel();
  
  //declaring buttons on panel
  JButton helpButton = new JButton("Help");
  JLabel hitLabel = new JLabel("Hits:"); 
  JLabel missLabel = new JLabel("Misses:"); 
  JLabel shipsLabel= new JLabel("Ships Sunk:"); 
  JTextField hitField = new JTextField("", 3); // tells the number of hits
  JTextField missField = new JTextField("", 3);
  JTextField shipsField = new JTextField("", 3);
  int hit = 0; // opponents hits
  int miss = 0; //opponents misses
  int sunk = 0;
  int compmiss=0;
  int compsink = 0;
  final int ROW = 10;
  final int COL = 10;
  int win = 0; // for the hits
  int tie = 0; // for the tie
  //declaring 2-D arrays; one for the computing array which is the main processor, the other is for the 2-D array of buttons (user layout)
  JButton grid[][] = new JButton[ROW][COL];
  String[][] compGrid = new String[ROW][COL];
  int carrierC = 0;
  int battleshipC = 0;
  int cruiserC = 0;
  int submarineC = 0;
  int destroyerC = 0;
  
  //main constructor
  public BattleshipGame(){ 
  }
  public void placeShips(){ 
	    
	//placing ships onto grid algorithm
	    
	    boolean place1 = false;
	    boolean place2 = false;
	    boolean place3 = false;
	    boolean place4 = false;
	    boolean place5 = false;
	    
	    //placing the 1st ship onto the grid, 5 squares long
	    do {
	      //random selection of coordinates
	      int r1_1 = (int)(Math.random() * 10) + 0;
	      int r1_2 = (int)(Math.random() * 10) + 0;
	      
	      //direction variable; ensures randomness of ship placement
	      int dir1 = (int)(Math.random() * 4) + 0;
	      
	      int direction1_1[][] = {{r1_1 + 1, r1_2}, {r1_1 - 1, r1_2}, {r1_1, r1_2 + 1}, {r1_1, r1_2 - 1}};
	      int direction1_2[][] = {{r1_1 + 2, r1_2}, {r1_1 - 2, r1_2}, {r1_1, r1_2 + 2}, {r1_1, r1_2 - 2}};
	      int direction1_3[][] = {{r1_1 + 3, r1_2}, {r1_1 - 3, r1_2}, {r1_1, r1_2 + 3}, {r1_1, r1_2 - 3}};
	      int direction1_4[][] = {{r1_1 + 4, r1_2}, {r1_1 - 4, r1_2}, {r1_1, r1_2 + 4}, {r1_1, r1_2 - 4}};
	      
	      if (dir1 == 0){
	        if (r1_1 + 4 <= 9){
	          compGrid[r1_1][r1_2] = "1";
	          compGrid[direction1_1[dir1][0]][direction1_1[dir1][1]] = "1";
	          compGrid[direction1_2[dir1][0]][direction1_2[dir1][1]] = "1";
	          compGrid[direction1_3[dir1][0]][direction1_3[dir1][1]] = "1";
	          compGrid[direction1_4[dir1][0]][direction1_4[dir1][1]] = "1";
	          place1 = true;
	        }
	      }
	      if (dir1 == 1){
	        if (r1_1 - 4 >= 0){
	          compGrid[r1_1][r1_2] = "1";
	          compGrid[direction1_1[dir1][0]][direction1_1[dir1][1]] = "1";
	          compGrid[direction1_2[dir1][0]][direction1_2[dir1][1]] = "1";
	          compGrid[direction1_3[dir1][0]][direction1_3[dir1][1]] = "1";
	          compGrid[direction1_4[dir1][0]][direction1_4[dir1][1]] = "1";
	          place1 = true;
	        }
	      }
	      if (dir1 == 2){
	        if (r1_2 + 4 <= 9){
	          compGrid[r1_1][r1_2] = "1";
	          compGrid[direction1_1[dir1][0]][direction1_1[dir1][1]] = "1";
	          compGrid[direction1_2[dir1][0]][direction1_2[dir1][1]] = "1";
	          compGrid[direction1_3[dir1][0]][direction1_3[dir1][1]] = "1";
	          compGrid[direction1_4[dir1][0]][direction1_4[dir1][1]] = "1";
	          place1 = true;
	        } 
	      }
	      if (dir1 == 3){
	        if (r1_2 - 4 >= 0){
	          compGrid[r1_1][r1_2] = "1";
	          compGrid[direction1_1[dir1][0]][direction1_1[dir1][1]] = "1";
	          compGrid[direction1_2[dir1][0]][direction1_2[dir1][1]] = "1";
	          compGrid[direction1_3[dir1][0]][direction1_3[dir1][1]] = "1";
	          compGrid[direction1_4[dir1][0]][direction1_4[dir1][1]] = "1";
	          place1 = true;
	        } 
	      }
	    } while (place1 == false);
	    
	    //second ship, 4 squares long
	    do {
	      //random selection of coordinates
	      int r2_1 = (int)(Math.random() * 10) + 0;
	      int r2_2 = (int)(Math.random() * 10) + 0;
	      
	      //direction variable; ensures randomness of ship placement
	      int dir2 = (int)(Math.random() * 4) + 0;
	      
	      int direction2_1[][] = {{r2_1 + 1, r2_2}, {r2_1 - 1, r2_2}, {r2_1, r2_2 + 1}, {r2_1, r2_2 - 1}};
	      int direction2_2[][] = {{r2_1 + 2, r2_2}, {r2_1 - 2, r2_2}, {r2_1, r2_2 + 2}, {r2_1, r2_2 - 2}};
	      int direction2_3[][] = {{r2_1 + 3, r2_2}, {r2_1 - 3, r2_2}, {r2_1, r2_2 + 3}, {r2_1, r2_2 - 3}};
	      
	      if (dir2 == 0){
	        if ((r2_1 + 3 <= 9) && (compGrid[r2_1][r2_2].equals("0")) && (compGrid[direction2_1[dir2][0]][direction2_1[dir2][1]].equals("0")) && (compGrid[direction2_2[dir2][0]][direction2_2[dir2][1]].equals("0")) && (compGrid[direction2_3[dir2][0]][direction2_3[dir2][1]].equals("0"))){
	          compGrid[r2_1][r2_2] = "2";
	          compGrid[direction2_1[dir2][0]][direction2_1[dir2][1]] = "2";
	          compGrid[direction2_2[dir2][0]][direction2_2[dir2][1]] = "2";
	          compGrid[direction2_3[dir2][0]][direction2_3[dir2][1]] = "2";
	          place2 = true;
	        }
	      }
	      if (dir2 == 1){
	        if ((r2_1 - 3 >= 0) && (compGrid[r2_1][r2_2].equals("0")) && (compGrid[direction2_1[dir2][0]][direction2_1[dir2][1]].equals("0")) && (compGrid[direction2_2[dir2][0]][direction2_2[dir2][1]].equals("0")) && (compGrid[direction2_3[dir2][0]][direction2_3[dir2][1]].equals("0"))){
	          compGrid[r2_1][r2_2] = "2";
	          compGrid[direction2_1[dir2][0]][direction2_1[dir2][1]] = "2";
	          compGrid[direction2_2[dir2][0]][direction2_2[dir2][1]] = "2";
	          compGrid[direction2_3[dir2][0]][direction2_3[dir2][1]] = "2";
	          place2 = true;
	        }
	      }
	      if (dir2 == 2){
	        if ((r2_2 + 3 <= 9) && (compGrid[r2_1][r2_2].equals("0")) && (compGrid[direction2_1[dir2][0]][direction2_1[dir2][1]].equals("0")) && (compGrid[direction2_2[dir2][0]][direction2_2[dir2][1]].equals("0")) && (compGrid[direction2_3[dir2][0]][direction2_3[dir2][1]].equals("0"))){
	          compGrid[r2_1][r2_2] = "2";
	          compGrid[direction2_1[dir2][0]][direction2_1[dir2][1]] = "2";
	          compGrid[direction2_2[dir2][0]][direction2_2[dir2][1]] = "2";
	          compGrid[direction2_3[dir2][0]][direction2_3[dir2][1]] = "2";
	          place2 = true;
	        } 
	      }
	      if (dir2 == 3){
	        if ((r2_2 - 3 >= 0) && (compGrid[r2_1][r2_2].equals("0")) && (compGrid[direction2_1[dir2][0]][direction2_1[dir2][1]].equals("0")) && (compGrid[direction2_2[dir2][0]][direction2_2[dir2][1]].equals("0")) && (compGrid[direction2_3[dir2][0]][direction2_3[dir2][1]].equals("0"))){
	          compGrid[r2_1][r2_2] = "2";
	          compGrid[direction2_1[dir2][0]][direction2_1[dir2][1]] = "2";
	          compGrid[direction2_2[dir2][0]][direction2_2[dir2][1]] = "2";
	          compGrid[direction2_3[dir2][0]][direction2_3[dir2][1]] = "2";
	          place2 = true;
	        } 
	      }
	    } while (place2 == false);
	    
	    //third ship, 3 squares long
	    do {
	      //random selection of coordinates
	      int r3_1 = (int)(Math.random() * 10) + 0;
	      int r3_2 = (int)(Math.random() * 10) + 0;
	      
	      //direction variable; ensures randomness of ship placement
	      int dir3 = (int)(Math.random() * 4) + 0;
	      
	      int direction3_1[][] = {{r3_1 + 1, r3_2}, {r3_1 - 1, r3_2}, {r3_1, r3_2 + 1}, {r3_1, r3_2 - 1}};
	      int direction3_2[][] = {{r3_1 + 2, r3_2}, {r3_1 - 2, r3_2}, {r3_1, r3_2 + 2}, {r3_1, r3_2 - 2}};
	      
	      if (dir3 == 0){
	        if ((r3_1 + 2 <= 9) && (compGrid[r3_1][r3_2].equals("0")) && (compGrid[direction3_1[dir3][0]][direction3_1[dir3][1]].equals("0")) && (compGrid[direction3_2[dir3][0]][direction3_2[dir3][1]].equals("0"))){
	          compGrid[r3_1][r3_2] = "3";
	          compGrid[direction3_1[dir3][0]][direction3_1[dir3][1]] = "3";
	          compGrid[direction3_2[dir3][0]][direction3_2[dir3][1]] = "3";
	          place3 = true;
	        }
	      }
	      if (dir3 == 1){
	        if ((r3_1 - 2 >= 0) && (compGrid[r3_1][r3_2].equals("0")) && (compGrid[direction3_1[dir3][0]][direction3_1[dir3][1]].equals("0")) && (compGrid[direction3_2[dir3][0]][direction3_2[dir3][1]].equals("0"))){
	          compGrid[r3_1][r3_2] = "3";
	          compGrid[direction3_1[dir3][0]][direction3_1[dir3][1]] = "3";
	          compGrid[direction3_2[dir3][0]][direction3_2[dir3][1]] = "3";
	          place3 = true;
	        }
	      }
	      if (dir3 == 2){
	        if ((r3_2 + 2 <= 9) && (compGrid[r3_1][r3_2].equals("0")) && (compGrid[direction3_1[dir3][0]][direction3_1[dir3][1]].equals("0")) && (compGrid[direction3_2[dir3][0]][direction3_2[dir3][1]].equals("0"))){
	          compGrid[r3_1][r3_2] = "3";
	          compGrid[direction3_1[dir3][0]][direction3_1[dir3][1]] = "3";
	          compGrid[direction3_2[dir3][0]][direction3_2[dir3][1]] = "3";
	          place3 = true;
	        } 
	      }
	      if (dir3 == 3){
	        if ((r3_2 - 2 >= 0) && (compGrid[r3_1][r3_2].equals("0")) && (compGrid[direction3_1[dir3][0]][direction3_1[dir3][1]].equals("0")) && (compGrid[direction3_2[dir3][0]][direction3_2[dir3][1]].equals("0"))){
	          compGrid[r3_1][r3_2] = "3";
	          compGrid[direction3_1[dir3][0]][direction3_1[dir3][1]] = "3";
	          compGrid[direction3_2[dir3][0]][direction3_2[dir3][1]] = "3";
	          place3 = true;
	        } 
	      }
	    } while (place3 == false);
	    
	    //fourth ship, 3 squares long
	    do {
	      //random selection of coordinates
	      int r4_1 = (int)(Math.random() * 10) + 0;
	      int r4_2 = (int)(Math.random() * 10) + 0;
	      
	      //direction variable; ensures randomness of ship placement
	      int dir4 = (int)(Math.random() * 4) + 0;
	      
	      int direction4_1[][] = {{r4_1 + 1, r4_2}, {r4_1 - 1, r4_2}, {r4_1, r4_2 + 1}, {r4_1, r4_2 - 1}};
	      int direction4_2[][] = {{r4_1 + 2, r4_2}, {r4_1 - 2, r4_2}, {r4_1, r4_2 + 2}, {r4_1, r4_2 - 2}};
	      
	      if (dir4 == 0){
	        if ((r4_1 + 2 <= 9) && (compGrid[r4_1][r4_2].equals("0")) && (compGrid[direction4_1[dir4][0]][direction4_1[dir4][1]].equals("0")) && (compGrid[direction4_2[dir4][0]][direction4_2[dir4][1]].equals("0"))){
	          compGrid[r4_1][r4_2] = "4";
	          compGrid[direction4_1[dir4][0]][direction4_1[dir4][1]] = "4";
	          compGrid[direction4_2[dir4][0]][direction4_2[dir4][1]] = "4";
	          place4 = true;
	        }
	      }
	      if (dir4 == 1){
	        if ((r4_1 - 2 >= 0) && (compGrid[r4_1][r4_2].equals("0")) && (compGrid[direction4_1[dir4][0]][direction4_1[dir4][1]].equals("0")) && (compGrid[direction4_2[dir4][0]][direction4_2[dir4][1]].equals("0"))){
	          compGrid[r4_1][r4_2] = "4";
	          compGrid[direction4_1[dir4][0]][direction4_1[dir4][1]] = "4";
	          compGrid[direction4_2[dir4][0]][direction4_2[dir4][1]] = "4";
	          place4 = true;
	        }
	      }
	      if (dir4 == 2){
	        if ((r4_2 + 2 <= 9) && (compGrid[r4_1][r4_2].equals("0")) && (compGrid[direction4_1[dir4][0]][direction4_1[dir4][1]].equals("0")) && (compGrid[direction4_2[dir4][0]][direction4_2[dir4][1]].equals("0"))){
	          compGrid[r4_1][r4_2] = "4";
	          compGrid[direction4_1[dir4][0]][direction4_1[dir4][1]] = "4";
	          compGrid[direction4_2[dir4][0]][direction4_2[dir4][1]] = "4";
	          place4 = true;
	        } 
	      }
	      if (dir4 == 3){
	        if ((r4_2 - 2 >= 0) && (compGrid[r4_1][r4_2].equals("0")) && (compGrid[direction4_1[dir4][0]][direction4_1[dir4][1]].equals("0")) && (compGrid[direction4_2[dir4][0]][direction4_2[dir4][1]].equals("0"))){
	          compGrid[r4_1][r4_2] = "4";
	          compGrid[direction4_1[dir4][0]][direction4_1[dir4][1]] = "4";
	          compGrid[direction4_2[dir4][0]][direction4_2[dir4][1]] = "4";
	          place4 = true;
	        } 
	      }
	    } while (place4 == false);
	    
	    //fifth ship, 2 squares long
	    do {
	      //random selection of coordinates
	      int r5_1 = (int)(Math.random() * 10) + 0;
	      int r5_2 = (int)(Math.random() * 10) + 0;
	      
	      //direction variable; ensures randomness of ship placement
	      int dir5 = (int)(Math.random() * 4) + 0;
	      
	      int direction5_1[][] = {{r5_1 + 1, r5_2}, {r5_1 - 1, r5_2}, {r5_1, r5_2 + 1}, {r5_1, r5_2 - 1}};
	      
	      if (dir5 == 0){
	        if ((r5_1 + 1 <= 9) && (compGrid[r5_1][r5_2].equals("0")) && (compGrid[direction5_1[dir5][0]][direction5_1[dir5][1]].equals("0"))){
	          compGrid[r5_1][r5_2] = "5";
	          compGrid[direction5_1[dir5][0]][direction5_1[dir5][1]] = "5";
	          place5 = true;
	        }
	      }
	      if (dir5 == 1){
	        if ((r5_1 - 1 >= 0) && (compGrid[r5_1][r5_2].equals("0")) && (compGrid[direction5_1[dir5][0]][direction5_1[dir5][1]].equals("0"))){
	          compGrid[r5_1][r5_2] = "5";
	          compGrid[direction5_1[dir5][0]][direction5_1[dir5][1]] = "5";
	          place5 = true;
	        }
	      }
	      if (dir5 == 2){
	        if ((r5_2 + 1 <= 9) && (compGrid[r5_1][r5_2].equals("0")) && (compGrid[direction5_1[dir5][0]][direction5_1[dir5][1]].equals("0"))){
	          compGrid[r5_1][r5_2] = "5";
	          compGrid[direction5_1[dir5][0]][direction5_1[dir5][1]] = "5";
	          place5 = true;
	        } 
	      }
	      if (dir5 == 3){
	        if ((r5_2 - 1 >= 0) && (compGrid[r5_1][r5_2].equals("0")) && (compGrid[direction5_1[dir5][0]][direction5_1[dir5][1]].equals("0"))){
	          compGrid[r5_1][r5_2] = "5";
	          compGrid[direction5_1[dir5][0]][direction5_1[dir5][1]] = "5";
	          place5 = true;
	        } 
	      }
	    } while (place5 == false);
	    
	    for (int row = 0; row < ROW; row++){
	      for (int col = 0; col < COL; col++){
	        System.out.print(compGrid[row][col]);
	      }
	      System.out.println();
	      
	    }
	    try{
	      File file = new File("ship.txt");
	      PrintWriter output = new PrintWriter(file);
	      
	      
	      for (int row = 0; row < ROW; row++){
	        
	        for (int col = 0; col < COL; col++){
	          if(compGrid[row][col].equals("0")){
	            output.print(compGrid[row][col]+"  ");
	          }else{
	            output.print("X  ");
	          }
	        }
	        output.println('\n');
	      }  output.close();
	    }
	    catch(Exception e){
	    }
  }
  //start game
  public void start(){
    bottomPanel.setPreferredSize(new Dimension(800, 30));
    
    setTitle("Battleship Game");
    setSize(500, 600);
    topPanel.setPreferredSize(new Dimension(500, 35));
    //setting initial values of compGrid to 0
    for (int row = 0; row < ROW; row++){
      for (int col = 0; col < COL; col++){
        compGrid[row][col] = "0";
      }
    }
    placeShips();
    
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    
    
    
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    
//creating the GUI
    

    
  
    
    GridLayout layout1 = new GridLayout(ROW, COL);
    FlowLayout layout2 = new FlowLayout();
    
    setLayout(layout2);
    
    mainPanel.setLayout(layout1);
    
    for (int row = 0; row < ROW; row++){
      for (int col = 0; col < COL; col++){
      
        grid[row][col] = new JButton();
        grid[row][col].setPreferredSize(new Dimension(30,30));
        grid[row][col].addActionListener(this); // adds the action listener for every button
        mainPanel.add(grid[row][col]); 
      }
    }
    start.addActionListener(this);
    //connects buttons with action listner inorder to give them a function
    
    
    
    userscores.setPreferredSize(new Dimension(190, 500));
    userscores.setOpaque(true);
    userscores.setBackground(Color.WHITE);
    compscores.setPreferredSize(new Dimension(190, 500));
    compscores.setOpaque(true);
    compscores.setBackground(Color.WHITE);
    helpButton.setOpaque(true);
    startPanel.setLayout(layout2);
    startPanel.setPreferredSize(new Dimension(490,599));
    startPanel.setBackground(Color.lightGray);
    startPanel.add(instructions);
    startPanel.add(start);
    
    horGrid.setLayout(layout2);
    horGrid.add(hor1);
    horGrid.add(hor2);
    horGrid.add(hor3);
    horGrid.add(hor4);
    horGrid.add(hor5);
    horGrid.add(hor6);
    horGrid.add(hor7);
    horGrid.add(hor8);
    horGrid.add(hor9);
    horGrid.add(hor10);
    
    verGrid.setLayout(new BoxLayout(verGrid, BoxLayout.Y_AXIS));
    verGrid.add(ver1);
    verGrid.add(Box.createVerticalStrut(15));
    verGrid.add(ver2);
    verGrid.add(Box.createVerticalStrut(15));
    verGrid.add(ver3);
    verGrid.add(Box.createVerticalStrut(15));
    verGrid.add(ver4);
    verGrid.add(Box.createVerticalStrut(15));
    verGrid.add(ver5);
    verGrid.add(Box.createVerticalStrut(15));
    verGrid.add(ver6);
    verGrid.add(Box.createVerticalStrut(15));
    verGrid.add(ver7);
    verGrid.add(Box.createVerticalStrut(15));
    verGrid.add(ver8);
    verGrid.add(Box.createVerticalStrut(15));
    verGrid.add(ver9);
    verGrid.add(Box.createVerticalStrut(20));
    verGrid.add(ver10);
    
    middle.setLayout(layout2);
    middle.add(verGrid);
    middle.add(mainPanel);
    bottomPanel.add(helpButton);
    hitField.setEditable(false);
    missField.setEditable(false);
    shipsField.setEditable(false);
    bottomPanel.add(hitLabel);
    bottomPanel.add(hitField); 
    bottomPanel.add(missLabel);
    bottomPanel.add(missField);
    bottomPanel.add(shipsLabel); 
    bottomPanel.add(shipsField); 
    
    helpButton.setToolTipText("Click on grid to shoot opponent");
    missLabel.setToolTipText("Total number of shots missed.");
    hitLabel.setToolTipText("Total number of successful shots.");
    shipsLabel.setToolTipText("Total number of ships sunk.");
    helpButton.addActionListener(this);
    setVisible(true);
    
    endPanel.setLayout(layout2);
    endPanel.add(userscores);
    endPanel.add(compscores);
    
    gamePanel.setPreferredSize(new Dimension(400,500));
    topPanel.add(topLabel);
    gamePanel.setLayout(layout2);
    gamePanel.add(topPanel);
    gamePanel.add(horGrid);
    gamePanel.add(middle);
    gamePanel.add (bottomPanel);
    add(gamePanel);
    gamePanel.setVisible(false);
    startPanel.setVisible(true);
    endPanel.setVisible(false);
    add(startPanel);
    add(endPanel);
    
    setResizable(false);
    setVisible(true);
  }

//determining hit or mis actionlistener ====================================================================================
  public void actionPerformed (ActionEvent event) {
    String command = event.getActionCommand();//gets the name of the button
    
    for (int row = 0; row < ROW; row++) {
      for (int col = 0; col < COL; col++) {
        if (grid[row][col] == event.getSource() && (win < 17) && (tie < 50)) {
          if (compGrid[row][col].equals ("1")) { //if they hit a ship
            JOptionPane.showMessageDialog(null, "You chose row #" + (row + 1) + ", and column #" + (col + 1) + ". You hit the CARRIER.");//tells them where they pressed
            grid[row][col].setBackground(Color.red);
            grid[row][col].setEnabled(false);//button can no longer be pressed
            
            grid[row][col].setForeground(Color.WHITE);
            win = win + 1;
            tie = tie + 1;
            hitField.setText(win + " ");
            carrierC++;
          }
          else if (compGrid[row][col].equals ("2")) { //if they hit a ship
            JOptionPane.showMessageDialog(null, "You chose row #" + (row + 1) + ", and column #" + (col + 1) + ". You hit the BATTLESHIP.");//tells them where they pressed
            grid[row][col].setBackground(Color.red);
            grid[row][col].setEnabled(false);//button can no longer be pressed
            
            grid[row][col].setForeground(Color.WHITE);
            win = win + 1;
            tie = tie + 1;
            hitField.setText(win + " ");
            battleshipC++;
          }
          
          else if (compGrid[row][col].equals ("3")) { //if they hit a ship
            JOptionPane.showMessageDialog(null, "You chose row #" + (row + 1) + ", and column #" + (col + 1) + ". You hit the CRUISER.");//tells them where they pressed
            grid[row][col].setBackground(Color.red);
            grid[row][col].setEnabled(false);//button can no longer be pressed
            
            grid[row][col].setForeground(Color.WHITE);
            win = win + 1;
            tie = tie + 1;
            hitField.setText(win + " ");
            cruiserC++;
          }
          else if (compGrid[row][col].equals ("4")) { //if they hit a ship
            JOptionPane.showMessageDialog(null, "You chose row #" + (row + 1) + ", and column #" + (col + 1) + ". You hit the SUBMARINE.");//tells them where they pressed
            grid[row][col].setBackground(Color.red);
            grid[row][col].setEnabled(false);//button can no longer be pressed
            
            grid[row][col].setForeground(Color.WHITE);
            win = win + 1;
            tie = tie + 1;
            hitField.setText(win + " ");
            submarineC++;
          }
          else if (compGrid[row][col].equals ("5")) { //if they hit a ship
            JOptionPane.showMessageDialog(null, "You chose row #" + (row + 1) + ", and column #" + (col + 1) + ". You hit the DESTROYER.");//tells them where they pressed
            grid[row][col].setBackground(Color.red);
            grid[row][col].setEnabled(false);//button can no longer be pressed
            
            grid[row][col].setForeground(Color.WHITE);
            win = win + 1;
            tie = tie + 1;
            hitField.setText(win + " ");
            destroyerC++;
          }
          
          else {
            JOptionPane.showMessageDialog(null, "You chose row #" + (row + 1) + ", and column #" + (col + 1) + ". It was a MISS."); // tells them that they missed
            grid[row][col].setBackground(Color.blue); //blue for water
            grid[row][col].setEnabled(false); //button cannot be pressed anymore
            
            grid[row][col].setForeground(Color.WHITE);
            tie = tie + 1; //adds to the 50 shots
            miss++;
            missField.setText(miss + " ");
          }
          String[] hitormiss = {"Hit","Miss","Sunk"};
          
          int[] coordinate = processor.getNextRowAndColumn();
          int n = JOptionPane.showOptionDialog(null, "The opponent has shot:\n Row #: " + coordinate[0] + "\n Column #: " + coordinate[1] + "\n Did they hit, miss, or sink a ship", "Opponent's Turn:", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, hitormiss, hitormiss[0]);
          //Object[] possibilities = {"Destroyer","Submarine","Cruiser","Battleship","Carrier"};
          if (n == 0) {
            String s = (String)JOptionPane.showInputDialog(
                                                           null,
                                                           "Please select which ship was hit:\n",
                                                           "Select Ship",
                                                           JOptionPane.PLAIN_MESSAGE,
                                                           null,
                                                           possibilities,
                                                           "Select ship" );
            
            processor.updateList(0);
            hit++;
          } else if (n == 1) {
            processor.updateList(1);
            compmiss++;
          } else {
            processor.updateList(2);
            compsink++;
            String s = (String)JOptionPane.showInputDialog(
                                                           null,
                                                           "Please select which ship was sunk:\n",
                                                           "Destroyer",
                                                           JOptionPane.PLAIN_MESSAGE,
                                                           null,
                                                           possibilities,
                                                           "Select ship" );
            if(s.equals("Destroyer")){
              possibilities[0]="";
            }
            else if(s.equals("Submarine")){
              possibilities[1]="";
            }
            else if(s.equals("Cruiser")){
              possibilities[2]="";
            }
            else if(s.equals("Battleship")){
              possibilities[3]="";
            }
            else  if(s.equals("Carrier")){
              possibilities[4]="";
            }
            processor.updateList(2);
            hit++;
          }
          System.out.println(n);
          
          
          if (carrierC == 5){
            JOptionPane.showMessageDialog(mainPanel, "You have sunk the CARRIER.");
            sunk++;
            shipsField.setText(sunk + " ");
            carrierC = 0;
            break;
          } else if (battleshipC == 4){
            JOptionPane.showMessageDialog(mainPanel, "You have sunk the BATTLESHIP.");
            sunk++;
            shipsField.setText(sunk + " ");
            battleshipC = 0;
            break;
          } else if (cruiserC == 3){
            JOptionPane.showMessageDialog(mainPanel, "You have sunk the CRUISER.");
            sunk++;
            shipsField.setText(sunk + " ");
            cruiserC = 0;
            break;
          } else if (submarineC == 3){
            JOptionPane.showMessageDialog(mainPanel, "You have sunk the SUBMARINE.");
            sunk++;
            shipsField.setText(sunk + " ");
            submarineC = 0;
            break;
          } else if (destroyerC == 2){
            JOptionPane.showMessageDialog(mainPanel, "You have sunk the DESTROYER.");
            sunk++;
            shipsField.setText(sunk + " ");
            destroyerC = 0;
            break;
          }
        }      
        
        
        
        
        if (win == 17) { //after all 17 ship spots have been hit
          JOptionPane.showMessageDialog (mainPanel, "Congratulations! You won!");
          gamePanel.setVisible(false);
          endPanel.setVisible(true);
          int userscore = (sunk *100)+(win*25)-(miss*10);
          int compscore = (compsink *100)+(hit*25)-(compmiss*10);
          userscores.setText("<html>YOUR SCORES:<br># of ships you sunk: "+sunk+"<br># of hits: "+win+"<br># of shots :"+(win+miss)+"<br>FINAL SCORE:"+userscore);
          compscores.setText("<html>COMPUTER'S SCORES:<br># of computer sank: "+compsink+"<br># of hits: "+hit+"<br># of shots :"+(hit+compmiss)+"<br>FINAL SCORE:"+compscore);
          break;//breaks out of the loop to prevent repeat
        }
        else if (hit == 17) {
          JOptionPane.showMessageDialog (mainPanel, "The computer has sunk all of the ships!");
          gamePanel.setVisible(false);
          endPanel.setVisible(true);
          int userscore = (sunk *100)+(win*25)-(miss*10);
          int compscore = (compsink *100)+(hit*25)-(compmiss*10);
          userscores.setText("<html>YOUR SCORES:<br># of ships you sunk: "+sunk+"<br># of hits: "+win+"<br># of shots :"+(win+miss)+"<br>FINAL SCORE:"+userscore);
          compscores.setText("<html>COMPUTER'S SCORES:<br># of computer sank: "+compsink+"<br># of hits: "+hit+"<br># of shots :"+(hit+compmiss)+"<br>FINAL SCORE:"+compscore);
          
          break;//breaks out of the loop to prevent repeat
        }
        else if (tie == 50) {
          JOptionPane.showMessageDialog(mainPanel, "50 shots have been fired. The game is over. Please see below for scores.");
          gamePanel.setVisible(false);
          endPanel.setVisible(true);
          JOptionPane.showMessageDialog(mainPanel, "You had " + win + " hits and the computer had " + hit + " hits."); // tells them your score
          int userscore = (sunk *100)+(win*25)-(miss*10);
          int compscore = (compsink *100)+(hit*25)-(compmiss*10);
          userscores.setText("<html>YOUR SCORES:<br># of ships you sunk: "+sunk+"<br># of hits: "+win+"<br># of shots :"+(win+miss)+"<br>FINAL SCORE:"+userscore);
          compscores.setText("<html>COMPUTER'S SCORES:<br># of computer sank: "+compsink+"<br># of hits: "+hit+"<br># of shots :"+(hit+compmiss)+"<br>FINAL SCORE:"+compscore);
          break;
        }
        
      }
    }
    if (command.equals("  START  ")){
      startPanel.setVisible(false);
      gamePanel.setVisible(true);
      
    }
    
    if (command.equals("Exit")){
      System.exit(0); //if they press exit
    }
    else if (command.equals("Help")){ //if the press help
      startPanel.setVisible(true);
      gamePanel.setVisible(false); 
      start.setText("CONTINUE");
    }
    else if(command.equals("CONTINUE")){
      startPanel.setVisible(false);
      gamePanel.setVisible(true); 
      start.setText("  START  ");
    }
    
    
    else if (command.equals("Misses:")){ 
      
      
      
    }
    
    else if (command.equals("Hits:")){
      
      
      
    }
    else if (command.equals("missField")){
      System.out.println();
    }
    else if (command.equals("shipsField")){
      System.out.println();
      
      
      
      
    }
  }

  private String getInstructions(){
	return  "<html>Welcome to the Battleship Game!<br>The following computer software is an "
			+ "adaptation <br>of the classic board game of the same name.<br> The object of the "
			+ "game is to sink all of your opponent's ships. <br>In this game, you (the human) will "
			+ "verse the computer (the AI).<br>You will be playing ON PAPER. <br>Before starting "
			+ "the game, please make sure you understand ALL of <br>the following instructions"
			+ ":<br>1. After printing the grid for yourself, place your 5 <br>ships on the grid "
			+ "however you may like.<br> 2. You will go first by choosing a square on the 10 x 10 "
			+ "grid.<br> The computer will tell you if your choice was a HIT or a MISS. <br>If it "
			+ "was a HIT, the computer will tell you which ship you hit.<br>3. Then, the computer "
			+ "will output a certain row and column <br>number which corresponds to a square on your "
			+ "grid.<br> REMEMBER, the numbers labeling the left are ROWS, and the numbers "
			+ "<br>labeling the top are COLUMNS. <br>You will then tell the computer whether it was "
			+ "a HIT or MISS.<br>4. The game will continue until: a) either the <br>player (you) or "
			+ "the computer has sunk ALL of the other player's ships,<br> orb) You have made a total "
			+ "of 50 moves, including HITS and MISSES.<br>5. At the end of the game, the software "
			+ "will tell you the results<br> of the game, and who won.<br>After reading these "
			+ "instructions, press START and have fun!";

}
}

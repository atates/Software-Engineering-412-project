import java.lang.Math;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.io.*;
public class GameMain{
  
  public static void main(String[] args) throws IOException{
    Scanner scan = new Scanner(System.in);
    System.out.println("Welcome to the game menu! Before we being, please enter your first and last name, separated by an underscore (_).");
    String name = scan.nextLine();
    int option = menu();
    
    switch (option) {
      case 1:
        System.out.println("Monday");
        break;
      case 2:
        int score = rideTimes();
        System.out.println("Your Final Score for Ride Times is " + score*10 + "%!");
        System.out.println("Would you like to save this score to a downloadable file on your computer?");
        saveToFile(score, name);
        break;
      case 3:
        System.out.println("Wednesday");
        break;
      case 0:
        System.out.println("Have a good day!");
        break;
    }
  }
  
  public static void saveToFile(int score, String name)  throws IOException{
    FileWriter fwriter = new FileWriter("Statistics.txt", true);
    PrintWriter statFile = new PrintWriter(fwriter);
    PrintWriter outputFile = new PrintWriter(name + "_GameSession_" + getDate() + "_" + getTime() + ".txt");
    outputFile.println("It works.");
    outputFile.close();
  }
  
  public static String getTime(){
    String time = "" + LocalDateTime.now();
    time = time.substring(time.indexOf("T")+1,time.indexOf("T")+9);
    for(int i =0; i < time.length()-1;i++)
    {
      String beginning = "";
      
      if(time.charAt(i)==':'){
        beginning = time.substring(0,i);
        time = beginning + "_" + time.substring(i+1);
      }
    }
    return time;
  }
  
  public static String getDate(){
    String date = "" + LocalDateTime.now();
    date = date.substring(0, date.indexOf("T"));
    return date;
  }
  public static int menu() { 
    int option; 
    Scanner input = new Scanner(System.in); 
    do { 
      System.out.println("Your options are:"); 
      System.out.println("-----------------"); 
      System.out.println("\t1) Play Wack-a-Mole"); 
      System.out.println("\t2) Play Ride Times Conversion"); 
      System.out.println("\t3)Statistics from this session"); 
      System.out.println("\t0)EXIT");  
      System.out.print("\nPlease enter a valid mumber: "); 
      option = input.nextInt(); 
      System.out.println(); 
    } while(option < 0 || option > 3); 
    return option; 
  }
  
  //rideTimes creates a game in which the system spits out a ride time in seconds. The user must then return
  //the correct minute conversion for that value. This value must be a double, with all digits before the decimal point being minutes
  //and all after being seconds, not to exceed .6, which would immediately round up to another minute.
  public static double secToMin(int d){
    if(d <60)
      System.out.println("Number too low");
    double conversion=0;
    conversion = (((double)((int)(((double)d/60)*100)))/100);
    
    return conversion;
  }
  public static int rideTimes(){
    System.out.println("This game tests your division skills. You will be given a number to divide. Good Luck!\n");
    Scanner keyboard = new Scanner(System.in);
    double answer = 0.0;
    int finalScore = 0;
    int question = 0;
    for (int i = 0; i <10; i++){
      question = (int)((Math.random()*((600-60) + 1)) + 60);
      System.out.println("This ride has a run-time of " + question + " seconds. Please convert it to minutes, rounding to" +
                         " two decimal places.");
      
      answer = getDouble(keyboard,"");
      if (answer == secToMin(question)){
        finalScore+=1;
        System.out.println("Good Job!\n");
        
      }else
        System.out.println("Sorry, but the answer was " + secToMin(question) + "!\n");
    }
    return finalScore;
  }
  public static double getDouble(Scanner input, String prompt){
    System.out.print(prompt);
    while(!input.hasNextDouble()){
      input.next();
      System.out.println("Not a double! Try again");
    }
    return input.nextDouble();
  }
  
  public static String getString(Scanner input, String prompt){
    System.out.print(prompt);
    while(!input.hasNextLine()){
      input.next();
      System.out.println("Not a word! Try again");
    }
    return input.nextLine();
    
  }
}

/**
 
 *
 * Program Plays a Whack-a-Mole style game via a GUI. Relies upon the Creature sub-class of JButton to facilitate the interaction between the player and 
 * the moles, called creatures in the code for re-usability. The game offers the user the option to play again or end the program after each attempt to beat the game. 
 * 
 */

import javax.swing.*;



import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.*;


public class Menu extends JFrame implements ActionListener{
	
	JFrame window, window2;
	
	Container con;
	
	JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, 
	whackPanel, ridePanel, statPanel, 
	whackPanel2, ridePanel2, statPanel2,
	whackPanel3, ridePanel3, ridePanel4, whackPan, RidePan, statPan,
	playerPanel2, statPanel3;

	JScrollPane stat;
	
	JLabel titleNameLabel, subLabel, triesLabel, triesLabelNo, lvlLabel, lvlLabelNo, triesLabelNo2, lvlLabel2, lvlLabelNo2,triesLabel2, statLabel2;
	
	Font titleFont= new Font("Lucida Console", Font.PLAIN, 90);
	Font gameFont= new Font("Monospaced", Font.BOLD, 70);
	Font normalFont= new Font("Lucida Console", Font.PLAIN, 20);
	Font mediumFont= new Font("Lucida Console", Font.PLAIN, 30);
	Font smallFont= new Font("Lucida Console", Font.PLAIN, 10);
	Font boldFont= new Font("Lucida Console", Font.BOLD, 10);
	
	JButton startButton, mainScreenExit, choice1, choice2, choice3, choice4, returnWhack, 
	returnRide, returnStat, 
	continueWhack, continueRide, continueStat, playRide, exitRide;
	
	JTextArea mainTextArea, whackText, rideText, statText, statText3 ,
	whackText2 , rideText2, statText2,
	whackText3 , rideText3, statText4,statText5,
	whackText4, whackText5, whackText6;
	
	JTextField whackTxt, RideTxt, statTxt;

	TitleScreenHandler tsHandler= new TitleScreenHandler();
	TitleScreenExitHandler exitHandler= new TitleScreenExitHandler();
	ChoiceHandler choiceHandler = new ChoiceHandler();
	
	int lvl;
	int lives;
	
	
	public static void main(String[] args) {
		
		
		
		//create an instance of the game and display start message 
	
		new Menu();
		
	}
		/*
		 * JOptionPane.showMessageDialog(this_game,"Hello, Welcome to our fun-filled gaming application!");
		 
		JOptionPane.showMessageDialog(this_game,"Your options are \n"
				+ "1.) Play Wack-a-Mole \n"
				+ "2.) Play Ride Times Conversion \n"
				+ "3.) View statstics from the session \n"
				+ "4.) Exit \n"
				);
		Object[] options = {"Play Wack-a-Mole",
                "Play Ride Times Conversion",
                "View statstics from the session"," Exit"};
int n = JOptionPane.showOptionDialog(this_game,
"Please choose an option: "
+ "with that ham?",
"A Silly Question",
JOptionPane.DEFAULT_OPTION,
JOptionPane.QUESTION_MESSAGE,
null,
options,
options[2]);

if (n==0) {
	JOptionPane.showMessageDialog(this_game,"You have chose to play Whack-A-Mole!");
}
else if (n==1) {
	JOptionPane.showMessageDialog(this_game,"You have chosen to play Ride Times Conversion!");
}
else if (n==2) {
JOptionPane.showMessageDialog(this_game,"You have chosen to view scores!");
}
else if (n==3) {
System.exit(0);	
}


				
	//loop to enable the user the continuously replay the game, the loop ends when the player chooses to not replay the game at the end of the game
		while(true) {
			
			//keep playing levels until the player does not reach target score or the last level is played 
			while(level <= MAX_LEVEL) {
				
				//announce level number and number of creatures to warn player before timer starts 
				JOptionPane.showMessageDialog(this_game, "Level " + level + "\n Number of Moles: " + numOfCreatures + "\n Press OK to begin level.");
				
				//play the level 
				this_game.playGame(); 
				
				//if target score is not reached end the game 
				if(score < TARGET_SCORE) {
					JOptionPane.showMessageDialog(this_game, "Level " + level + " Score: " + score + "\n Did not get to " + TARGET_SCORE + " points.  Game Over");
					break; 
					}
				
				//increment appropriate values to prepare  for next level 
				//nextLevel();
				
				//if there are levels left to play then re-instantiate Game to reflect changes
				if(level <= MAX_LEVEL) {
					this_game.dispose();
					this_game = new Menu();
				}   	
			}
			
			//if player has beat the last level display success message 
			if(level > MAX_LEVEL)
				JOptionPane.showMessageDialog(this_game, "Congratulations, you have won the game!");
			
			//display ending screen where the player decides to play again or not 
			int response = JOptionPane.showConfirmDialog(this_game, "Thank you for playing!\n Do you want to play again?", "Play Again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			//if the player decides not to play again or closes the box exit the loop to begin closing the game, otherwise let the game re-set itself
			if (response == JOptionPane.NO_OPTION || response == JOptionPane.CLOSED_OPTION) 
				break; 
			
			//to facilitate playing the game again reset the appropriate variables to the way they were at the start of the game and re-instantiate the game to reflect the changes
			//resetLevel(); 
			this_game.dispose();
			this_game = new Menu();
		
		}
		
		//dispose of game hence ending the program 
		this_game.dispose(); 
		 

	}
	//updates the time left in the game setting the value 
	//displayed by the time label equal to the time remaining 
	//variable 
	/*
	public static void update_time(double timeRemaining) {
		//update the time label 
		timeLabel.setText(TIME_PREFIX + NumberFormat.getInstance().format(timeRemaining/1000)); 		
	}
	
	//increments the score variable while updating the score label
		public static void update_score() {
			score += 10; 
			scoreLabel.setText(SCORE_PREFIX + score); 		
		}
	*/
	public Menu() {
		//set JFrames size, layout, close operation, and title 
		window = new JFrame();
		window.setSize(800, 600); 
		window.setLayout(null); 
		window.setVisible(true);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setTitle("Whack-a-Mole");
		con = window.getContentPane();
		
		titleNamePanel= new JPanel();
		titleNamePanel.setBounds(100,100,600,150);
		titleNamePanel.setBackground(Color.black);
	
		
		titleNameLabel= new JLabel("US of Parks");
		titleNameLabel.setForeground(Color.green);
		titleNameLabel.setFont(titleFont);
		
		subLabel=new JLabel ("Were education meets fun!");
		subLabel.setForeground(Color.yellow);
		subLabel.setFont(normalFont);
		
		
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(300,400,200,100);
		startButtonPanel.setBackground(Color.black);
		startButtonPanel.setLayout(new GridLayout(2,1));
		
		startButton= new JButton("START");
		startButton.setBackground(Color.red);
		startButton.setForeground(Color.blue);
		startButton.setFont(normalFont);
		startButton.addActionListener(tsHandler);
		startButton.setFocusPainted(false);
		
		mainScreenExit= new JButton("EXIT");
		mainScreenExit.setBackground(Color.blue);
		mainScreenExit.setForeground(Color.yellow);
		mainScreenExit.setFont(normalFont);
		mainScreenExit.addActionListener(exitHandler);

		
		titleNamePanel.add(titleNameLabel);
		titleNamePanel.add(subLabel);
		startButtonPanel.add(startButton);
		startButtonPanel.add(mainScreenExit);
		
		con.add(titleNamePanel);
		con.add(startButtonPanel);	
	}
	
	public void createGameScreen() {
		
	titleNamePanel.setVisible(false);
	startButtonPanel.setVisible(false);
	
		
		mainTextPanel=new JPanel();
		mainTextPanel.setBounds(140, 100, 600, 250);
		mainTextPanel.setBackground(Color.red);
		con.add(mainTextPanel);
		
		mainTextArea= new JTextArea(" Welcome to the US of Parks! \n\n A healthy way to game! \n\n Please select a menu option:");
		mainTextArea.setBounds(140,100,600,250);
		mainTextArea.setBackground(Color.white);
		mainTextArea.setForeground(Color.green);
		mainTextArea.setFont(mediumFont);
		mainTextArea.setLineWrap(true);
		mainTextArea.setEditable(false);
		mainTextPanel.add(mainTextArea);
		
		choiceButtonPanel= new JPanel();
		choiceButtonPanel.setBounds(250,390,300,150);
		choiceButtonPanel.setBackground(Color.black);
		choiceButtonPanel.setLayout(new GridLayout(4,1));
		con.add(choiceButtonPanel);
	
		choice1 = new JButton( "Play Wack-a-Mole" );
		choice1.setBackground(Color.green);
		choice1.setForeground(Color.red);
		choice1.setFont(smallFont);
		choiceButtonPanel.add(choice1);
		choice1.setFocusPainted(false);
		choice1.addActionListener(choiceHandler);
		choice1.setActionCommand("c1");
		
		choice2 = new JButton("Play Ride Times Conversion");
		choice2.setBackground(Color.yellow);
		choice2.setForeground(Color.blue);
		choice2.setFont(smallFont);
		choiceButtonPanel.add(choice2);
		choice2.setFocusPainted(false);
		choice2.addActionListener(choiceHandler);
		choice2.setActionCommand("c2");
		
		choice3 = new JButton("View statistics");
		choice3.setBackground(Color.red);
		choice3.setForeground(Color.yellow);
		choice3.setFont(smallFont);
		choiceButtonPanel.add(choice3);
		choice3.setFocusPainted(false);
		choice3.addActionListener(choiceHandler);
		choice3.setActionCommand("c3");
		
		choice4 = new JButton("Exit");
		choice4.setBackground(Color.blue);
		choice4.setForeground(Color.green);
		choice4.setFont(smallFont);
		choiceButtonPanel.add(choice4);
		choice4.setFocusPainted(false);
		choice4.addActionListener(choiceHandler);
		choice4.setActionCommand("c4");
		
		playerPanel=new JPanel();
		playerPanel.setBounds(160,15,600,50);
		playerPanel.setBackground(Color.black);
		playerPanel.setLayout(new GridLayout(1,4));
		con.add(playerPanel);
		

	}
	
	public void whackamoleScreen() {
		
		mainTextPanel.setVisible(false);
		choiceButtonPanel.setVisible(false);
		
		whackPanel= new JPanel();
		whackPanel.setBounds(100, 100, 600, 250);
		whackPanel.setBackground(Color.black);
		con.add(whackPanel);
		
		 String name = JOptionPane.showInputDialog(null, "Please Enter your full name before continuing:" , "Attention:", JOptionPane.INFORMATION_MESSAGE);
		 JOptionPane.showMessageDialog(null, "Thank You" , ":)", JOptionPane.PLAIN_MESSAGE);
		
		whackText= new JTextArea(" Welcome to Whack-A-Mole");
		whackText.setBounds(100,100,600,250);
		whackText.setBackground(Color.black);
		whackText.setForeground(Color.white);
		whackText.setFont(mediumFont);
		whackText.setLineWrap(true);
		whackText.setEditable(false);
		whackPanel.add(whackText);
		
		triesLabel=new JLabel("Lives Left:");
		triesLabel.setFont(normalFont);
		triesLabel.setForeground(Color.white);
		playerPanel.add(triesLabel);
		
		triesLabelNo= new JLabel(""+lives);
		triesLabelNo.setFont(normalFont);
		triesLabelNo.setForeground(Color.yellow);
		playerPanel.add(triesLabelNo);
		
		lvlLabel= new JLabel("Level: ");
		lvlLabel.setFont(normalFont);
		lvlLabel.setForeground(Color.white);
		playerPanel.add(lvlLabel);
		
		lvlLabelNo= new JLabel(""+lvl);
		lvlLabelNo.setFont(normalFont);
		lvlLabelNo.setForeground(Color.white);
		playerPanel.add(lvlLabelNo);
		
		whackPan= new JPanel();
		whackPan.setBounds(250,390,300,150);
		whackPan.setBackground(Color.red);
		whackPan.setLayout(new GridLayout(2,1));
		
		
		con.add(whackPan);
		
		returnWhack = new JButton( "Return" );
		returnWhack.setBackground(Color.black);
		returnWhack.setForeground(Color.white);
		returnWhack.setFont(smallFont);
		
		continueWhack = new JButton( "Continue" );
		continueWhack.setBackground(Color.black);
		continueWhack.setForeground(Color.white);
		continueWhack.setFont(smallFont);
		
		whackPan.add(returnWhack);
		whackPan.add(continueWhack);
		
		returnWhack.setFocusPainted(false);
		returnWhack.addActionListener(choiceHandler);
		returnWhack.setActionCommand("returnW");
		
		continueWhack.setFocusPainted(false);
		continueWhack.addActionListener(choiceHandler);
		continueWhack.setActionCommand("continueW");
		
		
	
		playerSetup();
		
	}
	
	
	public void whackamoleScreen2(){
	
		lvlLabelNo.setVisible(false);
		triesLabelNo.setVisible(false);
	
	
	
	playerPanel.setVisible(false);
	mainTextPanel.setVisible(false);
	choiceButtonPanel.setVisible(false);
	whackPanel.setVisible(false);
	whackPan.setVisible(false);
	
	whackPanel2= new JPanel();
	whackPanel2.setBounds(100, 60, 600, 400);
	whackPanel2.setBackground(Color.black);
	whackPanel2.setVisible(true);
	con.add(whackPanel2);
	
	whackPanel3= new JPanel();
	whackPanel3.setBounds(100,500,600,200);
	whackPanel3.setBackground(Color.black);
	whackPanel3.setVisible(true);
	con.add(whackPanel3);
	
	
	playerPanel2=new JPanel();
	playerPanel2.setBounds(100,15,600,50);
	playerPanel2.setBackground(Color.red);
	playerPanel2.setLayout(new GridLayout(1,4));
	con.add(playerPanel2);
	

	

	 int[] mole1 = new int[] {41, 25, 42, 17, 14, 7, 2, 8, 16};
     int[] mole2 = new int[] {40, 23, 34, 28, 8, 4, 13, 20, 25};
     int[] mole3 = new int[] {13, 43, 19, 5, 18, 20, 21, 33, 17};
     int[] choices = RandomizeArray(1, 3);
     int score = 0;
     int option;
     int answer=0;;
     String dialog;
     
     
	for (int i = 0; i<3; i++){
        option = choices[i];
    switch (option) {
      case 1:
    	  whackText2= new JTextArea("" );
    		whackText2.setBounds(100,300,600,200);
    		whackText2.setBackground(Color.green);
    		whackText2.setForeground(Color.white);
    		whackText2.setFont(gameFont);
    		whackText2.setLineWrap(true);
    		whackPanel2.add(whackText2);
    	  whackText3= new JTextArea ("Please enter the number in the above array which is a multiple of 3");
    	  whackText3.setBounds(10,10,600,200);
    	  whackText3.setBackground(Color.white);
    	  whackText3.setForeground(Color.white);
    	  whackText3.setFont(normalFont);
    	  whackText3.setLineWrap(true);
    	  whackPanel3.add(whackText3);
    	  
    	  
    	  
          System.out.println(mole1[0] + ", " + mole1[1] + ", " + mole1[2] + ", ");
          System.out.println(mole1[3] + ", " + mole1[4] + ", " + mole1[5] + ", ");
          System.out.println(mole1[6] + ", " + mole1[7] + ", " + mole1[8]);
          System.out.println();
          System.out.println("The Prime Number is: 3");
  //        answer = getInt(keyboard, "Please enter the number in the above array which is a multiple of 3\n");
          String answerr = JOptionPane.showInputDialog("Please Enter you're answer:");
      	
      	 answer = (int) Double.parseDouble(answerr);
          if (answer == 42){
              System.out.println("Good job!\n");
          score++;
          }
          else
          System.out.println("Sorry but that was the wrong answer!\n");
          break;
      case 2:
    	  whackText2= new JTextArea( );
    		whackText2.setBounds(100,300,600,200);
    		whackText2.setBackground(Color.green);
    		whackText2.setForeground(Color.white);
    		whackText2.setFont(gameFont);
    		whackText2.setLineWrap(true);
    		whackPanel2.add(whackText2);
    	  whackText3= new JTextArea ("2");
    	  whackText3.setBounds(10,10,600,200);
    	  whackText3.setBackground(Color.black);
    	  whackText3.setForeground(Color.white);
    	  whackText3.setFont(normalFont);
    	  whackText3.setLineWrap(true);
    	  whackPanel3.add(whackText3);
    	  
          System.out.println(mole2[0] + ", " + mole2[1] + ", " + mole2[2] + ", ");
          System.out.println(mole2[3] + ", " + mole2[4] + ", " + mole2[5] + ", ");
          System.out.println(mole2[6] + ", " + mole2[7] + ", " + mole2[8]);
          System.out.println();
          System.out.println("The Prime Number is: 7");
    //      answer = getInt(keyboard, "Please enter the number in the above array which is a multiple of 7\n");
          if (answer == 28){
              System.out.println("Good job!\n");
          score++;
          }
          else
          System.out.println("Sorry but that was the wrong answer!\n");
          break;
      case 3:
    	  whackText2= new JTextArea( );
    		whackText2.setBounds(100,300,600,200);
    		whackText2.setBackground(Color.green);
    		whackText2.setForeground(Color.white);
    		whackText2.setFont(gameFont);
    		whackText2.setLineWrap(true);
    		whackPanel2.add(whackText2);
    	  whackText3= new JTextArea ("3");
    	  whackText3.setBounds(10,10,600,200);
    	  whackText3.setBackground(Color.black);
    	  whackText3.setForeground(Color.white);
    	  whackText3.setFont(normalFont);
    	  whackText3.setLineWrap(true);
    	  whackPanel3.add(whackText3);
    	  
          System.out.println(mole3[0] + ", " + mole3[1] + ", " + mole3[2] + ", ");
          System.out.println(mole3[3] + ", " + mole3[4] + ", " + mole3[5] + ", ");
          System.out.println(mole3[6] + ", " + mole3[7] + ", " + mole3[8]);
          System.out.println();
          System.out.println("The Prime Number is: 6");
   //       answer = getInt(keyboard, "Please enter the number in the above array which is a multiple of 6\n");
          if (answer == 18){
              System.out.println("Good job!\n");
          score++;
          }
          else
          System.out.println("Sorry but that was the wrong answer!\n");
          break;
    }
	}
	/*
	
	for (int i = 0; i<3; i++){
	    option = choices[i];
	}
	if(option== 1) {
		
	lvlLabelNo.setVisible(false);
	triesLabelNo.setVisible(false);
	
		
		whackText2= new JTextArea(
	    mole1[0] + " , " + mole1[1] + ", " + mole1[2] + " \n" 
	   +mole1[3] + " , " + mole1[4] + ", " + mole1[5] + " \n" 
	   +mole1[6] + "  , " + mole1[7] + " , " + mole1[8] );
		whackText2.setBounds(100,300,600,200);
		whackText2.setBackground(Color.black);
		whackText2.setForeground(Color.white);
		whackText2.setFont(gameFont);
		whackText2.setLineWrap(true);
		whackPanel2.add(whackText2);
	
	    
	whackText3= new JTextArea ("The Prime Number is: 3 Please enter the number in the above array which is a multiple of 3");
	whackText3.setBounds(100,300,600,200);
	whackText3.setBackground(Color.black);
	whackText3.setForeground(Color.white);
	whackText3.setFont(normalFont);
	whackText3.setLineWrap(true);
	whackPanel3.add(whackText3);
	
	while(lives>0) {
	
	String answerr = JOptionPane.showInputDialog("Please Enter you're answer:");
	
	double answerrr = Double.parseDouble(answerr);
	

	    
	
	
	   if (answerrr == 42){
	    	
		    JOptionPane.showMessageDialog(null, "You have entered:",+answerrr+ "Good job!", JOptionPane.PLAIN_MESSAGE);
		       
		    lvl++;
		    
		    whackPanel2.repaint();
		    whackPanel3.repaint();
		    whackText3.repaint();
		    whackText2.repaint();
		    
	   }
	   
	   else {
		   JOptionPane.showMessageDialog(null, "Sorry, but that was the wrong answer." , ",", JOptionPane.PLAIN_MESSAGE);
		    lives--;
		    JOptionPane.showMessageDialog(null, "Lives Left :" +lives, ",", JOptionPane.PLAIN_MESSAGE);
		    dialog = JOptionPane.showInputDialog("Try Again:");
		    answer = Integer.parseUnsignedInt(dialog);
		  playerPanel.updateUI();
	   }
	
	}
	}
	
	
	
	 if(option==2) {
		
		lvlLabelNo.setVisible(false);
		triesLabelNo.setVisible(false);
		
			
			whackText2= new JTextArea(
		    mole1[0] + " , " + mole1[1] + ", " + mole1[2] + " \n" 
	       +mole1[3] + " , " + mole1[4] + ", " + mole1[5] + " \n" 
	       +mole1[6] + "  , " + mole1[7] + " , " + mole1[8] );
			whackText2.setBounds(100,300,600,200);
			whackText2.setBackground(Color.black);
			whackText2.setForeground(Color.white);
			whackText2.setFont(gameFont);
			whackText2.setLineWrap(true);
			whackPanel2.add(whackText2);
		
	        
		whackText3= new JTextArea ("The Prime Number is: 3 Please enter the number in the above array which is a multiple of 3");
		whackText3.setBounds(100,300,600,200);
		whackText3.setBackground(Color.black);
		whackText3.setForeground(Color.white);
		whackText3.setFont(normalFont);
		whackText3.setLineWrap(true);
		whackPanel3.add(whackText3);
		
		 dialog = JOptionPane.showInputDialog("Please Enter you're answer:");
	     answer = Integer.parseUnsignedInt(dialog);
	     
	        if (answer == 28){
	        	
	        JOptionPane.showMessageDialog(null, "You have entered:" +answer, "Good job!", JOptionPane.PLAIN_MESSAGE);
	           
	        lvl++;
	        
	        whackPanel2.repaint();
	        whackPanel3.repaint();
	        whackText3.repaint();
	        whackText2.repaint();
	        
	        }
	        else
	        	JOptionPane.showMessageDialog(null, "Sorry, but that was the wrong answer." , ",", JOptionPane.PLAIN_MESSAGE);
	        lives--;
	        
	        whackPanel2.repaint();
	        whackPanel3.repaint();
	        whackText3.repaint();
	        whackText2.repaint();
	        
	        JOptionPane.showMessageDialog(null, "Lives Left :" +lives, ",", JOptionPane.PLAIN_MESSAGE);
	}
	else if(option==3) {
		
		lvlLabelNo.setVisible(false);
		triesLabelNo.setVisible(false);
		
			
			whackText2= new JTextArea(
		    mole1[0] + " , " + mole1[1] + ", " + mole1[2] + " \n" 
	       +mole1[3] + " , " + mole1[4] + ", " + mole1[5] + " \n" 
	       +mole1[6] + "  , " + mole1[7] + " , " + mole1[8] );
			whackText2.setBounds(100,300,600,200);
			whackText2.setBackground(Color.black);
			whackText2.setForeground(Color.white);
			whackText2.setFont(gameFont);
			whackText2.setLineWrap(true);
			whackPanel2.add(whackText2);
		
	        
		whackText3= new JTextArea ("The Prime Number is: 3 Please enter the number in the above array which is a multiple of 3");
		whackText3.setBounds(100,300,600,200);
		whackText3.setBackground(Color.black);
		whackText3.setForeground(Color.white);
		whackText3.setFont(normalFont);
		whackText3.setLineWrap(true);
		whackPanel3.add(whackText3);
		
		 dialog = JOptionPane.showInputDialog("Please Enter you're answer:");
	     answer = Integer.parseUnsignedInt(dialog);
	     
	        if (answer == 18){
	        	
	        JOptionPane.showMessageDialog(null, "You have entered:  " +answer, "Good job!", JOptionPane.PLAIN_MESSAGE);
	           
	        lvl++;
	        whackPanel2.repaint();
	        whackPanel3.repaint();
	        whackText3.repaint();
	        whackText2.repaint();
	        
	        
	        
	        }
	        else
	        	JOptionPane.showMessageDialog(null, "Sorry, but that was the wrong answer.", ",", JOptionPane.PLAIN_MESSAGE);
	        lives--;
	        whackPanel2.repaint();
	        whackPanel3.repaint();
	        whackText3.repaint();
	        whackText2.repaint();
	        
	        
	        JOptionPane.showMessageDialog(null, "Lives Left :" +lives, ",", JOptionPane.PLAIN_MESSAGE);
	        */
	}
		
	
	public void rideTimesScreen() {
		
		mainTextPanel.setVisible(false);
		choiceButtonPanel.setVisible(false);
		
		
		
		 
		whackPanel= new JPanel();
		whackPanel.setBounds(100, 100, 600, 250);
		whackPanel.setBackground(Color.black);
		con.add(whackPanel);
		
		whackText= new JTextArea(" Welcome to Ride Times Conversion");
		whackText.setBounds(100,100,600,250);
		whackText.setBackground(Color.black);
		whackText.setForeground(Color.green);
		whackText.setFont(mediumFont);
		whackText.setLineWrap(true);
		whackText.setEditable(false);
		whackPanel.add(whackText);
		
		triesLabel=new JLabel("Lives Left:");
		triesLabel.setFont(normalFont);
		triesLabel.setForeground(Color.blue);
		playerPanel.add(triesLabel);
		
		triesLabelNo= new JLabel(""+lives);
		triesLabelNo.setFont(normalFont);
		triesLabelNo.setForeground(Color.yellow);
		playerPanel.add(triesLabelNo);
		
		lvlLabel= new JLabel("Level: ");
		lvlLabel.setFont(normalFont);
		lvlLabel.setForeground(Color.red);
		playerPanel.add(lvlLabel);
		
		lvlLabelNo= new JLabel(""+lvl);
		lvlLabelNo.setFont(normalFont);
		lvlLabelNo.setForeground(Color.white);
		playerPanel.add(lvlLabelNo);
		
		whackPan= new JPanel();
		whackPan.setBounds(250,390,300,150);
		whackPan.setBackground(Color.red);
		whackPan.setLayout(new GridLayout(2,1));
		
		
		con.add(whackPan);
		
		returnWhack = new JButton( "Return" );
		returnWhack.setBackground(Color.blue);
		returnWhack.setForeground(Color.yellow);
		returnWhack.setFont(normalFont);
		
		continueWhack = new JButton( "Continue" );
		continueWhack.setBackground(Color.green);
		continueWhack.setForeground(Color.red);
		continueWhack.setFont(normalFont);
		
		whackPan.add(returnWhack);
		whackPan.add(continueWhack);
		
		returnWhack.setFocusPainted(false);
		returnWhack.addActionListener(choiceHandler);
		returnWhack.setActionCommand("returnW");
		
		continueWhack.setFocusPainted(false);
		continueWhack.addActionListener(choiceHandler);
		continueWhack.setActionCommand("continueR");
		
		
	
		playerSetup();
		
			
	}
	
	
	public void rideTimesScreen2() {
			 
	whackText.setVisible(true);
	whackPan.setVisible(false);
	choiceButtonPanel.setVisible(false);
	
	ridePanel= new JPanel();
	ridePanel.setBounds(100, 100, 600, 250);
	ridePanel.setBackground(Color.black);
	con.add(ridePanel);
	
	whackText.setText(" This game tests your division skills. You will \n be given a number to divide. Good Luck!");
	whackText.setFont(normalFont);
	ridePanel3= new JPanel();
	ridePanel3.setBounds(250,390,300,150);
	ridePanel3.setBackground(Color.black);
	ridePanel3.setLayout(new GridLayout(3,1));
	con.add(ridePanel3);
	
	playRide = new JButton( "Play" );
	playRide.setBackground(Color.black);
	playRide.setForeground(Color.yellow);
	playRide.setFont(normalFont);
	playRide.addActionListener(choiceHandler);
	playRide.setActionCommand("playR");
	
	exitRide = new JButton( "Exit" );
	exitRide.setBackground(Color.black);
	exitRide.setForeground(Color.red);
	exitRide.setFont(normalFont);
	exitRide.addActionListener(choiceHandler);
	exitRide.setActionCommand("exitR");
	
	ridePanel3.add(playRide);
	ridePanel3.add(exitRide);
	
	     
	}
	public void rideTimesScreen3() throws IOException {
			
			int rideScore=0;
		 	double answer = 0.0;
		    int finalScore = 0;
		    int question = 0;
		    
		    String name = JOptionPane.showInputDialog(null, "Please Enter your full name before continuing:" , "Attention:", JOptionPane.INFORMATION_MESSAGE);
			 JOptionPane.showMessageDialog(null, "Thank You" , ":)", JOptionPane.PLAIN_MESSAGE);
			
		whackText.setVisible(true);
		whackPan.setVisible(false);
		choiceButtonPanel.setVisible(false);
		ridePanel4= new JPanel();
		ridePanel4.setBounds(250,390,300,150);
		ridePanel4.setBackground(Color.black);
		ridePanel4.setLayout(new GridLayout(2,1));
		con.add(ridePanel3);
		ridePanel2= new JPanel();
		ridePanel2.setBounds(100, 100, 600, 250);
		ridePanel2.setBackground(Color.black);
		con.add(ridePanel2);
		
		
		 for (int i = 0; i <10; i++){
		      question = (int)((Math.random()*((600-60) + 1)) + 60);
		      
		whackText.setText("This ride has a run-time of " + question + " seconds. \n\nPlease convert it to minutes \n\nRound to two decimal places:");	
		whackText.setFont(normalFont);
	
		String rideTime = JOptionPane.showInputDialog("Please Enter you're answer:");
		   
		float times = Float.parseFloat(rideTime);
		 
		 if (times == secToMin(question)){
		        finalScore+=1;
		        JOptionPane.showMessageDialog(null, "Good Job!." , ",", JOptionPane.PLAIN_MESSAGE);
		        lvl++;
		        lvlLabelNo.setText(""+lvl);
		        whackText.setText("This ride has a run-time of " + question + " seconds. \n\nPlease convert it to minutes \n\nRound to two decimal places:");	
		    	whackText.setFont(normalFont);
		    	 if (lvl>=10){
		    		 JOptionPane.showMessageDialog(null, "You beat Ride Times! GAMEOVER " +secToMin(question) , ":(" , JOptionPane.PLAIN_MESSAGE);
		    		 System.out.println("Your Final Score for Ride Times is " + rideScore + "%!");
		    		 String useranswer = JOptionPane.showInputDialog(null, "Would you like to save this score to a file on your computer? (y/n)\n");
		             useranswer = useranswer.toLowerCase();
		             if (useranswer.equals("y")){
		             saveToRFile(rideScore, name);
		             whackText.setText("Statistics successfully saved. Goodbye!");
		             }
		             else
		             System.out.println("OK. Goodbye!");
		            
		    	  }
		        
		      }else
		        JOptionPane.showMessageDialog(null, "Sorry, but the answer was " +secToMin(question) , "Oops!" , JOptionPane.PLAIN_MESSAGE); 
		 		lives--;
		 		triesLabelNo.setText(""+lives);
		 		JOptionPane.showMessageDialog(null, "Lives Left:  " +lives , ":(" , JOptionPane.WARNING_MESSAGE); 
		 		 if (lives<=0){
		 			 JOptionPane.showMessageDialog(null, "Sorry, you have no more lives. GAMEOVER " , "ahahAHAHAhahaHAHA" , JOptionPane.WARNING_MESSAGE);
		 			 JOptionPane.showMessageDialog(null, "Your Final Score for Ride Times is " + rideScore + "%!" , "", JOptionPane.PLAIN_MESSAGE);
		             String useranswer = JOptionPane.showInputDialog(null, "Would you like to save this score to a file on your computer? (y/n)\n");
		             useranswer = useranswer.toLowerCase();
		             if (useranswer.equals("y")){
		             saveToRFile(rideScore, name);
		             JOptionPane.showMessageDialog(null,"Statistics successfully saved. Goodbye!","" ,JOptionPane.WARNING_MESSAGE);
		             }
		             else
		            	 JOptionPane.showMessageDialog(null,"Thank you for playing Goodbye!");
		 			 System.exit(0);
		 		    }
		    } 
	
		 
	}
	private void statistics() {
		
		mainTextPanel.setVisible(false);
		choiceButtonPanel.setVisible(false);
		playerPanel.setVisible(false);
		
		
		mainTextPanel.setVisible(false);
		choiceButtonPanel.setVisible(false);
		
		whackPanel= new JPanel();
		whackPanel.setBounds(140, 100, 600, 250);
		whackPanel.setBackground(Color.black);
		con.add(whackPanel);
		
		whackText= new JTextArea(" Welcome to your Statistics!\n\n\n You can come here to view past\n scores and times!");
		whackText.setBounds(140,100,600,250);
		whackText.setBackground(Color.black);
		whackText.setForeground(Color.yellow);
		whackText.setFont(mediumFont);
		whackText.setLineWrap(true);
		whackText.setEditable(false);
		whackPanel.add(whackText);
		
		whackPan= new JPanel();
		whackPan.setBounds(250,390,300,150);
		whackPan.setBackground(Color.red);
		whackPan.setLayout(new GridLayout(2,1));
		
		
		con.add(whackPan);
		
		returnWhack = new JButton( "Return" );
		returnWhack.setBackground(Color.black);
		returnWhack.setForeground(Color.white);
		returnWhack.setFont(smallFont);
		
		continueWhack = new JButton( "Continue" );
		continueWhack.setBackground(Color.black);
		continueWhack.setForeground(Color.white);
		continueWhack.setFont(smallFont);
		
		whackPan.add(returnWhack);
		whackPan.add(continueWhack);
		
		returnWhack.setFocusPainted(false);
		returnWhack.addActionListener(choiceHandler);
		returnWhack.setActionCommand("returnW");
		
		continueWhack.setFocusPainted(false);
		continueWhack.addActionListener(choiceHandler);
		continueWhack.setActionCommand("continueS");
		
		
	
	
		
	}
	
	
public void statistics2() throws IOException {
	

		window.setVisible(false);
		
		window2 = new JFrame();
		window2.setSize(800, 600); 
		window2.setLayout(null); 
		window2.setVisible(true);
		window2.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window2.getContentPane().setBackground(Color.black);
		window2.setTitle("Statistics");
		con = window2.getContentPane();
		
		statPanel3= new JPanel();
		statPanel3.setBounds(3,3,780,580);
		statPanel3.setBackground(Color.black);
		con.add(statPanel3);
		
		//statLabel2= new JLabel("xxxxxxxxxxxxxxxxx");
		//statLabel2.setForeground(Color.green);
		//statLabel2.setFont(smallFont);
		
		statText5= new JTextArea();
		statText5.setBounds(3,3,780,580);
		statText5.setBackground(Color.white);
		statText5.setForeground(Color.green);
		statText5.setFont(normalFont);
		statText5.setLineWrap(true);
		statText5.setEditable(false);
		statPanel3.add(statText5);
		
		
		

	
				   
		
		
		  Scanner scan = new Scanner(System.in);
		    File stats = new File("Statistics.txt");
		    stats.createNewFile();
		    
		    @SuppressWarnings("resource")
			Scanner fileRead = new Scanner(new File("Statistics.txt"));
		    
		    System.out.println("Here are the contents from the Statistics.txt file on your computer.");
		       
	        while (fileRead.hasNextLine()) {  
	        String line = fileRead.nextLine();
	        statText5.setText("      "+line+"         ");
	        JOptionPane.showMessageDialog(null,""+line);
	        statText5.setText(line);
	        
	        }
	        System.out.println("Goodbye!");
		  
		
	}
public void playerSetup() {
		lvl=0;
		lives=5;
		lvlLabelNo.setText(""+lvl);
		triesLabelNo.setText(""+lives);
		
	}
	public static int[] RandomizeArray(int a, int b){
			Random rgen = new Random();  // Random number generator		
			int size = b-a+1;
			int[] array = new int[size];
	 
			for(int i=0; i< size; i++){
				array[i] = a+i;
			}
	 
			for (int i=0; i<array.length; i++) {
			    int randomPosition = rgen.nextInt(array.length);
			    int temp = array[i];
			    array[i] = array[randomPosition];
			    array[randomPosition] = temp;
			}
			return array;
		}
	  

public class TitleScreenHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			createGameScreen();
			
		}
	}
	
	public class TitleScreenExitHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
	}
	public class ChoiceHandler implements ActionListener{
	
		public void actionPerformed(ActionEvent event) {
			
			String yourChoice = event.getActionCommand();
			
			switch(yourChoice) {
			
			case "c1": whackamoleScreen(); break;
				
			case "c2": rideTimesScreen(); break;
				
			case "c3": statistics(); break;
				
			case "c4": System.exit(0); break;
			
			case "returnW": 
				
				whackPan.setVisible(false);
				whackPanel.setVisible(false);
				playerPanel.setVisible(false);
				createGameScreen(); break;
				
			case "returnR": 
				
				ridePanel2.setVisible(false);
				playerPanel.setVisible(false);
				createGameScreen(); break;
				
			case "returnS": 
				
				statPanel.setVisible(false);
				playerPanel.setVisible(false);
				whackamoleScreen(); break;
				
				
case "continueW": 
				
	whackamoleScreen2(); break;
	
				
case "continueR": 
	
	rideTimesScreen2(); break;
	
	
case "continueS": 
	
				try {
					statistics2();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				} break;
	
case "playR":
	
				try {
					rideTimesScreen3();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
case "exitR": System.exit(0);
				
			
			}
		}
			public class ChoiceHand implements ActionListener{
				
				public void actionPerformed(ActionEvent event) {
			
	}

				
				
	
	 public int getInt(Scanner input, String prompt){
		 JOptionPane.showInputDialog(prompt);		    
		 while(!input.hasNextInt()){
		      input.next();
		      System.out.println("Not an integer! Try again");
		    }
		    return input.nextInt();
		  }
	/*
	 * STATISTICS
	 * STATISTICS
	 * STATISTICS
	 * STATISTICS
	 * STATISTICS
	 * 
	 */
	 public void saveToWFile(int score, String name)  throws IOException{
		    FileWriter fwriter = new FileWriter("Statistics.txt", true);
		    PrintWriter statFile = new PrintWriter(fwriter);
		    statFile.println(name + "'s Wack-A-Mole Game on " + getDate() + " at " + getTime() + "\n\t Score: " + score + "%");
		    statFile.close();
		  }
		  
		  //file save method to be used for the ride times game
		  public void saveToRFile(int score, String name)  throws IOException{
		    FileWriter fwriter = new FileWriter("Statistics.txt", true);
		    PrintWriter statFile = new PrintWriter(fwriter);
		    statFile.println(name + "'s Ride Time's Game on " + getDate() + " at " + getTime() + "\n\t Score: " + score + "%");
		    statFile.close();
		  }
		  
		  //helper method which grabs current time for use in statistics file
		  public String getTime(){
		    String time = "" + LocalDateTime.now();
		    time = time.substring(time.indexOf("T")+1,time.indexOf("T")+9);
		    for(int i =0; i < time.length()-1;i++)
		    {
		      String beginning = "";
		      
		      if(time.charAt(i)==':'){
		        beginning = time.substring(0,i);
		        time = beginning + ":" + time.substring(i+1);
		      }
		    }
		    return time;
		  }
		  //helper method which grabs current date for use in statistics file
		  public String getDate(){
		    String date = "" + LocalDateTime.now();
		    date = date.substring(0, date.indexOf("T"));
		    for(int i = 0; i < date.length()-1;i++)
		    {
		      String beginning = "";
		      
		      if(date.charAt(i)=='_'){
		        beginning = date.substring(0,i);
		        date = beginning + "/" + date.substring(i+1);
		      }
		  }
		  return date;
		  }
			}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


public static double secToMin(int d){
    if(d <60)
      System.out.println("Number too low");
    double conversion=0;
    conversion = (((double)((int)(((double)d/60)*100)))/100);
    
    return conversion;
  }

		   
// file save method to be used for the wack a mole game
public  void saveToWFile(int score, String name)  throws IOException{
  FileWriter fwriter = new FileWriter("Statistics.txt", true);
  PrintWriter statFile = new PrintWriter(fwriter);
  statFile.println(name + "'s Wack-A-Mole Game on " + getDate() + " at " + getTime() + "\n\t Score: " + score + "%");
  statFile.close();
}

//file save method to be used for the ride times game
public  void saveToRFile(int score, String name)  throws IOException{
  FileWriter fwriter = new FileWriter("Statistics.txt", true);
  PrintWriter statFile = new PrintWriter(fwriter);
  statFile.println(name + "'s Ride Time's Game on " + getDate() + " at " + getTime() + "\n\t Score: " + score + "%");
  statFile.close();
}


//ALL REMAINING METHODS ARE HELPER METHODS

//Remaining methods which are type checks for user input, to prevent variable type mismatches. getdouble, getstring, and getInt
//helper method to convert seconds to minutes

//helper method which grabs current time for use in statistics file
public  String getTime(){
  String time = "" + LocalDateTime.now();
  time = time.substring(time.indexOf("T")+1,time.indexOf("T")+9);
  for(int i =0; i < time.length()-1;i++)
  {
    String beginning = "";
    
    if(time.charAt(i)==':'){
      beginning = time.substring(0,i);
      time = beginning + ":" + time.substring(i+1);
    }
  }
  return time;
}
//helper method which grabs current date for use in statistics file
public String getDate(){
  String date = "" + LocalDateTime.now();
  date = date.substring(0, date.indexOf("T"));
  for(int i = 0; i < date.length()-1;i++)
  {
    String beginning = "";
    
    if(date.charAt(i)=='_'){
      beginning = date.substring(0,i);
      date = beginning + "/" + date.substring(i+1);
    }
}
return date;
}}
	

import java.lang.Math;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.io.*;
import java.util.Random;

public class FinalGameMain{
 
  public static void main(String[] args) throws IOException{
      boolean flag = true;
      
    Scanner scan = new Scanner(System.in);
    File stats = new File("Statistics.txt");
    stats.createNewFile();
    
    Scanner fileRead = new Scanner(new File("Statistics.txt"));
  
    
    System.out.println("Welcome to the game menu! Before you begin, please enter your first and last name, separated by a space.");
    
    String name = scan.nextLine();
      if (!name.contains(" "))
      name = name + " ";

    System.out.println("Hi, " + name.substring(0, name.indexOf(' ')) + ". You have three options. You can play the Whack-a-Mole game, the Ride Times game, or you can review your statistics for this session.");
    do{
    int option = menu();
    int wackScore;
    int rideScore;
    String answer;
    //Switch Menu that contains the Wack-A-Mole game, Ride Times game, Statistics file, and exit option.
    switch (option) {
      case 1:
          wackScore = (wackAMole()*33) + 1;
          if (wackScore == 1)
            wackScore--;
         System.out.println("Your Final Score for Wack-A-Mole is " + wackScore + "%!");
         answer = getString(scan, "Would you like to save this score to a file on your computer? (y/n)\n");
         answer = answer.toLowerCase();
         if (answer.equals("y")){
         saveToWFile(wackScore, name);
         System.out.println("Statistics successfully saved. Goodbye!");
         }
         else
         System.out.println("OK. Goodbye!");
        break;
      case 2:
        rideScore = rideTimes()*10;
        
        System.out.println("Your Final Score for Ride Times is " + rideScore + "%!");
         answer = getString(scan, "Would you like to save this score to a file on your computer? (y/n)\n");
         answer = answer.toLowerCase();
         if (answer.equals("y")){
         saveToRFile(rideScore, name);
         System.out.println("Statistics successfully saved. Goodbye!");
         }
         else
         System.out.println("OK. Goodbye!");
        break;
      case 3:
          System.out.println("Here are the contents from the Statistics.txt file on your computer.");
       
        while (fileRead.hasNextLine()) {  
        String line = fileRead.nextLine();
        System.out.println(line);
        }
        System.out.println("Goodbye!");
        break;
      case 0:
        System.out.println("Have a good day!");
        break;
    }
  
    String choice = getString(scan, "To exit the game, type End. To see the menu again, press enter.");
    choice = choice.toLowerCase();
    if (choice.equals("end"))
          flag = false;
    }
    while(flag);
  }
  
  //menu method for user to pick their option. returns option picked
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
  
//Wackamole. Three preset arrays, chosen in a random order for the user to guess the number associated with the prime. Returns score.
  public static int wackAMole(){
      Scanner keyboard = new Scanner(System.in);
      int[] mole1 = new int[] {41, 25, 42, 17, 14, 7, 2, 8, 16};
      int[] mole2 = new int[] {40, 23, 34, 28, 8, 4, 13, 20, 25};
      int[] mole3 = new int[] {13, 43, 19, 5, 18, 20, 21, 33, 17};
      int[] choices = RandomizeArray(1, 3);
      int score = 0;
      int option;
      int answer;
      for (int i = 0; i<3; i++){
          option = choices[i];
      switch (option) {
        case 1:
            System.out.println(mole1[0] + ", " + mole1[1] + ", " + mole1[2] + ", ");
            System.out.println(mole1[3] + ", " + mole1[4] + ", " + mole1[5] + ", ");
            System.out.println(mole1[6] + ", " + mole1[7] + ", " + mole1[8]);
            System.out.println();
            System.out.println("The Prime Number is: 3");
            answer = getInt(keyboard, "Please enter the number in the above array which is a multiple of 3\n");
            if (answer == 42){
                System.out.println("Good job!\n");
            score++;
            }
            else
            System.out.println("Sorry but that was the wrong answer!\n");
            break;
        case 2:
            System.out.println(mole2[0] + ", " + mole2[1] + ", " + mole2[2] + ", ");
            System.out.println(mole2[3] + ", " + mole2[4] + ", " + mole2[5] + ", ");
            System.out.println(mole2[6] + ", " + mole2[7] + ", " + mole2[8]);
            System.out.println();
            System.out.println("The Prime Number is: 7");
            answer = getInt(keyboard, "Please enter the number in the above array which is a multiple of 7\n");
            if (answer == 28){
                System.out.println("Good job!\n");
            score++;
            }
            else
            System.out.println("Sorry but that was the wrong answer!\n");
            break;
        case 3:
            System.out.println(mole3[0] + ", " + mole3[1] + ", " + mole3[2] + ", ");
            System.out.println(mole3[3] + ", " + mole3[4] + ", " + mole3[5] + ", ");
            System.out.println(mole3[6] + ", " + mole3[7] + ", " + mole3[8]);
            System.out.println();
            System.out.println("The Prime Number is: 6");
            answer = getInt(keyboard, "Please enter the number in the above array which is a multiple of 6\n");
            if (answer == 18){
                System.out.println("Good job!\n");
            score++;
            }
            else
            System.out.println("Sorry but that was the wrong answer!\n");
            break;
      }
      
    }
    return score;
  }
  
  
 // file save method to be used for the wack a mole game
  public static void saveToWFile(int score, String name)  throws IOException{
    FileWriter fwriter = new FileWriter("Statistics.txt", true);
    PrintWriter statFile = new PrintWriter(fwriter);
    statFile.println(name + "'s Wack-A-Mole Game on " + getDate() + " at " + getTime() + "\n\t Score: " + score + "%");
    statFile.close();
  }
  
  //file save method to be used for the ride times game
  public static void saveToRFile(int score, String name)  throws IOException{
    FileWriter fwriter = new FileWriter("Statistics.txt", true);
    PrintWriter statFile = new PrintWriter(fwriter);
    statFile.println(name + "'s Ride Time's Game on " + getDate() + " at " + getTime() + "\n\t Score: " + score + "%");
    statFile.close();
  }
  
  
  //ALL REMAINING METHODS ARE HELPER METHODS
  
  //Remaining methods which are type checks for user input, to prevent variable type mismatches. getdouble, getstring, and getInt
  //helper method to convert seconds to minutes
  
  //helper method which grabs current time for use in statistics file
  public static String getTime(){
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
  public static String getDate(){
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
  
  public static double secToMin(int d){
    if(d <60)
      System.out.println("Number too low");
    double conversion=0;
    conversion = (((double)((int)(((double)d/60)*100)))/100);
    
    return conversion;
  }
  
  //Helper method which randomizes an array to preserve originiality
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
  public static int getInt(Scanner input, String prompt){
    System.out.print(prompt);
    while(!input.hasNextInt()){
      input.next();
      System.out.println("Not an integer! Try again");
    }
    return input.nextInt();
  }
}

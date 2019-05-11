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
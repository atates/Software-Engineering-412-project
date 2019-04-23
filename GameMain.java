import java.lang.Math;
import java.util.Scanner;
public class GameMain{
  public static void main(String[] args){
    //for (int i = 0; i <10; i++){
   // int question = (int)((Math.random()*((600-60) + 1)) + 60);
   // System.out.print(question + ", ");
    
   // }
  
   // System.out.println("\n" + secToMin(382));
  
    int score = rideTimes();
    System.out.println("Your Final Score for Ride Times is " + score*10 + "%!");
    System.out.println("Would you like to save this score to a downloadable file on your computer?");
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
}
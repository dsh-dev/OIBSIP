import java.util.*;
public class numberGuessingGame{
    static Scanner sc=new Scanner(System.in);
    static Random random=new Random();
    static int totalScore=0;
    static int round=1;
    public static void main(String args[])
    {
        welcomeScreen();
        boolean playAgain=true;
        while(playAgain){
            System.out.println("-----------------------------------------------------------");
            System.out.println("ROUND "+round);
            System.out.println("-----------------------------------------------------------");
            playRound();
            System.out.print("\n Do you want to unlock another game?(yes/no)");
            String choice=sc.next().toLowerCase();
            if(choice.equals("yes")){
                round++;
            }
            else{
                playAgain=false;
            }
        }
        gameSummary();
    }
    public static void welcomeScreen()
    {
     System.out.println("-----------------------------------------------------------");
     System.out.println("MYSTERY VAULT - NUMBER GUESSING GAME");
     System.out.println("-----------------------------------------------------------");
     System.out.println("A secret vault is locked with a hidden code");
     System.out.println("You must guess the correct number to unlock it!");
     System.out.println("The number is between 1 and 100");
     System.out.println("You have limited attempts , Earn more points with fewer attempts");
     System.out.println("-----------------------------------------------------------");
    }
    public static void playRound()
    {
        int secretNumber=random.nextInt(100)+1;
        int attemptsLeft=7;
        int score=100;
        ArrayList<Integer> guessedNumbers=new ArrayList<>();
        System.out.println("\nGuess the number between 1 and 100");
        System.out.println("You have "+ attemptsLeft + "attempts.");
        while(attemptsLeft>0){
            System.out.println("\nEnter your guess: ");
            int guess=sc.nextInt();
            if(guessedNumbers.contains(guess)){
              System.out.println("⚠ You already guessed this number!");
              continue;
            }
            guessedNumbers.add(guess);
            if(guess==secretNumber)
            {
                System.out.println("\n ACCESS GRANTED!");
                System.out.println("You guessed the correct number: " + secretNumber);
                score+=attemptsLeft*10;
                totalScore+=score;
                System.out.println("Round score: "+score);
                return;
            }
            if(guess>secretNumber)
            {
                System.out.println("Too HIGH!");
            }
            else {
                System.out.println("Too LOW!");
            }
            int difference=Math.abs(secretNumber-guess);
            if (difference<=5) {
                System.out.println(" Very Close!");
            } else if (difference<=15) {
                System.out.println(" Close!");
            } else {
                System.out.println("❄ Far Away!");
            }
            attemptsLeft--;
            score-=10;
            System.out.println("Attempts left: "+attemptsLeft);
        }
        System.out.println("\n ACCESS DENIED!");
        System.out.println("The secret number was: " + secretNumber);
    }
    public static void gameSummary(){
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("             GAME OVER");
        System.out.println("-----------------------------------------------------------");
        if (round == 0) {
        System.out.println("Rank : No Games Played");
        return;
         }
        System.out.println("Number of Rounds : " + round);
        System.out.println("Total Score : " + totalScore);
        int avgScore = totalScore / round;
        if (avgScore >= 150)
        {
        System.out.println("Rank : MASTER PLAYER ");
        } 
        else if(avgScore >= 120)
        {
        System.out.println("Rank : ELITE PLAYER ");
        } 
        else if(avgScore >= 90)
        {
        System.out.println("Rank : ROOKIE PLAYER ");
        } 
        else
        {
        System.out.println("Rank : BEGINNER PLAYER ");
        }
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("Thanks for playing! See you next time!");
    }
}
